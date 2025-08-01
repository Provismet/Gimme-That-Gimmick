package com.provismet.cobblemon.gimmick.api.data.registry;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.net.messages.client.animation.PlayPosableAnimationPacket;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.particle.ParticleAnimation;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import kotlin.Unit;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Registerable object containing the data for special effects surrounding a Pokémon.
 *
 * @param particles The optional particle animation to play.
 * @param pokemonAnimation The optional animation to trigger on the Pokémon.
 * @param formChangeDelay Optional and only triggers in battle, how many seconds to wait until the form change occurs.
 * @param sound Optional sound to play when the effect is triggered.
 */
public record EffectsData (Optional<ParticleAnimation> particles, Optional<String> pokemonAnimation, Optional<Float> formChangeDelay, Optional<SoundEvent> sound) {
    public static final Codec<EffectsData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        ParticleAnimation.CODEC.optionalFieldOf("particles").forGetter(EffectsData::particles),
        Codec.STRING.optionalFieldOf("animation").forGetter(EffectsData::pokemonAnimation),
        Codec.FLOAT.optionalFieldOf("formDelaySeconds").forGetter(EffectsData::formChangeDelay),
        SoundEvent.CODEC.optionalFieldOf("sound").forGetter(EffectsData::sound)
    ).apply(instance, EffectsData::new));

    public static RegistryKey<EffectsData> key (Identifier id) {
        return RegistryKey.of(GTGDynamicRegistryKeys.EFFECTS, id);
    }

    public static Optional<RegistryEntry.Reference<EffectsData>> get (DynamicRegistryManager registryManager, Identifier id) {
        return registryManager.getOptionalWrapper(GTGDynamicRegistryKeys.EFFECTS)
            .flatMap(registry -> registry.getOptional(EffectsData.key(id)));
    }

    public static void run (PokemonEntity pokemon, Identifier id) {
        EffectsData.get(pokemon.getRegistryManager(), id).ifPresent(reference -> reference.value().run(pokemon));
    }

    public static void run (PokemonEntity pokemon, @Nullable PokemonEntity other, PokemonBattle battle, Identifier id) {
        EffectsData.get(pokemon.getRegistryManager(), id).ifPresent(reference -> reference.value().run(pokemon, other, battle));
    }

    public void run (PokemonEntity pokemon) {
        this.particles.ifPresent(particleAnimation -> particleAnimation.runParticles(pokemon, null));
        this.pokemonAnimation.ifPresent(animation -> this.playAnimation(pokemon, animation));
        this.sound.ifPresent(pokemon::playSound);
    }

    public void run (PokemonEntity pokemon, @Nullable PokemonEntity other, PokemonBattle battle) {
        this.particles.ifPresent(particleAnimation -> particleAnimation.runParticles(pokemon, other));
        this.sound.ifPresent(pokemon::playSound);
        this.pokemonAnimation.ifPresent(animation -> battle.dispatchToFront(battle1 -> {
            this.playAnimation(pokemon, animation);
            return new UntilDispatch(() -> true);
        }));
        this.formChangeDelay.ifPresent(delay -> battle.dispatchWaitingToFront(delay, () -> Unit.INSTANCE));
    }

    private void playAnimation (PokemonEntity pokemon, String animationName) {
        PlayPosableAnimationPacket packet = new PlayPosableAnimationPacket(pokemon.getId(), Set.of(animationName), List.of());
        packet.sendToPlayersAround(
            pokemon.getX(),
            pokemon.getY(),
            pokemon.getZ(),
            128,
            pokemon.getWorld().getRegistryKey(),
            player -> false
        );
    }
}
