package com.provismet.cobblemon.gimmick.util.tag;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public abstract class GTGBlockTags {
    public static final TagKey<Block> POWER_SPOTS = GTGBlockTags.of("power_spots");
    public static final TagKey<Block> MAX_MUSHROOM_PLANTABLE = GTGBlockTags.of("max_mushroom_plantable");

    private static TagKey<Block> of (String name) {
        return TagKey.of(RegistryKeys.BLOCK, GimmeThatGimmickMain.identifier(name));
    }
}
