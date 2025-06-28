package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record HeldItem(
        String gtg_id,
        String showdown_id,
        String item_id,
        String item_name,
        List<String> item_description,
        Integer custom_model_data
) {
    public static final Codec<HeldItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("gtg_id").forGetter(HeldItem::gtg_id),
            Codec.STRING.fieldOf("showdown_id").forGetter(HeldItem::showdown_id),
            Codec.STRING.fieldOf("item_id").forGetter(HeldItem::item_id),
            Codec.STRING.fieldOf("item_name").forGetter(HeldItem::item_name),
            Codec.list(Codec.STRING).fieldOf("item_description").forGetter(HeldItem::item_description),
            Codec.INT.fieldOf("custom_model_data").forGetter(HeldItem::custom_model_data)
    ).apply(instance, HeldItem::new));
}