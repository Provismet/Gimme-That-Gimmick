package com.provismet.cobblemon.gimmick.api.data.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.PokemonRequirements;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

import java.util.Optional;

/**
 * A data component used exclusively by the {@link com.provismet.cobblemon.gimmick.item.forms.DataDrivenToggleItem} item.
 * <p>
 * Toggles the pokemon between two forms. The fields are labelled under the assumption that the Pokémon as a default state
 * and a special state, but this is not necessary in practice.
 *
 * @param validPokemon Checks if a given Pokémon is valid to have its form toggled.
 * @param shouldApply Checks if the Pokémon is in its default state. If true the onApply features are used, otherwise the onRemove features are used.
 * @param onApply Features applied to put the Pokémon in its special state.
 * @param onRemove Features applied to put the Pokémon in its default state.
 * @param effects Optional identifier of an EffectsData object.
 */
public record FormToggle (PokemonRequirements validPokemon, PokemonRequirements shouldApply, PokemonFeatures onApply, PokemonFeatures onRemove, Optional<Identifier> effects) {
    public static final Codec<FormToggle> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PokemonRequirements.CODEC.fieldOf("validPokemon").forGetter(FormToggle::validPokemon),
        PokemonRequirements.CODEC.fieldOf("applyIf").forGetter(FormToggle::shouldApply),
        PokemonFeatures.CODEC.fieldOf("featuresOnApply").forGetter(FormToggle::onApply),
        PokemonFeatures.CODEC.fieldOf("featuresOnRemove").forGetter(FormToggle::onRemove),
        Identifier.CODEC.optionalFieldOf("effects").forGetter(FormToggle::effects)
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
        PacketCodecs.optional(Identifier.PACKET_CODEC),
        FormToggle::effects,
        FormToggle::new
    );
}
