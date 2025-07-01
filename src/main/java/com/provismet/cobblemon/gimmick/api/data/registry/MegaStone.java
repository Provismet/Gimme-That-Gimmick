package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.api.data.MegaEvolution;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

public record MegaStone (DataItem itemData, MegaEvolution megaEvolution, String showdownId) implements DataItemStack {
    public static final Codec<MegaStone> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(MegaStone::itemData),
        MegaEvolution.CODEC.fieldOf("megaEvolution").forGetter(MegaStone::megaEvolution),
        Codecs.NON_EMPTY_STRING.fieldOf("showdownId").forGetter(MegaStone::showdownId)
    ).apply(instance, MegaStone::new));

    public static RegistryKey<MegaStone> key (Identifier id) {
        return RegistryKey.of(GTGDynamicRegistryKeys.MEGASTONE, id);
    }

    @Override
    public String name () {
        return this.itemData.name();
    }

    @Override
    @Nullable
    public ItemStack create () {
        ItemStack stack = this.itemData.create();
        if (stack != null) {
            stack.set(GTGItemDataComponents.MEGA_EVOLUTION, this.megaEvolution);
            stack.set(GTGItemDataComponents.SHOWDOWN_ID, this.showdownId);
        }

        return stack;
    }
}