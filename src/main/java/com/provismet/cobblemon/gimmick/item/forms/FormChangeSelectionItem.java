package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kotlin.Unit;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public interface FormChangeSelectionItem extends PokemonSelectingItem {
    @Nullable
    @Override
    default TypedActionResult<ItemStack> applyToPokemon(@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(pokemon)) return TypedActionResult.fail(stack);

        List<Pokemon> forms = pokemon.getSpecies()
                .getForms()
                .stream()
                .filter(Objects::nonNull)
                .filter(form -> !form.formOnlyShowdownId().equals(pokemon.getForm().formOnlyShowdownId()))
                .map(form -> {
                    Pokemon copy = BattlePokemon.Companion.safeCopyOf(pokemon).getEffectedPokemon();
                    this.applyForm(player, copy, form);
                    copy.setNickname(Text.literal(form.getName()));
                    return copy;
                })
                .toList();

        PartySelectCallbacks.INSTANCE.createFromPokemon(
                player,
                Text.translatable("gui.title.gimme-that-gimmick.form"),
                forms,
                form -> true,
                selectedForm -> {
                    this.applyForm(player, pokemon, selectedForm.getForm());
                    this.postFormChange(player, stack, pokemon);
                    return Unit.INSTANCE;
                }
        );
        return TypedActionResult.success(stack);
    }

    void applyForm(ServerPlayerEntity player, Pokemon pokemon, FormData form);

    default void postFormChange(ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.form", pokemon.getDisplayName(), pokemon.getForm().getName()), true);
    }
}
