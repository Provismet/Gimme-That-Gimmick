package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.provismet.cobblemon.gimmick.handlers.CobblemonEventHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PokemonBattle.class, remap = false)
public abstract class PokemonBattleMixin {
    @Shadow
    @Final
    public abstract Iterable<BattleActor> getActors();

    @Inject(method = "stop", at = @At("HEAD"))
    private void resetTeamsOnForceTie(CallbackInfo info) {
        for (BattleActor actor : this.getActors()) {
            actor.getPokemonList().forEach(bp -> {
                CobblemonEventHandler.resetBattleForms(bp.getEffectedPokemon());
                CobblemonEventHandler.resetBattleForms(bp.getOriginalPokemon());
            });
        }
    }
}
