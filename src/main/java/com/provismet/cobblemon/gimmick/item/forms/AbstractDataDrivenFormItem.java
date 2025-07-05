package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public abstract class AbstractDataDrivenFormItem extends PolymerPokemonSelectingItem implements FormChangeItem {
    public AbstractDataDrivenFormItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    public AbstractDataDrivenFormItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
    }

    @Override
    public @NotNull TypedActionResult<ItemStack> interactGeneral (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        return FormChangeItem.super.interactGeneral(player, itemStack);
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
