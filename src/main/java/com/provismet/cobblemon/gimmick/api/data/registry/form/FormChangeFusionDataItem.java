package com.provismet.cobblemon.gimmick.api.data.registry.form;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.api.data.component.Fusion;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public record FormChangeFusionDataItem (DataItem itemData, Fusion fusion) implements DataItemStack {
    public static final Codec<FormChangeFusionDataItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(FormChangeFusionDataItem::itemData),
        Fusion.CODEC.fieldOf("fusion").forGetter(FormChangeFusionDataItem::fusion)
    ).apply(instance, FormChangeFusionDataItem::new));

    public static RegistryKey<FormChangeFusionDataItem> key (Identifier id) {
        return RegistryKey.of(GTGDynamicRegistryKeys.FUSION, id);
    }

    @Override
    public String name () {
        return this.itemData.name();
    }

    @Override
    public ItemStack create () {
        ItemStack stack = GTGItems.DATA_DRIVEN_FUSION.getDefaultStack();
        stack.set(GTGItemDataComponents.FUSION, this.fusion);
        stack.set(GTGItemDataComponents.DATA_ITEM, this.itemData);
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.of(this.itemData.name()));
        return stack;
    }
}
