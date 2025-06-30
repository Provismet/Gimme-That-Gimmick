package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.Fusion;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DataDrivenFusionItem extends AbstractDataDrivenFormItem implements FormChangeFusionItem {
    public DataDrivenFusionItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    @Override
    public boolean canBeMerged (ItemStack stack, Pokemon other) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            return fusionData.canBeUsedAsInput(other);
        }
        return false;
    }

    @Override
    public void applyUnplitForme (ItemStack stack, Pokemon pokemon) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            fusionData.removeFeatures(pokemon);
        }
    }

    @Override
    public void applyFusedForme (ItemStack stack, Pokemon pokemon, Pokemon other) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            fusionData.applyFeatures(pokemon, other);
        }
    }

    @Override
    public boolean canUseOnPokemon (ItemStack stack, Pokemon pokemon) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);

        if (fusionData == null) return false;
        return fusionData.recipient().matches(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return false;
    }
}
