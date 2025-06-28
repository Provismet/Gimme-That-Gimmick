package com.provismet.cobblemon.gimmick.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import net.minecraft.util.Identifier;

public class PowerSpotBlock extends GenericPolymerTexturedBlock {
    public PowerSpotBlock(Settings settings, Identifier id) {
        super(settings, id, BlockModelType.FULL_BLOCK);
    }
}
