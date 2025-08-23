package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingBlockItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MeteoriteItem extends PolymerPokemonSelectingBlockItem implements FormChangeSelectionItem {
    public MeteoriteItem (Block block, Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(block, settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public @Nullable TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        GimmeThatGimmickMain.LOGGER.info("applyToPokemon");
        return FormChangeSelectionItem.super.applyToPokemon(player, stack, pokemon);
    }

    @Override
    public void applyForm (ServerPlayerEntity player, Pokemon pokemon, FormData form) {
        String formName = form == null ? "normal" : form.formOnlyShowdownId();
        new StringSpeciesFeature("meteorite_forme", formName).apply(pokemon);

        if (pokemon.getEntity() != null) {
            List<String> effects = List.of("meteorite_" + formName, "meteorite");
            for (String effectName : effects) {
                Identifier id = GimmeThatGimmickMain.identifier(effectName);
                if (EffectsData.get(pokemon.getEntity().getRegistryManager(), id).isPresent()) {
                    EffectsData.run(pokemon.getEntity(), id);
                    break;
                }
            }
        }
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return pokemon.getSpecies().getResourceIdentifier().toString().equals("cobblemon:deoxys");
    }
}
