package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import kotlin.Unit;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface FormChangeFusionItem extends FormChangeItem {
    @Nullable
    @Override
    default TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(stack, pokemon)) return TypedActionResult.fail(stack);

        if (pokemon.getPersistentData().contains("fusion_forme")) { // Split
            try {
                Pokemon other = Pokemon.Companion.loadFromNBT(player.getWorld().getRegistryManager(), pokemon.getPersistentData().getCompound("fusion_forme"));
                PlayerExtensionsKt.party(player).add(other);
            }
            catch (Exception e) {
                GimmeThatGimmickMain.LOGGER.error(
                    "Failed to decode fused pokemon to give back to {}. Pokemon NBT is as follows: {}",
                    player.getName().getString(),
                    pokemon.getPersistentData().getCompound("fusion_forme").toString()
                );
                GimmeThatGimmickMain.LOGGER.error("Error is as follows: ", e);
            }
            this.applyUnplitForme(stack, pokemon);
            pokemon.getPersistentData().remove("fusion_forme");
        }
        else { // Merge
            PartySelectCallbacks.INSTANCE.createFromPokemon(
                player,
                PlayerExtensionsKt.party(player).toGappyList().stream().filter(Objects::nonNull).toList(),
                otherPokemon -> this.isSuitableForMerging(stack, otherPokemon),
                otherPokemon -> {
                    if (player.getMainHandStack().equals(stack) || player.getOffHandStack().equals(stack)) {
                        return this.merge(player, stack, pokemon, otherPokemon);
                    }
                    return Unit.INSTANCE;
                }
            );
        }
        pokemon.getAnyChangeObservable().emit(pokemon);
        return TypedActionResult.success(stack);
    }

    default Unit merge (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon, Pokemon other) {
        this.applyFusedForme(stack, pokemon, other);
        NbtCompound otherPokemonNbt = other.saveToNBT(player.getWorld().getRegistryManager(), new NbtCompound());
        pokemon.getPersistentData().put("fusion_forme", otherPokemonNbt);
        this.postMerge(player, stack, pokemon, other);
        PlayerExtensionsKt.party(player).remove(other);

        if (other.getEntity() != null) {
            other.getEntity().discard();
        }

        return Unit.INSTANCE;
    }

    private boolean isSuitableForMerging (ItemStack stack, Pokemon other) {
        return other.heldItem().isEmpty() && this.canBeMerged(stack, other);
    }

    default void postMerge (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon, Pokemon absorbed) {
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.fusion", pokemon.getDisplayName(), absorbed.getDisplayName()), true);
    }

    boolean canBeMerged (ItemStack stack, Pokemon other);
    void applyUnplitForme (ItemStack stack, Pokemon pokemon);
    void applyFusedForme (ItemStack stack, Pokemon pokemon, Pokemon other);
}
