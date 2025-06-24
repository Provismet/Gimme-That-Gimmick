package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public class NLunarizerItem extends AbstractFormChangeFusionItem {
    public NLunarizerItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    protected boolean canBeMerged (Pokemon other) {
        return other.getSpecies().getResourceIdentifier().toString().equals("cobblemon:lunala");
    }

    @Override
    protected void applyUnplitForme (Pokemon pokemon) {
        new StringSpeciesFeature("prism_fusion", "none").apply(pokemon);
    }

    @Override
    protected void applyFusedForme (Pokemon pokemon, Pokemon other) {
        new StringSpeciesFeature("prism_fusion", "dawn").apply(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:necrozma");
    }
}
