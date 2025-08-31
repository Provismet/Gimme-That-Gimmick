package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class PrisonBottleItem extends PolymerPokemonSelectingItem implements FormChangeToggleItem {
    private static final String FEATURE = "djinn_state";

    public PrisonBottleItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean shouldApplySpecialForm (ItemStack stack, Pokemon pokemon) {
        return !pokemon.getAspects().contains("unbound");
    }

    @Override
    public void applySpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "unbound").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.prison.unbound", pokemon.getDisplayName(false)), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("prison_bottle_apply"));
        }
    }

    @Override
    public void removeSpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "confined").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.prison.confined", pokemon.getDisplayName(false)), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("prison_bottle_remove"));
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:hoopa");
    }

    @Override
    public void postFormChange (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon) {
        player.getWorld().playSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS, 1f , 1f, true);
    }
}
