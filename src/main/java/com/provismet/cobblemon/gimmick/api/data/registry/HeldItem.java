package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Represents regular Showdown held items, adding only a showdown id to the existing item.
 *
 * @param itemData The custom data-driven item.
 * @param showdownId The Showdown id used by this item.
 */
public record HeldItem (DataItem itemData, String showdownId) implements DataItemStack {
    public static final Codec<HeldItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(HeldItem::itemData),
        Codec.STRING.fieldOf("showdownId").forGetter(HeldItem::showdownId)
    ).apply(instance, HeldItem::new));

    @Override
    @Nullable
    public ItemStack create () {
        ItemStack stack = this.itemData.create();
        if (stack == null) return null;

        stack.set(GTGItemDataComponents.SHOWDOWN_ID, this.showdownId);
        return stack;
    }
}
