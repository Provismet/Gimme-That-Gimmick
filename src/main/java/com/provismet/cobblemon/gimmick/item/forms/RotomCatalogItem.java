package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class RotomCatalogItem extends PolymerPokemonSelectingItem implements FormChangeSelectionItem {
    public RotomCatalogItem(Settings settings, Item polymerItem, PolymerModelData modelData) {
        super(settings, polymerItem, modelData, 1);
    }

    @Override
    public boolean canUseOnPokemon(@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:rotom");
    }

    @Override
    public void applyForm(ServerPlayerEntity player, Pokemon pokemon, FormData form) {
        String appliance = (form == null || form.formOnlyShowdownId().equals("normal")) ? "none" : form.formOnlyShowdownId();
        new StringSpeciesFeature("appliance", appliance).apply(pokemon);
    }
}
