package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class MeteoriteItem extends AbstractFormChangeSelectionItem {
    public MeteoriteItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    protected void applyForm (ServerPlayerEntity player, Pokemon pokemon, FormData form) {
        String formName = form == null ? "normal" : form.formOnlyShowdownId();
        new StringSpeciesFeature("meteorite_forme", formName).apply(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:deoxys");
    }
}
