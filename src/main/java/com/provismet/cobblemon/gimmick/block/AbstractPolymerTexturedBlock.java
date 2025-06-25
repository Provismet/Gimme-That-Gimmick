package com.provismet.cobblemon.gimmick.block;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

public class AbstractPolymerTexturedBlock extends Block implements PolymerTexturedBlock {
    private final BlockState model;

    public AbstractPolymerTexturedBlock (AbstractBlock.Settings settings, Identifier id, BlockModelType modelType) {
        super(settings);
        this.model = PolymerBlockResourceUtils.requestBlock(modelType, PolymerBlockModel.of(id.withPrefixedPath("block/")));
    }

    @Override
    public BlockState getPolymerBlockState (BlockState blockState) {
        return this.model;
    }
}
