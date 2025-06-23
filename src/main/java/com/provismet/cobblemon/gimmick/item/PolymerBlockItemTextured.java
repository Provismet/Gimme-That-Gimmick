package com.provismet.cobblemon.gimmick.item;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.Nullable;

public class PolymerBlockItemTextured extends PolymerBlockItem {
    private final PolymerModelData model;

    public PolymerBlockItemTextured (Block block, Settings settings, Item virtualItem, PolymerModelData model) {
        super(block, settings, virtualItem);
        this.model = model;
    }

    @Override
    public int getPolymerCustomModelData (ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.model.value();
    }

    @Override
    public ActionResult useOnBlock (ItemUsageContext context) {
        ActionResult superResult = super.useOnBlock(context);
        if (superResult == ActionResult.CONSUME) {
            context.getWorld().playSound(
                null,
                context.getBlockPos().getX(),
                context.getBlockPos().getY(),
                context.getBlockPos().getZ(),
                this.getPlaceSound(this.getBlock().getDefaultState()),
                SoundCategory.BLOCKS,
                1f, 1f,
                context.getPlayer().getRandom().nextLong()
            );
            return ActionResult.SUCCESS;
        }
        return superResult;
    }
}
