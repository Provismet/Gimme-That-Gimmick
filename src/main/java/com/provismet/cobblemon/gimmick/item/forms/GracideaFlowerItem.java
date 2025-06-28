package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class GracideaFlowerItem extends PolymerPokemonSelectingItem implements FormChangeToggleItem {
    private static final String FEATURE = "gracidea_forme";

    public GracideaFlowerItem(Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean shouldApplySpecialForm(Pokemon pokemon) {
        return !pokemon.getAspects().contains("sky-forme");
    }

    @Override
    public void applySpecialForm(ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "sky").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.gracidea.sky", pokemon.getDisplayName()), true);
    }

    @Override
    public void removeSpecialForm(ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "land").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.gracidea.land", pokemon.getDisplayName()), true);
    }

    @Override
    public boolean canUseOnPokemon(@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:shaymin");
    }

    @Override
    public void postFormChange(ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {
        player.getWorld().playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1f, 1f, true);
    }
}
