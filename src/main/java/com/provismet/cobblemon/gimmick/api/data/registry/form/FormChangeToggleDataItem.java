package com.provismet.cobblemon.gimmick.api.data.registry.form;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.api.data.component.FormToggle;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public record FormChangeToggleDataItem (DataItem itemData, FormToggle formToggle) implements DataItemStack {
    public static final Codec<FormChangeToggleDataItem> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(FormChangeToggleDataItem::itemData),
        FormToggle.CODEC.fieldOf("form").forGetter(FormChangeToggleDataItem::formToggle)
    ).apply(instance, FormChangeToggleDataItem::new));

    public static RegistryKey<FormChangeToggleDataItem> key (Identifier id) {
        return RegistryKey.of(GTGDynamicRegistryKeys.FORM_TOGGLE, id);
    }

    @Override
    public String name () {
        return this.itemData.name();
    }

    @Override
    public ItemStack create () {
        ItemStack stack = GTGItems.DATA_DRIVEN_TOGGLE.getDefaultStack();
        stack.set(GTGItemDataComponents.FORM_TOGGLE, this.formToggle);
        stack.set(GTGItemDataComponents.DATA_ITEM, this.itemData);
        stack.set(DataComponentTypes.CUSTOM_NAME, Text.of(this.itemData.name()));
        return stack;
    }
}
