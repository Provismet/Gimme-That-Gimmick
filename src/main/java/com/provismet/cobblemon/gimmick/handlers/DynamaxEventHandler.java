package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.api.event.DynamaxEvents;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.registry.GTGStatusEffects;
import com.provismet.cobblemon.gimmick.util.GlowHandler;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.stream.StreamSupport;

public abstract class DynamaxEventHandler {
    private static final List<ScalingData> scalingData = new LinkedList<>();

    public static void register () {
        DynamaxEvents.DYNAMAX_START.register(Event.DEFAULT_PHASE, DynamaxEventHandler::startDynamax);
        DynamaxEvents.DYNAMAX_END.register(Event.DEFAULT_PHASE, DynamaxEventHandler::endDynamax);
        ServerTickEvents.START_SERVER_TICK.register(server -> DynamaxEventHandler.updateScalingAnimations());
    }

    public static void scaleDownDynamax (PokemonEntity pokemonEntity) {
        scalingData.add(new ScalingData(Options.getDynamaxScaleDuration()) {
            @Override
            protected void function () {
                if (!pokemonEntity.isRemoved() && pokemonEntity.hasStatusEffect(GTGStatusEffects.DYNAMAX)) {
                    pokemonEntity.setStatusEffect(
                        new StatusEffectInstance(
                            GTGStatusEffects.DYNAMAX,
                            Integer.MAX_VALUE,
                            (this.maxAge - this.age),
                            true,
                            true,
                            true
                        ),
                        null
                    );
                }
                else {
                    this.age = this.maxAge;
                }

                if (this.age == this.maxAge) pokemonEntity.removeStatusEffect(StatusEffects.GLOWING);
            }
        });
    }

    private static void startDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon, boolean gmax) {
        if (gmax) {
            pokemonBattle.dispatchToFront(() -> {
                new StringSpeciesFeature("dynamax_form", "gmax").apply(pokemon.getEffectedPokemon());
                CobblemonEventHandler.updatePokemonPackets(pokemonBattle, pokemon, false);
                return new UntilDispatch(() -> true);
            });
        }
        PokemonEntity pokemonEntity = pokemon.getEntity();
        if (pokemonEntity == null) return;

        List<String> prioritisedEffects = List.of(
            "dynamax_" + pokemon.getEffectedPokemon().getSpecies().showdownId(),
            "dynamax"
        );

        for (String effectName : prioritisedEffects) {
            Identifier key = GimmeThatGimmickMain.identifier(effectName);
            Optional<RegistryEntry.Reference<EffectsData>> effect = EffectsData.get(pokemonEntity.getRegistryManager(), key);
            if (effect.isPresent()) {
                Optional<PokemonEntity> other = StreamSupport.stream(pokemonBattle.getActivePokemon().spliterator(), false)
                    .map(ActiveBattlePokemon::getBattlePokemon)
                    .filter(active -> pokemon.getFacedOpponents().contains(active))
                    .filter(Objects::nonNull)
                    .map(BattlePokemon::getEntity)
                    .filter(Objects::nonNull)
                    .findAny();

                effect.get().value().run(pokemonEntity, other.orElse(null), pokemonBattle);
            }
        }

        scalingData.add(new ScalingData(Options.getDynamaxScaleDuration()) {
            @Override
            protected void function () {
                if (!pokemonEntity.isRemoved()) {
                    pokemonEntity.addStatusEffect(
                        new StatusEffectInstance(
                            GTGStatusEffects.DYNAMAX,
                            Integer.MAX_VALUE,
                            this.age,
                            true,
                            true,
                            true
                        )
                    );
                }
                else {
                    this.age = this.maxAge;
                }
            }
        });

        if (Options.shouldApplyBasicDynamaxGlow()) GlowHandler.applyDynamaxGlow(pokemonEntity);
    }

    private static void endDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon) {
        pokemonBattle.dispatchToFront(() -> {
            pokemon.getEffectedPokemon().getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("dynamax_form"));
            pokemon.getEffectedPokemon().updateAspects();
            return new UntilDispatch(() -> true);
        });
        PokemonEntity pokemonEntity = pokemon.getEntity();
        if (pokemonEntity == null) return;

        scaleDownDynamax(pokemonEntity);
    }

    private static void updateScalingAnimations () {
        scalingData.removeIf(ScalingData::isDone);
        scalingData.forEach(ScalingData::run);
    }

    private static abstract class ScalingData {
        protected final int maxAge;
        protected int age;

        public ScalingData (int maxAge) {
            this.age = 0;
            this.maxAge = maxAge;
        }

        public void run () {
            this.function();
            ++this.age;
        }

        public boolean isDone () {
            return this.age > this.maxAge;
        }

        protected abstract void function ();
    }
}
