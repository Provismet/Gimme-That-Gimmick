package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.serialization.Codec;
import org.jetbrains.annotations.Nullable;

public interface ParticleAnimation {
    Codec<ParticleAnimation> CODEC = Codec.STRING.dispatch(
        animation -> {
            if (animation instanceof SnowstormEffect) return "snowstorm";
            if (animation instanceof VanillaParticleEffect) return "vanilla";
            return null;
        },
        string -> {
            if (string.equals("snowstorm")) return SnowstormEffect.CODEC;
            if (string.equals("vanilla")) return VanillaParticleEffect.CODEC;
            return null;
        }
    );

    void runParticles (PokemonEntity source, @Nullable PokemonEntity target);
}
