package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DNASplicersItem extends AbstractFormChangeFusionItem {
    private static final String FEATURE = "absofusion";
    private static final Set<String> DRAGONS = Set.of("cobblemon:reshiram", "cobblemon:zekrom");

    public DNASplicersItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    protected boolean canBeMerged (Pokemon other) {
        return DRAGONS.contains(other.getSpecies().getResourceIdentifier().toString());
    }

    @Override
    protected void applyUnplitForme (Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "none").apply(pokemon);
    }

    @Override
    protected void applyFusedForme (Pokemon pokemon, Pokemon other) {
        String forme = other.getSpecies().getResourceIdentifier().getPath().equals("zekrom") ? "black" : "white";
        new StringSpeciesFeature(FEATURE, forme).apply(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:kyurem");
    }
}
