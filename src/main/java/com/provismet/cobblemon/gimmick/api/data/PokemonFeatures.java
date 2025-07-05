package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

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

    public record FeatureValue (Either<String, Either<Boolean, Integer>> value) {
        public static final Codec<FeatureValue> CODEC = Codec.either(
            Codecs.NON_EMPTY_STRING,
            Codec.either(Codec.BOOL, Codec.INT)
        ).xmap(FeatureValue::of, FeatureValue::value);

        public static final PacketCodec<RegistryByteBuf, FeatureValue> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.either(PacketCodecs.STRING, PacketCodecs.either(PacketCodecs.BOOL, PacketCodecs.INTEGER)),
            FeatureValue::value,
            FeatureValue::new
        );

        public static FeatureValue of (String value) {
            return new FeatureValue(Either.left(value));
        }

        public static FeatureValue of (boolean value) {
            return new FeatureValue(Either.right(Either.left(value)));
        }

        public static FeatureValue of (int value) {
            return new FeatureValue(Either.right(Either.right(value)));
        }

        public static FeatureValue of (Either<String, Either<Boolean, Integer>> value) {
            if (value.left().isPresent()) return FeatureValue.of(value.left().get());
            if (value.right().isPresent()) {
                if (value.right().get().left().isPresent()) return FeatureValue.of(value.right().get().left().get());
                if (value.right().get().right().isPresent()) return FeatureValue.of(value.right().get().right().get());
            }

            // This point should never be reached, and if you do then you'll get an error.
            return null;
        }
    }
}
