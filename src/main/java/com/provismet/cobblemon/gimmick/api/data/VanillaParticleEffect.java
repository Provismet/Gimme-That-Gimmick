package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record VanillaParticleEffect (List<VanillaParticleLayer> layers) implements ParticleAnimation {
    public static final MapCodec<VanillaParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
        VanillaParticleLayer.CODEC.listOf().fieldOf("layers").forGetter(VanillaParticleEffect::layers)
    ).apply(instance, VanillaParticleEffect::new));


    @Override
    public void runParticles (PokemonEntity source, @Nullable PokemonEntity target) {
        this.layers.forEach(layer -> layer.run(source));
    }

    public record VanillaParticleLayer (Identifier id, int count, double maxDistance) {
        public static final Codec<VanillaParticleLayer> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(VanillaParticleLayer::id),
            Codecs.POSITIVE_INT.fieldOf("count").forGetter(VanillaParticleLayer::count),
            Codec.DOUBLE.fieldOf("maxDistance").forGetter(VanillaParticleLayer::maxDistance)
        ).apply(instance, VanillaParticleLayer::new));

        public VanillaParticleLayer (ParticleType<?> particleType, int count, double maxDistance) {
            this(Registries.PARTICLE_TYPE.getId(particleType), count, maxDistance);
        }

        public void run (PokemonEntity source) {
            Optional<ParticleType<?>> particle = Registries.PARTICLE_TYPE.getOrEmpty(this.id);
            if (particle.isPresent() && source.getWorld() instanceof ServerWorld world && particle.get() instanceof SimpleParticleType particleType) {
                world.spawnParticles(
                    particleType,
                    source.getX(), source.getY(), source.getZ(),
                    this.count,
                    this.maxDistance, this.maxDistance, this.maxDistance,
                    0
                );
            }
        }
    }
}
