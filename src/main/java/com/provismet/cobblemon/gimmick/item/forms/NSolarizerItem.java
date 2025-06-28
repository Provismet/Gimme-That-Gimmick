package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

public class NSolarizerItem extends PolymerPokemonSelectingItem implements FormChangeFusionItem {
    public NSolarizerItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean canBeMerged (Pokemon other) {
        return other.getSpecies().getResourceIdentifier().toString().equals("cobblemon:solgaleo");
    }

    @Override
    public void applyUnplitForme (Pokemon pokemon) {
        new StringSpeciesFeature("prism_fusion", "none").apply(pokemon);
    }

    @Override
    public void applyFusedForme (Pokemon pokemon, Pokemon other) {
        new StringSpeciesFeature("prism_fusion", "dusk").apply(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:necrozma");
    }
}
