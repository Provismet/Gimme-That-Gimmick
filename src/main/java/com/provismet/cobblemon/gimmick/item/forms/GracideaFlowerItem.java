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
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class GracideaFlowerItem extends PolymerPokemonSelectingItem implements FormChangeToggleItem {
    private static final String FEATURE = "gracidea_forme";

    public GracideaFlowerItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean shouldApplySpecialForm (ItemStack stack, Pokemon pokemon) {
        return !pokemon.getAspects().contains("sky-forme");
    }

    @Override
    public void applySpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "sky").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.gracidea.sky", pokemon.getDisplayName(false)), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("gracidea_flower_apply"));
        }
    }

    @Override
    public void removeSpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature(FEATURE, "land").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.gracidea.land", pokemon.getDisplayName(false)), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("gracidea_flower_remove"));
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:shaymin");
    }
}
