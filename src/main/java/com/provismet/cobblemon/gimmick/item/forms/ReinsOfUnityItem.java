package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class ReinsOfUnityItem extends PolymerPokemonSelectingItem implements FormChangeFusionItem {
    private static final String FEATURE = "king_steed";
    private static final Set<String> HORSES = Set.of("cobblemon:glastrier", "cobblemon:spectrier");

    public ReinsOfUnityItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean canBeMerged (Pokemon other) {
        return HORSES.contains(other.getSpecies().getResourceIdentifier().toString());
    }

    @Override
    public void applyUnplitForme (Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "none").apply(pokemon);
    }

    @Override
    public void applyFusedForme (Pokemon pokemon, Pokemon other) {
        String forme = other.getSpecies().getResourceIdentifier().getPath().equals("glastrier") ? "ice" : "shadow";
        new StringSpeciesFeature(FEATURE, forme).apply(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:calyrex");
    }
}
