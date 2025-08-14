package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.BattleTypes;
import com.cobblemon.mod.common.battles.InBattleGimmickMove;
import com.cobblemon.mod.common.battles.InBattleMove;
import com.cobblemon.mod.common.battles.MoveActionResponse;
import com.cobblemon.mod.common.battles.MoveTarget;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
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
     * @author YajatKaul
     * @reason TargetSelection
     */
    @Overwrite
    public boolean isValid(ActiveBattlePokemon activeBattlePokemon, ShowdownMoveset showdownMoveSet, boolean forceSwitch) {
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

        MoveTarget moveTarget = (gimmickID != null && gimmickMove != null) ? gimmickMove.getTarget() : move.getTarget();

        List<?> availableTargets = moveTarget.getTargetList().invoke(activeBattlePokemon);
        if (availableTargets == null || availableTargets.isEmpty()) {
            GimmeThatGimmickMain.LOGGER.info("no available targets");
            return true;
        }
        GimmeThatGimmickMain.LOGGER.info("Number of available targets: {}", availableTargets.size());

        boolean isGimmickAOEInSingles = ("terastal".equals(gimmickID) || "dynamax".equals(gimmickID))
            && (moveTarget == MoveTarget.allAdjacent || moveTarget == MoveTarget.allAdjacentFoes || moveTarget == MoveTarget.adjacentFoe)
            && activeBattlePokemon.getBattle().getFormat().getBattleType() == BattleTypes.INSTANCE.getSINGLES();


        if (targetPnx == null) {
            GimmeThatGimmickMain.LOGGER.info("targetPnx is null");
            GimmeThatGimmickMain.LOGGER.info("moveTarget = {}", moveTarget.name());
            if (activeBattlePokemon.getBattle().getFormat().getBattleType() != BattleTypes.INSTANCE.getSINGLES()
                && moveTarget == MoveTarget.adjacentFoe) {
                return true;
            }

            return isGimmickAOEInSingles;
        }
        GimmeThatGimmickMain.LOGGER.info("targetPnx = {}", targetPnx);

        var pair = activeBattlePokemon.getActor().getBattle().getActorAndActiveSlotFromPNX(targetPnx);
        var targetPokemon = pair.getSecond();
        if (!availableTargets.contains(targetPokemon)) return false;
        if (isGimmickAOEInSingles) {
            this.targetPnx = null;
            return true;
        }

        return true;
    }
}
