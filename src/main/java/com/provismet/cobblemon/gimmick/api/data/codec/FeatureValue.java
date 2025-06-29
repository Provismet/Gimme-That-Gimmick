package com.provismet.cobblemon.gimmick.api.data.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;

// TODO: Try and find a way to let the feature json use primitive strings or primitive booleans.
public class FeatureValue {
    public static final Codec<FeatureValue> CODEC = new Codec<>() {
        @Override
        public <T> DataResult<Pair<FeatureValue, T>> decode (DynamicOps<T> ops, T input) {
            return null;
        }

        @Override
        public <T> DataResult<T> encode (FeatureValue input, DynamicOps<T> ops, T prefix) {
            return null;
        }
    };

    public final boolean isString;
    public final boolean isInt;
    public final boolean isBool;

    private final Object value;

    public FeatureValue (String value) {
        this.value = value;
        this.isString = true;
        this.isInt = false;
        this.isBool = false;
    }

    public FeatureValue (int value) {
        this.value = value;
        this.isString = false;
        this.isInt = true;
        this.isBool = false;
    }

    public FeatureValue (boolean value) {
        this.value = value;
        this.isString = false;
        this.isInt = false;
        this.isBool = true;
    }

    public String getString () {
        if (this.value instanceof String stringValue) return stringValue;
        return "";
    }
}
