package com.provismet.cobblemon.gimmick.item.zmove;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

/**
 * Semi-empty class representing non-exclusive z-crystals, provided for mixin convenience.
 */
public class TypedZCrystalItem extends PolymerHeldItem {
    public final ElementalType type;

    public TypedZCrystalItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData, ElementalType type) {
        this(settings, baseVanillaItem, modelData, 2, type);
    }

    public TypedZCrystalItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines, ElementalType type) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
        this.type = type;
    }
}
