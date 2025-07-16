package com.provismet.cobblemon.gimmick.item.zmove;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.provismet.cobblemon.gimmick.item.forms.GenericFormChangeHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

/**
 * Semi-empty class representing non-exclusive z-crystals, provided for mixin convenience.
 */
public class TypedZCrystalItem extends GenericFormChangeHeldItem {
    public final ElementalType type;

    public TypedZCrystalItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, ElementalType type) {
        this(settings, baseVanillaItem, modelData, 1, type);
    }

    public TypedZCrystalItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines, ElementalType type) {
        super(settings, baseVanillaItem, modelData, tooltipLines, MiscUtilsKt.cobblemonResource("arceus"), new StringSpeciesFeature("multitype", type.getName()), new StringSpeciesFeature("multitype", "normal"));
        this.type = type;
    }
}
