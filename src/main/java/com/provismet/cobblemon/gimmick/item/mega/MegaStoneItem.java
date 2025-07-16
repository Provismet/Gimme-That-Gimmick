package com.provismet.cobblemon.gimmick.item.mega;

import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

/**
 * Empty class representing mega stones, provided for mixin convenience.
 */
public class MegaStoneItem extends PolymerHeldItem {
    public MegaStoneItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        this(settings, baseVanillaItem, modelData, 1);
    }

    public MegaStoneItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
    }
}
