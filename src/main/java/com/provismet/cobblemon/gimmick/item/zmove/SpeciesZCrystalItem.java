package com.provismet.cobblemon.gimmick.item.zmove;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

/**
 * Empty class representing exclusive z-crystals, provided for mixin convenience.
 */
public class SpeciesZCrystalItem extends TypedZCrystalItem {
    public SpeciesZCrystalItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, ElementalType type) {
        super(settings, baseVanillaItem, modelData, type);
    }

    public SpeciesZCrystalItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines, ElementalType type) {
        super(settings, baseVanillaItem, modelData, tooltipLines, type);
    }

    @Override
    public void giveToPokemon (Pokemon pokemon) {

    }

    @Override
    public void removeFromPokemon (Pokemon pokemon) {

    }
}
