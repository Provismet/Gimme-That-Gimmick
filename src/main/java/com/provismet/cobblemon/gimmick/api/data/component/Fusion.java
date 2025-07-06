package com.provismet.cobblemon.gimmick.api.data.component;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.PokemonRequirements;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

/**
 * Used exclusively by the {@link com.provismet.cobblemon.gimmick.item.forms.DataDrivenFusionItem} item.
 * <p>
 * This controls the fusion data used by that item. Allowing custom fusions to take place.
 * <p>
 * The indexed fields are connected. If the requirements from index #3 are the first to match, then the features from
 * index #3 will be applied (or the last indexed feature if the feature list is shorter).
 *
 * @param recipient The Pokémon that receives the form change.
 * @param indexedInputPokemon An indexed list of PokemonRequirements for potential Pokémon to absorb.
 * @param indexedFusionFeatures An indexed list of features to grant the recipient.
 * @param defaultFeatures The features to apply to the recipient Pokémon when unfusing.
 * @param fusionEffect An optional identifier for the EffectData to trigger when fusing.
 * @param unfusionEffect An optional identifier for the EffectData to trigger when unfusing.
 *
 * @implNote The absorbed Pokémon is converted into NBT and saved inside the data of the recipient Pokémon.
 */
public record Fusion (
    PokemonRequirements recipient,
    List<PokemonRequirements> indexedInputPokemon,
    List<PokemonFeatures> indexedFusionFeatures,
    PokemonFeatures defaultFeatures,
    Optional<Identifier> fusionEffect,
    Optional<Identifier> unfusionEffect
) {
    public static final Codec<Fusion> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PokemonRequirements.CODEC.fieldOf("recipient").forGetter(Fusion::recipient),
        PokemonRequirements.CODEC.listOf().fieldOf("inputPokemon").forGetter(Fusion::indexedInputPokemon),
        PokemonFeatures.CODEC.listOf().fieldOf("inputFeatures").forGetter(Fusion::indexedFusionFeatures),
        PokemonFeatures.CODEC.fieldOf("defaultFeatures").forGetter(Fusion::defaultFeatures),
        Identifier.CODEC.optionalFieldOf("fuseEffect").forGetter(Fusion::fusionEffect),
        Identifier.CODEC.optionalFieldOf("defuseEffect").forGetter(Fusion::unfusionEffect)
    ).apply(instance, Fusion::new));

    public static final PacketCodec<RegistryByteBuf, Fusion> PACKET_CODEC = PacketCodec.tuple(
        PokemonRequirements.PACKET_CODEC,
        Fusion::recipient,
        PokemonRequirements.PACKET_CODEC.collect(PacketCodecs.toList()),
        Fusion::indexedInputPokemon,
        PokemonFeatures.PACKET_CODEC.collect(PacketCodecs.toList()),
        Fusion::indexedFusionFeatures,
        PokemonFeatures.PACKET_CODEC,
        Fusion::defaultFeatures,
        PacketCodecs.optional(Identifier.PACKET_CODEC),
        Fusion::fusionEffect,
        PacketCodecs.optional(Identifier.PACKET_CODEC),
        Fusion::unfusionEffect,
        Fusion::new
    );

    public boolean canBeUsedAsInput (Pokemon pokemon) {
        return this.indexedInputPokemon.stream().anyMatch(requirement -> requirement.matches(pokemon));
    }

    public void applyFeatures (Pokemon recipient, Pokemon input) {
        int index = 0;
        for (int i = 0; i < this.indexedInputPokemon.size(); ++i) {
            if (this.indexedInputPokemon.get(i).matches(input)) {
                index = i;
                break;
            }
        }

        PokemonFeatures features = index >= this.indexedFusionFeatures.size() ? this.indexedFusionFeatures.getLast() : this.indexedFusionFeatures.get(index);
        features.apply(recipient);
    }

    public void removeFeatures (Pokemon recipient) {
        this.defaultFeatures.apply(recipient);
    }
}
