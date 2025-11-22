package com.provismet.cobblemon.gimmick.api.data.particle;

import com.cobblemon.mod.common.CobblemonNetwork;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.net.messages.client.effect.SpawnSnowstormEntityParticlePacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * Represents a Snowstorm particle effects, as provided by Cobblemon.
 * <p>
 * Snowstorm particles are custom data-driven particles that can have complex animations.
 *
 * @param id The asset id of the particle.
 * @param sourceLocators List of source locators as required by Snowstorm.
 * @param targetLocators Optional list of target locators as required by Snowstorm.
 *
 * @see SpawnSnowstormEntityParticlePacket
 */
public record SnowstormEffect (Identifier id, List<String> sourceLocators, Optional<List<String>> targetLocators) implements ParticleAnimation {
    public static final MapCodec<SnowstormEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        Identifier.CODEC.fieldOf("particleId").forGetter(SnowstormEffect::id),
        Codec.STRING.listOf().optionalFieldOf("sourceLocators", List.of("root")).forGetter(SnowstormEffect::sourceLocators),
        Codec.STRING.listOf().optionalFieldOf("targetLocators").forGetter(SnowstormEffect::targetLocators)
    ).apply(instance, SnowstormEffect::new));

    @Override
    public void runParticles (PokemonEntity source, @Nullable PokemonEntity target) {
        SpawnSnowstormEntityParticlePacket packet = new SpawnSnowstormEntityParticlePacket(
            this.id,
            source.getId(),
            this.sourceLocators,
            target == null ? null : target.getId(),
            target == null || this.targetLocators.isEmpty() ? null : this.targetLocators.get()
        );
        CobblemonNetwork.sendToAllPlayers(packet);
    }
}
