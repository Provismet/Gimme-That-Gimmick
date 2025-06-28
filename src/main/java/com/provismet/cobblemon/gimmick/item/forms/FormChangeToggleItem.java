package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FormChangeToggleItem extends PokemonSelectingItem {
    @Nullable
    @Override
    default TypedActionResult<ItemStack> applyToPokemon(@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(pokemon)) return TypedActionResult.fail(stack);

        if (this.shouldApplySpecialForm(pokemon)) this.applySpecialForm(player, pokemon);
        else this.removeSpecialForm(player, pokemon);

        this.postFormChange(player, stack, pokemon);
        return TypedActionResult.success(stack);
    }

    boolean shouldApplySpecialForm(Pokemon pokemon);

    void applySpecialForm(ServerPlayerEntity player, Pokemon pokemon);

    void removeSpecialForm(ServerPlayerEntity player, Pokemon pokemon);

    default void postFormChange(ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {

    }
}
