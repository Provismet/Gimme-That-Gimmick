package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FormChangeToggleItem extends FormChangeItem {
    @Nullable
    @Override
    default TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(stack, pokemon)) return TypedActionResult.fail(stack);

        if (this.shouldApplySpecialForm(stack, pokemon)) this.applySpecialForm(stack, player, pokemon);
        else this.removeSpecialForm(stack, player, pokemon);

        this.postFormChange(player, stack, pokemon);
        return TypedActionResult.success(stack);
    }

    boolean shouldApplySpecialForm (ItemStack stack, Pokemon pokemon);
    void applySpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon);
    void removeSpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon);

    default void postFormChange (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {

    }
}
