package com.provismet.cobblemon.gimmick.api.data.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.api.data.MegaEvolution;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a complete megastone item.
 *
 * @param itemData The base item data.
 * @param megaData The mega evolution applied by this item.
 * @param showdownId The Showdown id used by this item.
 */
public record MegaStone (DataItem itemData, MegaEvolution megaData, String showdownId) implements DataItemStack {
    public static final Codec<MegaStone> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        DataItem.CODEC.fieldOf("item").forGetter(MegaStone::itemData),
        MegaEvolution.CODEC.fieldOf("mega").forGetter(MegaStone::megaData),
        Codec.STRING.fieldOf("showdownId").forGetter(MegaStone::showdownId)
    ).apply(instance, MegaStone::new));

    @Override
    @Nullable
    public ItemStack create () {
        ItemStack stack = this.itemData.create();
        if (stack == null) return null;

        stack.set(GTGItemDataComponents.MEGA_EVOLUTION, this.megaData);
        stack.set(GTGItemDataComponents.SHOWDOWN_ID, this.showdownId);
        return stack;
    }
}
