package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class DNASplicersItem extends PolymerPokemonSelectingItem implements FormChangeFusionItem {
    private static final String FEATURE = "absofusion";
    private static final Set<String> DRAGONS = Set.of("cobblemon:reshiram", "cobblemon:zekrom");

    public DNASplicersItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean canBeMerged (ItemStack stack, Pokemon other) {
        return DRAGONS.contains(other.getSpecies().getResourceIdentifier().toString());
    }

    @Override
    public void applyUnplitForme (ItemStack stack, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "none").apply(pokemon);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("dna_splicers_unfuse"));
        }
    }

    @Override
    public void applyFusedForme (ItemStack stack, Pokemon pokemon, Pokemon other) {
        String forme = other.getSpecies().getResourceIdentifier().getPath().equals("zekrom") ? "black" : "white";
        new StringSpeciesFeature(FEATURE, forme).apply(pokemon);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("dna_splicers_fuse"));
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:kyurem");
    }
}
