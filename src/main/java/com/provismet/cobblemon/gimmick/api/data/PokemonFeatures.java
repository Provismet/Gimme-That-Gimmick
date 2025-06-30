package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.provismet.cobblemon.gimmick.api.data.codec.FeatureValue;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.Map;

public record PokemonFeatures (Map<String, FeatureValue> featureMap) {
    public static final Codec<PokemonFeatures> CODEC = Codec.unboundedMap(Codec.STRING, FeatureValue.CODEC).xmap(PokemonFeatures::new, PokemonFeatures::featureMap);

    public static final PacketCodec<RegistryByteBuf, PokemonFeatures> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.map(Object2ObjectOpenHashMap::new, PacketCodecs.STRING, FeatureValue.PACKET_CODEC),
        PokemonFeatures::featureMap,
        PokemonFeatures::new
    );

    public static PokemonFeatures single (String name, String value) {
        return new PokemonFeatures(Map.of(name, FeatureValue.of(value)));
    }

    public static PokemonFeatures single (String name, boolean value) {
        return new PokemonFeatures(Map.of(name, FeatureValue.of(value)));
    }

    public static PokemonFeatures single (String name, int value) {
        return new PokemonFeatures(Map.of(name, FeatureValue.of(value)));
    }

    public void apply (Pokemon pokemon) {
        for (Map.Entry<String, FeatureValue> feature : this.featureMap.entrySet()) {
            feature.getValue().value()
                .ifLeft(string -> new StringSpeciesFeature(feature.getKey(), string).apply(pokemon))
                .ifRight(boolOrInt -> {
                    boolOrInt.ifLeft(bool -> new FlagSpeciesFeature(feature.getKey(), bool).apply(pokemon));
                    boolOrInt.ifRight(integer -> new IntSpeciesFeature(feature.getKey(), integer).apply(pokemon));
                });
        }
    }
}
