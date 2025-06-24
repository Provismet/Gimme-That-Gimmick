package com.provismet.cobblemon.gimmick.item.dynamax;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DMaxCandyItem extends PolymerPokemonSelectingItem {
    public DMaxCandyItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 2);
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(pokemon)) return TypedActionResult.fail(itemStack);
        if (pokemon.getEntity() != null) pokemon.getEntity().playSound(CobblemonSounds.MEDICINE_CANDY_USE, 1f, 1f);

        if (pokemon.getDmaxLevel() <= Cobblemon.config.getMaxDynamaxLevel()) {
            pokemon.setDmaxLevel(pokemon.getDmaxLevel() + 1);
            player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.dynamax.candy", pokemon.getDisplayName(), pokemon.getDmaxLevel()), true);
            itemStack.decrementUnlessCreative(1, player);
            pokemon.updateAspects();
            return TypedActionResult.success(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return !pokemon.getSpecies().getDynamaxBlocked() && pokemon.getDmaxLevel() < Cobblemon.config.getMaxDynamaxLevel();
    }
}
