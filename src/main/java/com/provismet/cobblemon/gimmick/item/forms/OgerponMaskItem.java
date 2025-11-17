package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;

public class OgerponMaskItem extends GenericFormChangeHeldItem {
    private static final String FEATURE = "ogre_mask";
    private final TeraType appliedTera;

    public OgerponMaskItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, CustomPokemonProperty apply, CustomPokemonProperty remove, TeraType teraType) {
        super(settings, baseVanillaItem, modelData, 1, MiscUtilsKt.cobblemonResource("ogerpon"), apply, remove);
        this.appliedTera = teraType;
    }

    public OgerponMaskItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, String maskAspect, TeraType teraType) {
        this(settings, baseVanillaItem, modelData, new StringSpeciesFeature(FEATURE, maskAspect), new StringSpeciesFeature(FEATURE, "teal"), teraType);
    }

    @Override
    public void giveToPokemon (Pokemon pokemon) {
        super.giveToPokemon(pokemon);
        pokemon.setTeraType(this.appliedTera);
    }

    @Override
    public void removeFromPokemon (Pokemon pokemon) {
        super.removeFromPokemon(pokemon);
        pokemon.setTeraType(TeraTypes.getGRASS());
    }
}
