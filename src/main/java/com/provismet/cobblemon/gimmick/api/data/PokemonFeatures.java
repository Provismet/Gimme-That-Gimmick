package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.Map;

// TODO: Ideally this should be able to take species features outright, or at least be able to take booleans natively.
public record PokemonFeatures (Map<String, String> featureMap) {
    public static final Codec<PokemonFeatures> CODEC = Codec.unboundedMap(Codec.STRING, Codec.STRING).xmap(PokemonFeatures::new, PokemonFeatures::featureMap);

    public static final PacketCodec<RegistryByteBuf, PokemonFeatures> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.map(Object2ObjectOpenHashMap::new, PacketCodecs.STRING, PacketCodecs.STRING),
        PokemonFeatures::featureMap,
        PokemonFeatures::new
    );

    public static PokemonFeatures single (String name, String value) {
        return new PokemonFeatures(Map.of(name, value));
    }

    public static PokemonFeatures single (String name, boolean value) {
        return new PokemonFeatures(Map.of(name, String.valueOf(value)));
    }

    public void apply (Pokemon pokemon) {
        for (Map.Entry<String, String> feature : this.featureMap.entrySet()) {
            if (feature.getValue().equals("true") || feature.getValue().equals("false")) {
                new FlagSpeciesFeature(feature.getKey(), Boolean.parseBoolean(feature.getValue())).apply(pokemon);
            }
            else {
                new StringSpeciesFeature(feature.getKey(), feature.getValue()).apply(pokemon);
            }
        }
    }
}
