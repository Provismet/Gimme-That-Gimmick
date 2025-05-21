package com.provismet.cobblemon.gimmick.item.tera;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TeraShardItem extends PolymerHeldItem implements PokemonSelectingItem {
    private static final Set<String> BLACKLIST = Set.of("Ogerpon", "Terapagos");

    private final TeraType type;

    public TeraShardItem (Item.Settings settings, Item polymerItem, PolymerModelData modelData, TeraType teraType) {
        super(settings, polymerItem, modelData, 0);
        this.type = teraType;
    }

    @Nullable
    @Override
    public BagItem getBagItem () {
        return null;
    }

    @Override
    public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            return this.use(serverPlayer, serverPlayer.getStackInHand(hand));
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> use (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        return PokemonSelectingItem.DefaultImpls.use(this, player, itemStack);
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
        if (pokemon.getEntity() == null || pokemon.getEntity().getWorld().isClient || pokemon.getEntity().isBattling()){
            return TypedActionResult.pass(itemStack);
        }

        if (BLACKLIST.contains(pokemon.getSpecies().getName())) {
            return TypedActionResult.fail(itemStack);
        }

        if (pokemon.getOwnerPlayer() == player) {
            if (itemStack.getCount() == 50) {
                if (pokemon.getTeraType() == this.type) {
                    player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera.already_has", pokemon.getDisplayName()).formatted(Formatting.RED), true);
                    return TypedActionResult.fail(itemStack);
                }
                itemStack.decrement(50);
                pokemon.setTeraType(this.type);
                pokemon.updateForm();
                player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera.set", pokemon.getDisplayName(), this.type.getDisplayName()).formatted(Formatting.GREEN), true);
                return TypedActionResult.success(itemStack);
            }
            else {
                player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera.no_shards").formatted(Formatting.RED), true);
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public void applyToBattlePokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {

    }

    @Override
    public boolean canUseOnPokemon (@NotNull Pokemon pokemon) {
        return !BLACKLIST.contains(pokemon.getSpecies().getName());
    }

    @Override
    public boolean canUseOnBattlePokemon (@NotNull BattlePokemon battlePokemon) {
        return false;
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactWithSpecificBattle (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {
        return TypedActionResult.fail(itemStack);
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactGeneral (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack) {
        return PokemonSelectingItem.DefaultImpls.interactGeneral(this, player, itemStack);
    }

    @NotNull
    @Override
    public TypedActionResult<ItemStack> interactGeneralBattle (@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull BattleActor battleActor) {
        return TypedActionResult.fail(itemStack);
    }
}
