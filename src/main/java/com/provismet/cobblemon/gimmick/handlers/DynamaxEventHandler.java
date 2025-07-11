package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.provismet.cobblemon.gimmick.api.event.DynamaxEvents;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.util.GlowHandler;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

import java.util.*;

public abstract class DynamaxEventHandler {
    private static final Map<UUID, ScalingData> activeScalingAnimations = new HashMap<>();
    private static final WeakHashMap<UUID, LivingEntity> entityCache = new WeakHashMap<>();
    private static MinecraftServer server;

    public static void register () {
        DynamaxEvents.DYNAMAX_START.register(Event.DEFAULT_PHASE, DynamaxEventHandler::startDynamax);
        DynamaxEvents.DYNAMAX_END.register(Event.DEFAULT_PHASE, DynamaxEventHandler::endDynamax);
        ServerTickEvents.END_SERVER_TICK.register(serverInstance -> {
            server = serverInstance;
            updateScalingAnimations();
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

        if (server == null && pokemonEntity.getWorld() instanceof ServerWorld serverWorld) {
            server = serverWorld.getServer();
        }

        startGradualScaling(pokemon.getEntity(), Options.getDynamaxScaleFactor());

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

        pokemonEntity.removeStatusEffect(StatusEffects.GLOWING);

        if (server == null && pokemonEntity.getWorld() instanceof ServerWorld serverWorld) {
            server = serverWorld.getServer();
        }

        startGradualScaling(pokemonEntity, 1.0f);
    }

    public static void startGradualScaling(LivingEntity entity, float targetScale) {
        UUID entityId = entity.getUuid();
        EntityAttributeInstance scaleAttribute = entity.getAttributeInstance(EntityAttributes.GENERIC_SCALE);

        if (scaleAttribute != null) {
            entityCache.put(entityId, entity);

            float startScale = (float) scaleAttribute.getBaseValue();

            int durationTicks = 60;

            ScalingData scalingData = new ScalingData(
                    entity.getWorld().getRegistryKey().toString(),
                    entityId,
                    startScale,
                    targetScale,
                    durationTicks,
                    0
            );

            activeScalingAnimations.put(entityId, scalingData);
        }
    }

    private static void updateScalingAnimations() {
        if (server == null) return;

        Iterator<Map.Entry<UUID, ScalingData>> iterator = activeScalingAnimations.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, ScalingData> entry = iterator.next();
            UUID entityId = entry.getKey();
            ScalingData data = entry.getValue();

            data.currentTick++;

            LivingEntity entity = entityCache.get(entityId);

            if (entity == null || entity.isRemoved()) {
                for (ServerWorld world : server.getWorlds()) {
                    entity = (LivingEntity) world.getEntity(entityId);
                    if (entity != null) {
                        entityCache.put(entityId, entity);
                        break;
                    }
                }
            }

            // If entity exists, update its scale
            if (entity != null && !entity.isRemoved()) {
                EntityAttributeInstance scaleAttribute = entity.getAttributeInstance(EntityAttributes.GENERIC_SCALE);
                if (scaleAttribute != null) {
                    float progress = Math.min(1.0f, (float) data.currentTick / data.durationTicks);
                    float newScale = data.startScale + (data.targetScale - data.startScale) * progress;

                    scaleAttribute.setBaseValue(newScale);
                }

                if (data.currentTick >= data.durationTicks) {
                    iterator.remove();
                    entityCache.remove(entityId);
                }
            } else {
                iterator.remove();
                entityCache.remove(entityId);
            }
        }
    }

    private static class ScalingData {
        final String worldId;
        final UUID entityId;
        final float startScale;
        final float targetScale;
        final int durationTicks;
        int currentTick;

        public ScalingData(String worldId, UUID entityId, float startScale, float targetScale, int durationTicks, int currentTick) {
            this.worldId = worldId;
            this.entityId = entityId;
            this.startScale = startScale;
            this.targetScale = targetScale;
            this.durationTicks = durationTicks;
            this.currentTick = currentTick;
        }
    }
}
