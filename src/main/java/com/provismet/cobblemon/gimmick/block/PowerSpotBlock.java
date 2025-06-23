package com.provismet.cobblemon.gimmick.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class PowerSpotBlock extends Block implements PolymerTexturedBlock {
    private final BlockState model;

    public PowerSpotBlock(Settings settings, Identifier id) {
        super(settings);
        this.model = PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(id.withPrefixedPath("block/")));
    }

    @Override
    public BlockState getPolymerBlockState(BlockState blockState) {
        return this.model;
    }
}
