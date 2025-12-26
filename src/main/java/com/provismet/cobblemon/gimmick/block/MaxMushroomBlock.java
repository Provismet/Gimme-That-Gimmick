package com.provismet.cobblemon.gimmick.block;

import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.util.tag.GTGBlockTags;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class MaxMushroomBlock extends CropBlock implements PolymerTexturedBlock {
    public static final int MAX_AGE = 3;

    private final BlockState model0;
    private final BlockState model1;
    private final BlockState model2;
    private final BlockState model3;

    public MaxMushroomBlock (Settings settings, Identifier id) {
        super(settings);
        this.model0 = PolymerBlockResourceUtils.requestBlock(BlockModelType.PLANT_BLOCK, PolymerBlockModel.of(id.withPrefixedPath("block/").withSuffixedPath("_0")));
        this.model1 = PolymerBlockResourceUtils.requestBlock(BlockModelType.PLANT_BLOCK, PolymerBlockModel.of(id.withPrefixedPath("block/").withSuffixedPath("_1")));
        this.model2 = PolymerBlockResourceUtils.requestBlock(BlockModelType.PLANT_BLOCK, PolymerBlockModel.of(id.withPrefixedPath("block/").withSuffixedPath("_2")));
        this.model3 = PolymerBlockResourceUtils.requestBlock(BlockModelType.PLANT_BLOCK, PolymerBlockModel.of(id.withPrefixedPath("block/").withSuffixedPath("_3")));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    protected void randomTick (BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (state.get(AGE) < MAX_AGE && world.getLightLevel(pos.up()) >= 9 && random.nextInt(5) == 0) {
            world.setBlockState(pos, state.with(AGE, state.get(AGE) + 1), Block.NOTIFY_ALL);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public boolean isFertilizable (WorldView world, BlockPos pos, BlockState state) {
        return state.get(AGE) < MAX_AGE;
    }

    @Override
    protected int getGrowthAmount (World world) {
        return 1;
    }

    @Override
    public boolean canPlaceAt (BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(GTGBlockTags.MAX_MUSHROOM_PLANTABLE);
    }

    @Override
    public ItemStack getPickStack (WorldView world, BlockPos pos, BlockState state) {
        return new ItemStack(GTGItems.MAX_MUSHROOM);
    }

    @Override
    public BlockState getPolymerBlockState (BlockState state) {
        int age = state.get(AGE);
        if (age == 0) return this.model0;
        if (age == 1) return this.model1;
        if (age == 2) return this.model2;
        return this.model3;
    }

    @Override
    public int getAge (BlockState state) {
        return state.get(AGE);
    }

    @Override
    public int getMaxAge () {
        return MAX_AGE;
    }
}
