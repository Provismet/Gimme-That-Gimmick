package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

public record HeldItem (DataItem itemData, String showdownId) implements DataItemStack {
    public static final Codec<HeldItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(HeldItem::itemData),
        Codecs.NON_EMPTY_STRING.fieldOf("showdownId").forGetter(HeldItem::showdownId)
    ).apply(instance, HeldItem::new));

    public static RegistryKey<HeldItem> key (Identifier id) {
        return RegistryKey.of(GTGDynamicRegistryKeys.HELD_ITEM, id);
    }

    @Override
    @Nullable
    public ItemStack create () {
        ItemStack stack = this.itemData.create();
        if (stack != null) {
            stack.set(GTGItemDataComponents.SHOWDOWN_ID, this.showdownId);
            stack.remove(DataComponentTypes.FOOD);
        }

        return stack;
    }
}