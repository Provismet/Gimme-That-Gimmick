package com.provismet.cobblemon.gimmick.api.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.codec.EffectsData;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.Optional;

public record FormToggle (PokemonRequirements validPokemon, PokemonRequirements shouldApply, PokemonFeatures onApply, PokemonFeatures onRemove, Optional<EffectsData> effects) {
    public static final Codec<FormToggle> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PokemonRequirements.CODEC.fieldOf("validPokemon").forGetter(FormToggle::validPokemon),
        PokemonRequirements.CODEC.fieldOf("applyIf").forGetter(FormToggle::shouldApply),
        PokemonFeatures.CODEC.fieldOf("featuresOnApply").forGetter(FormToggle::onApply),
        PokemonFeatures.CODEC.fieldOf("featuresOnRemove").forGetter(FormToggle::onRemove),
        EffectsData.CODEC.optionalFieldOf("effects").forGetter(FormToggle::effects)
    ).apply(instance, FormToggle::new));

    public static final PacketCodec<RegistryByteBuf, FormToggle> PACKET_CODEC = PacketCodec.tuple(
        PokemonRequirements.PACKET_CODEC,
        FormToggle::validPokemon,
        PokemonRequirements.PACKET_CODEC,
        FormToggle::shouldApply,
        PokemonFeatures.PACKET_CODEC,
        FormToggle::onApply,
        PokemonFeatures.PACKET_CODEC,
        FormToggle::onRemove,
        PacketCodecs.optional(EffectsData.PACKET_CODEC),
        FormToggle::effects,
        FormToggle::new
    );
}
