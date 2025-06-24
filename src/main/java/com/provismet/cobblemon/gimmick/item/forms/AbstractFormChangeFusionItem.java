package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.pokemon.PokemonProperties;
import com.cobblemon.mod.common.api.pokemon.PokemonPropertyExtractor;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.provismet.cobblemon.gimmick.item.PolymerPokemonSelectingItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import kotlin.Unit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class AbstractFormChangeFusionItem extends PolymerPokemonSelectingItem {
    public AbstractFormChangeFusionItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData);
    }

    public AbstractFormChangeFusionItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
    }

    @Nullable
    @Override
    public TypedActionResult<ItemStack> applyToPokemon (@NotNull ServerPlayerEntity player, @NotNull ItemStack stack, @NotNull Pokemon pokemon) {
        if (!this.canUseOnPokemon(pokemon)) return TypedActionResult.fail(stack);

        if (pokemon.getPersistentData().contains("fusion_forme")) { // Split
            PokemonProperties properties = PokemonProperties.Companion.parse(pokemon.getPersistentData().getString("fusion_forme"), " ");
            Pokemon other = properties.create();
            PlayerExtensionsKt.party(player).add(other);
            this.applyUnplitForme(pokemon);
            pokemon.getPersistentData().remove("fusion_forme");
        }
        else { // Merge
            PartySelectCallbacks.INSTANCE.createFromPokemon(
                player,
                PlayerExtensionsKt.party(player).toGappyList().stream().filter(Objects::nonNull).toList(),
                this::isSuitableForMerging,
                otherPokemon -> {
                    if (player.getMainHandStack().equals(stack) || player.getOffHandStack().equals(stack)) {
                        return this.merge(player, stack, pokemon, otherPokemon);
                    }
                    return Unit.INSTANCE;
                }
            );
        }

        return TypedActionResult.success(stack);
    }

    protected Unit merge (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon, Pokemon other) {
        PokemonProperties otherPokemonProps = other.createPokemonProperties(PokemonPropertyExtractor.ALL);
        otherPokemonProps.setOriginalTrainer(other.getOriginalTrainerName());
        otherPokemonProps.setMoves(
            Stream.concat(
                other.getMoveSet()
                    .getMoves()
                    .stream()
                    .map(Move::getName),
                StreamSupport.stream(other.getBenchedMoves().spliterator(), false)
                    .map(benchedMove -> benchedMove.getMoveTemplate().getName())
            )
            .distinct()
            .toList()
        );

        this.applyFusedForme(pokemon, other);
        pokemon.getPersistentData().putString("fusion_forme", otherPokemonProps.asString(" "));
        this.postMerge(player, stack, pokemon, other);
        PlayerExtensionsKt.party(player).remove(other);

        if (other.getEntity() != null) {
            other.getEntity().kill();
        }

        return Unit.INSTANCE;
    }

    private boolean isSuitableForMerging (Pokemon other) {
        return other.heldItem().isEmpty() && this.canBeMerged(other);
    }

    protected void postMerge (ServerPlayerEntity player, ItemStack stack, Pokemon pokemon, Pokemon absorbed) {
        player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.fusion", pokemon.getDisplayName(), absorbed.getDisplayName()), true);
    }

    protected abstract boolean canBeMerged (Pokemon other);
    protected abstract void applyUnplitForme (Pokemon pokemon);
    protected abstract void applyFusedForme (Pokemon pokemon, Pokemon other);
}
