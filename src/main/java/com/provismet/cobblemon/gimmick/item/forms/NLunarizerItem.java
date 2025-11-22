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

public class NLunarizerItem extends PolymerPokemonSelectingItem implements FormChangeFusionItem {
    public NLunarizerItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean canBeMerged (ItemStack stack, Pokemon other) {
        return other.getSpecies().getResourceIdentifier().toString().equals("cobblemon:lunala");
    }

    @Override
    public void applyUnplitForme (ItemStack stack, Pokemon pokemon) {
        new StringSpeciesFeature("prism_fusion", "none").apply(pokemon);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("n_lunarizer_unfuse"));
        }
    }

    @Override
    public void applyFusedForme (ItemStack stack,Pokemon pokemon, Pokemon other) {
        new StringSpeciesFeature("prism_fusion", "dawn").apply(pokemon);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("n_lunarizer_fuse"));
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:necrozma");
    }
}
