package com.provismet.cobblemon.gimmick.block;

import com.cobblemon.mod.common.api.callback.PartySelectCallbacks;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import eu.pb4.polymer.blocks.api.BlockModelType;
import kotlin.Unit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class MeteoriteBlock extends GenericPolymerTexturedBlock {
    public MeteoriteBlock (Settings settings, Identifier id) {
        super(settings, id, BlockModelType.FULL_BLOCK);
    }

    @Override
    protected ActionResult onUse (BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            PartySelectCallbacks.INSTANCE.createFromPokemon(
                serverPlayer,
                PlayerExtensionsKt.party(serverPlayer).toGappyList().stream().filter(Objects::nonNull).toList(),
                GTGItems.METEORITE::canUseOnPokemon,
                pokemon -> {
                    GTGItems.METEORITE.applyToPokemon(serverPlayer, GTGItems.METEORITE.getDefaultStack(), pokemon);
                    return Unit.INSTANCE;
                }
            );
        }
        return ActionResult.SUCCESS;
    }
}
