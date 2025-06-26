package com.provismet.cobblemon.gimmick.item;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PolymerHeldItem extends SimplePolymerItem implements NumericalTooltipItem {
    private final PolymerModelData modelData;
    private final int tooltipLines;

    public PolymerHeldItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        this(settings, baseVanillaItem, modelData, 0);
    }

    public PolymerHeldItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem);
        this.modelData = modelData;
        this.tooltipLines = tooltipLines;
    }

    @Override
    public int getPolymerCustomModelData (ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelData.value();
    }

    @Override
    public void appendTooltip (ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        for (int i = 1; i <= this.tooltipLines; ++i) {
            tooltip.add(Text.translatable(this.getTooltipTranslationKey(i)).formatted(Formatting.GRAY));
        }
    }
}
