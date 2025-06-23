package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractFormChangeToggleItem extends PolymerPokemonSelectingItem {
    public AbstractFormChangeToggleItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    public AbstractFormChangeToggleItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(pokemon)) return TypedActionResult.fail(stack);

        if (this.shouldApplySpecialForm(pokemon)) this.applySpecialForm(player, pokemon);
        else this.removeSpecialForm(player, pokemon);

        this.postFormChange(player, stack, pokemon);
        return TypedActionResult.success(stack);
    }

    protected abstract boolean shouldApplySpecialForm (Pokemon pokemon);
    protected abstract void applySpecialForm (ServerPlayerEntity player, Pokemon pokemon);
    protected abstract void removeSpecialForm (ServerPlayerEntity player, Pokemon pokemon);

    protected void postFormChange (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {

    }
}
