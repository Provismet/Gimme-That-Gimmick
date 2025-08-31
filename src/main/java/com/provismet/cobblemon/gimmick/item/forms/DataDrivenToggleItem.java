package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.component.FormToggle;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.NotNull;

public class DataDrivenToggleItem extends AbstractDataDrivenFormItem implements FormChangeToggleItem {
    public DataDrivenToggleItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    @Override
    public boolean shouldApplySpecialForm (ItemStack stack, Pokemon pokemon) {
        FormToggle toggle = stack.get(GTGItemDataComponents.FORM_TOGGLE);
        if (toggle != null) {
            return toggle.shouldApply().matches(pokemon);
        }
        return false;
    }

    @Override
    public void applySpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        FormToggle toggle = stack.get(GTGItemDataComponents.FORM_TOGGLE);
        if (toggle != null) {
            toggle.onApply().apply(pokemon);

            if (pokemon.getEntity() != null && toggle.effects().isPresent()) {
                EffectsData.run(pokemon.getEntity(), toggle.effects().get());
            }
        }
    }

    @Override
    public void removeSpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        FormToggle toggle = stack.get(GTGItemDataComponents.FORM_TOGGLE);
        if (toggle != null) {
            toggle.onRemove().apply(pokemon);

            if (pokemon.getEntity() != null && toggle.effects().isPresent()) {
                EffectsData.run(pokemon.getEntity(), toggle.effects().get());
            }
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        FormToggle toggle = stack.get(GTGItemDataComponents.FORM_TOGGLE);
        if (toggle != null) {
            return toggle.validPokemon().matches(pokemon);
        }
        return false;
    }
}
