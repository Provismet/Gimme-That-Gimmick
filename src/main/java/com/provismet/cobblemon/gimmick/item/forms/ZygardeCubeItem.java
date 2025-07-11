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

public class ZygardeCubeItem extends PolymerPokemonSelectingItem implements FormChangeToggleItem {
    public ZygardeCubeItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public boolean shouldApplySpecialForm (ItemStack stack, Pokemon pokemon) {
        return pokemon.getAspects().contains("10-percent");
    }

    @Override
    public void applySpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature("percent_cells", "50").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.zygarde.50", pokemon.getDisplayName()), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("zygarde_cube_apply"));
        }
    }

    @Override
    public void removeSpecialForm (ItemStack stack, ServerPlayerEntity player, Pokemon pokemon) {
        new StringSpeciesFeature("percent_cells", "10").apply(pokemon);
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.zygarde.10", pokemon.getDisplayName()), true);

        if (pokemon.getEntity() != null) {
            EffectsData.run(pokemon.getEntity(), GimmeThatGimmickMain.identifier("zygarde_cube_remove"));
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().showdownId().equalsIgnoreCase("zygarde")
            && pokemon.getAbility().getName().equalsIgnoreCase("powerconstruct");
    }
}
