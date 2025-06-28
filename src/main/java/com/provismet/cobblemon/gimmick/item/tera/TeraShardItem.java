package com.provismet.cobblemon.gimmick.item.tera;

import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class TeraShardItem extends PolymerPokemonSelectingItem {
    private static final Set<String> BLACKLIST = Set.of("Ogerpon", "Terapagos");

    private final TeraType type;

    public TeraShardItem(Item.Settings settings, Item polymerItem, PolymerModelData modelData, TeraType teraType) {
        super(settings, polymerItem, modelData, 0);
        this.type = teraType;
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon(@NotNull ServerPlayerEntity player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
        if (pokemon.getEntity() == null || pokemon.getEntity().getWorld().isClient || pokemon.getEntity().isBattling()) {
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
                itemStack.decrementUnlessCreative(50, player);
                pokemon.setTeraType(this.type);
                pokemon.getEntity().playSound(CobblemonSounds.MEDICINE_PILLS_USE, 1f, 1f);
                pokemon.updateAspects();
                player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera.set", pokemon.getDisplayName(), this.type.getDisplayName()), true);
                return TypedActionResult.success(itemStack);
            } else {
                player.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera.no_shards").formatted(Formatting.RED), true);
            }
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public boolean canUseOnPokemon(@NotNull Pokemon pokemon) {
        return !BLACKLIST.contains(pokemon.getSpecies().getName());
    }
}
