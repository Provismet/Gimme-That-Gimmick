package com.provismet.cobblemon.gimmick.api.data.codec;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;

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
