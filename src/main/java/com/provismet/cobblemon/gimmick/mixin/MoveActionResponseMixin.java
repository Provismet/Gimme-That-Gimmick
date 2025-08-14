package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.InBattleGimmickMove;
import com.cobblemon.mod.common.battles.InBattleMove;
import com.cobblemon.mod.common.battles.MoveActionResponse;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.battles.Targetable;
import kotlin.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(value = MoveActionResponse.class, remap = false)
public abstract class MoveActionResponseMixin {

    @Shadow private String moveName;
    @Shadow private String targetPnx;
    @Shadow private String gimmickID;

    /**
     * @author YajatKaul, Provismet
     * @reason TargetSelection
     */
    @Overwrite
    public boolean isValid (ActiveBattlePokemon activeBattlePokemon, ShowdownMoveset showdownMoveSet, boolean forceSwitch) {
        if (forceSwitch || showdownMoveSet == null) {
            return false;
        }

        InBattleMove move = showdownMoveSet.getMoves().stream()
            .filter(m -> m.getId().equals(moveName))
            .findFirst()
            .orElse(null);
        if (move == null) return false;

        InBattleGimmickMove gimmickMove = move.getGimmickMove();
        boolean validGimmickMove = gimmickMove != null && !gimmickMove.getDisabled();
        if (!validGimmickMove && !move.canBeUsed()) {
            return false;
        }

        List<Targetable> availableTargets;
        if (gimmickID != null && validGimmickMove && !gimmickID.equalsIgnoreCase("mega") && !gimmickID.equalsIgnoreCase("terastal")) {
            availableTargets = gimmickMove.getTarget().getTargetList().invoke(activeBattlePokemon);
        }
        else {
            availableTargets = move.getTarget().getTargetList().invoke(activeBattlePokemon);
        }

        if (availableTargets == null || availableTargets.isEmpty()) return true;
        if (this.targetPnx == null) return false;
        Pair<BattleActor, ActiveBattlePokemon> targetPair = activeBattlePokemon.getActor().getBattle().getActorAndActiveSlotFromPNX(this.targetPnx);
        return availableTargets.contains(targetPair.getSecond());
    }
}
