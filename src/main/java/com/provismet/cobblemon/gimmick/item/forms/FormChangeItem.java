package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.advancement.CobblemonCriteria;
import com.cobblemon.mod.common.advancement.criterion.PokemonInteractContext;
import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.ItemStackExtensionsKt;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface FormChangeItem extends PokemonSelectingItem {
    @Override
    default @NotNull TypedActionResult<ItemStack> interactGeneral (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        List<Pokemon> partyList = CollectionsKt.toList(PlayerExtensionsKt.party(player));
        if (partyList.isEmpty()) return TypedActionResult.fail(itemStack);

        PartySelectCallbacks.INSTANCE.createFromPokemon(
            player,
            partyList,
            pokemon -> this.canUseOnPokemon(itemStack, pokemon),
            pokemon -> {
                if (ItemStackExtensionsKt.isHeld(itemStack, player)) {
                    this.applyToPokemon(player, itemStack, pokemon);
                    CobblemonCriteria.POKEMON_INTERACT.trigger(player, new PokemonInteractContext(pokemon.getSpecies().resourceIdentifier, Registries.ITEM.getId(itemStack.getItem())));
                }
                return Unit.INSTANCE;
            }
        );

        return TypedActionResult.success(itemStack);
    }
}
