package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;

public record MegaStone(
        String gtg_id,
        String showdown_id,
        String item_id,
        String item_name,
        String pokemon,
        List<String> required_aspects,
        List<String> blacklist_aspects,
        List<String> item_description,
        List<String> aspects,
        Integer custom_model_data
) {
    public static final Codec<MegaStone> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("msd_id").forGetter(MegaStone::gtg_id),
            Codec.STRING.fieldOf("showdown_id").forGetter(MegaStone::showdown_id),
            Codec.STRING.fieldOf("item_id").forGetter(MegaStone::item_id),
            Codec.STRING.fieldOf("item_name").forGetter(MegaStone::item_name),
            Codec.STRING.fieldOf("pokemon").forGetter(MegaStone::pokemon),
            Codec.list(Codec.STRING).optionalFieldOf("required_aspects", List.of()).forGetter(MegaStone::required_aspects),
            Codec.list(Codec.STRING).optionalFieldOf("blacklist_aspects", List.of()).forGetter(MegaStone::blacklist_aspects),
            Codec.list(Codec.STRING).fieldOf("item_description").forGetter(MegaStone::item_description),
            Codec.list(Codec.STRING).fieldOf("aspects").forGetter(MegaStone::aspects),
            Codec.INT.fieldOf("custom_model_data").forGetter(MegaStone::custom_model_data)
    ).apply(instance, MegaStone::new));
}