package com.provismet.cobblemon.gimmick.api.data.particle;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.serialization.Codec;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public interface ParticleAnimation {
    Codec<ParticleAnimation> CODEC = Codec.STRING.dispatch(
        animation -> {
            if (animation instanceof SnowstormEffect) return "snowstorm";
            if (animation instanceof VanillaParticleEffect) return "vanilla";
            throw new NotImplementedException("The provided particle effect is not an instance of \"snowstorm\" or \"vanilla\".");
        },
        string -> {
            if (string.equals("snowstorm")) return SnowstormEffect.CODEC;
            if (string.equals("vanilla")) return VanillaParticleEffect.CODEC;
            throw new NotImplementedException("The provided particle effect type \"" + string + "\", does not match \"snowstorm\" or \"vanilla\".");
        }
    );

    void runParticles (PokemonEntity source, @Nullable PokemonEntity target);
}
