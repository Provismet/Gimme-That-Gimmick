package com.provismet.cobblemon.gimmick.item.dynamax;

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

public class MaxSoupItem extends PolymerPokemonSelectingItem {
    public MaxSoupItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(itemStack, pokemon)) return TypedActionResult.fail(itemStack);
        if (pokemon.getEntity() != null) pokemon.getEntity().playSound(CobblemonSounds.MEDICINE_LIQUID_USE, 1f, 1f);

        pokemon.setGmaxFactor(!pokemon.getGmaxFactor());
        if (pokemon.getGmaxFactor()) player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.dynamax.soup.yes", pokemon.getDisplayName(false)), true);
        else player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.dynamax.soup.no", pokemon.getDisplayName(false)), true);

        itemStack.decrementUnlessCreative(1, player);
        pokemon.updateAspects();
        return TypedActionResult.success(itemStack);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getForms().stream().anyMatch(formData -> formData.getLabels().contains("gmax"));
    }
}
