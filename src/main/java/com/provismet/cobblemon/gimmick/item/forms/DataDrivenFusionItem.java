package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.advancement.CobblemonCriteria;
import com.cobblemon.mod.common.advancement.criterion.PokemonInteractContext;
import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.ItemStackExtensionsKt;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.Fusion;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class DataDrivenFusionItem extends PolymerPokemonSelectingItem implements FormChangeFusionItem {
    public DataDrivenFusionItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    @Override
    public boolean canBeMerged (ItemStack stack, Pokemon other) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            return fusionData.canBeUsedAsInput(other);
        }
        return false;
    }

    @Override
    public void applyUnplitForme (ItemStack stack, Pokemon pokemon) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            fusionData.removeFeatures(pokemon);
        }
    }

    @Override
    public void applyFusedForme (ItemStack stack, Pokemon pokemon, Pokemon other) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);
        if (fusionData != null) {
            fusionData.applyFeatures(pokemon, other);
        }
    }

    @Override
    public @NotNull TypedActionResult<ItemStack> interactGeneral (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        GimmeThatGimmickMain.LOGGER.info("Used data-driven fusion item.");
        List<Pokemon> partyList = CollectionsKt.toList(PlayerExtensionsKt.party(player));
        if (partyList.isEmpty()) return TypedActionResult.fail(itemStack);

        PartySelectCallbacks.INSTANCE.createFromPokemon(
            player,
            partyList,
            pokemon -> this.canUseOnPokemon(itemStack, pokemon),
            pokemon -> {
                if (ItemStackExtensionsKt.isHeld(itemStack, player)) {
                    this.applyToPokemon(player, itemStack, pokemon);
                    CobblemonCriteria.INSTANCE.getPOKEMON_INTERACT().trigger(player, new PokemonInteractContext(pokemon.getSpecies().resourceIdentifier, Registries.ITEM.getId(itemStack.getItem())));
                }
                return Unit.INSTANCE;
            }
        );

        return TypedActionResult.success(itemStack);
    }

    @Override
    public boolean canUseOnPokemon (ItemStack stack, Pokemon pokemon) {
        Fusion fusionData = stack.get(GTGItemDataComponents.FUSION);

        if (fusionData == null) return false;
        return fusionData.recipient().matches(pokemon);
    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return false;
    }

    @Override
    public int getPolymerCustomModelData (ItemStack stack, @Nullable ServerPlayerEntity player) {
        return -1;
    }

    @Override
    public Item getPolymerItem (ItemStack stack, @Nullable ServerPlayerEntity player) {
        DataItem data = stack.get(GTGItemDataComponents.DATA_ITEM);
        if (data != null) {
            Optional<Item> item = Registries.ITEM.getOrEmpty(data.baseItem());
            if (item.isPresent()) return item.get();
        }

        return super.getPolymerItem(stack, player);
    }

    @Override
    public ItemStack getPolymerItemStack (ItemStack itemStack, TooltipType tooltipType, RegistryWrapper.WrapperLookup lookup, @Nullable ServerPlayerEntity player) {
        ItemStack stack = super.getPolymerItemStack(itemStack, tooltipType, lookup, player);
        DataItem data = itemStack.get(GTGItemDataComponents.DATA_ITEM);
        if (data != null) data.applyTo(stack);
        return stack;
    }
}
