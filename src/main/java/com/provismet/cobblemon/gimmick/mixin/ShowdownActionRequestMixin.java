package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.battles.ShowdownActionRequest;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.provismet.cobblemon.gimmick.api.gimmick.GimmickCheck;
import com.provismet.cobblemon.gimmick.api.gimmick.Gimmicks;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(value = ShowdownActionRequest.class, remap = false)
public class ShowdownActionRequestMixin {
    @Shadow private List<ShowdownMoveset> active;

    @Inject(method = "sanitize", at = @At("HEAD"), remap = false)
    private void beforeSanitize (PokemonBattle battle, BattleActor battleActor, CallbackInfo ci) {
        battle.getPlayers().forEach(GimmickCheck::applyGimmicks);
    }

    @Inject(method = "sanitize", at = @At("TAIL"), remap = false)
    private void afterSanitize (PokemonBattle battle, BattleActor battleActor, CallbackInfo info) {
        for (ServerPlayerEntity player : battle.getPlayers()) {
            GeneralPlayerData data = Cobblemon.INSTANCE.getPlayerDataManager().getGenericData(player);
            boolean hasBand = data.getKeyItems().contains(Gimmicks.DYNAMAX_BAND);

            if (player.getUuid().equals(battleActor.getUuid())) {
                if (this.active != null) {
                    for (ShowdownMoveset moveset : this.active) {
                        if (moveset.getCanUltraBurst()) {
                            moveset.blockGimmick(ShowdownMoveset.Gimmick.ULTRA_BURST);
                            moveset.setCanMegaEvo(true);
                        }

                        if (!hasBand) {
                            moveset.blockGimmick(ShowdownMoveset.Gimmick.DYNAMAX);
                            moveset.setMaxMoves(null);
                        }
                    }
                }
            }
        }
    }
}
