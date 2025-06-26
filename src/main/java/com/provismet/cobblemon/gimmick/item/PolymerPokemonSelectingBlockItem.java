package com.provismet.cobblemon.gimmick.item;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class PolymerPokemonSelectingBlockItem extends PolymerBlockItemTextured implements PokemonSelectingItem, NumericalTooltipItem {
    private final int tooltipLines;

    public PolymerPokemonSelectingBlockItem (Block block, Settings settings, Item virtualItem, PolymerModelData model, int tooltipLines) {
        super(block, settings, virtualItem, model);
        this.tooltipLines = tooltipLines;
    }

    @Override
    public void appendTooltip (ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        for (int i = 1; i <= this.tooltipLines; ++i) {
            tooltip.add(Text.translatable(this.getTooltipTranslationKey(i)).formatted(Formatting.GRAY));
        }
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> use (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        return PokemonSelectingItem.DefaultImpls.use(this, player, itemStack);
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            return this.use(serverPlayer, serverPlayer.getStackInHand(hand));
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock (ItemUsageContext context) {
        if (context.getPlayer() != null && context.getPlayer().isSneaking()) return super.useOnBlock(context);
        return ActionResult.PASS;
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactGeneral (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        return PokemonSelectingItem.DefaultImpls.interactGeneral(this, player, itemStack);
    }

    @Nullable
    @Override
    public BagItem getBagItem () {
        return null;
    }

    @Override
    public boolean canUseOnBattlePokemon (@NotNull BattlePokemon battlePokemon) {
        return false;
    }

    @Override
    public void applyToBattlePokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {

    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactWithSpecificBattle (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {
        return TypedActionResult.fail(itemStack);
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactGeneralBattle (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattleActor battleActor) {
        return TypedActionResult.fail(itemStack);
    }
}
