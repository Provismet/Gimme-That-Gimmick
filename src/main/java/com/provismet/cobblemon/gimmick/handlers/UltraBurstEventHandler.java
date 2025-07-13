package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.api.event.UltraBurstEvents;
import com.provismet.cobblemon.gimmick.item.zmove.TypedZCrystalItem;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class UltraBurstEventHandler {
    public static void register () {
        UltraBurstEvents.ULTRA_BURST.register(Event.DEFAULT_PHASE, UltraBurstEventHandler::onUltra);
    }

    private static void onUltra (PokemonBattle battle, BattlePokemon battlePokemon) {
        Pokemon pokemon = battlePokemon.getEffectedPokemon();

        if (pokemon.getSpecies().getName().equals("Necrozma")) {
            battle.dispatchToFront(() -> {
                if (pokemon.getAspects().contains("dusk-fusion") || pokemon.getAspects().contains("dawn-fusion")) {
                    if (pokemon.getFeature("prism_fusion") instanceof StringSpeciesFeature feature) {
                        pokemon.getPersistentData().putString("prism_fusion", feature.getValue());
                        new StringSpeciesFeature("prism_fusion", "ultra").apply(pokemon);
                    }
                }
                return new UntilDispatch(() -> true);
            });

            if (pokemon.getEntity() != null) {
                Identifier key = GimmeThatGimmickMain.identifier("ultra_burst");
                Optional<RegistryEntry.Reference<EffectsData>> effect = EffectsData.get(pokemon.getEntity().getRegistryManager(), key);
                if (effect.isPresent()) {
                    Optional<PokemonEntity> other = StreamSupport.stream(battle.getActivePokemon().spliterator(), false)
                        .map(ActiveBattlePokemon::getBattlePokemon)
                        .filter(active -> battlePokemon.getFacedOpponents().contains(active))
                        .filter(Objects::nonNull)
                        .map(BattlePokemon::getEntity)
                        .filter(Objects::nonNull)
                        .findAny();

                    effect.get().value().run(pokemon.getEntity(), other.orElse(null), battle);
                }
            }
        }
    }
}
