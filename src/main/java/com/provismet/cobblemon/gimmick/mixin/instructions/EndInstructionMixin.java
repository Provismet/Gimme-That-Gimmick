package com.provismet.cobblemon.gimmick.mixin.instructions;


import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.interpreter.instructions.EndInstruction;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.provismet.cobblemon.gimmick.api.event.DynamaxEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(value = EndInstruction.class, remap = false)
public class EndInstructionMixin {
    @Shadow @Final private BattleMessage message;

    @Inject(method = "invoke", at = @At("HEAD"), remap = false)
    private void injectBeforeInvoke(PokemonBattle battle, CallbackInfo ci) {
        BattleMessage message = ((EndInstructionAccessor) this).getMessage();
        String raw = message.getRawMessage();

        String[] parts = raw.split("\\|");
        boolean containsDynamax = Arrays.stream(parts).anyMatch(part -> part.contains("Dynamax"));

        if (containsDynamax) {
            BattlePokemon pokemon = this.message.battlePokemon(0, battle);
            battle.dispatch(pokemonBattle -> {
                DynamaxEvents.DYNAMAX_END.invoker().onDynamaxEnd(battle, pokemon);
                return new UntilDispatch(() -> true);
            });
        }
    }
}
