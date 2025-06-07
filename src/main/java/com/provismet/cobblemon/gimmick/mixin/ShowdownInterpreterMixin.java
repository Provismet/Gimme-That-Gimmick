package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.ShowdownInterpreter;
import com.cobblemon.mod.common.battles.dispatch.InstructionSet;
import com.cobblemon.mod.common.battles.dispatch.InterpreterInstruction;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import com.provismet.cobblemon.gimmick.api.event.UltraBurstEvents;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

@Mixin(value = ShowdownInterpreter.class, remap = false)
public abstract class ShowdownInterpreterMixin {
    @Shadow @Final
    private static Map<String, Function4<PokemonBattle, InstructionSet, BattleMessage, Iterator<BattleMessage>, InterpreterInstruction>> updateInstructionParser;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injectBurst(CallbackInfo ci) {
        updateInstructionParser.put("-burst", (battle, instructionSet, message, messageIterator) -> pokemonBattle -> {
            BattlePokemon battlePokemon = message.battlePokemon(0, battle);
            if (battlePokemon == null) return;

            pokemonBattle.dispatchWaiting(1f, () -> {
                MutableText pokemonName = battlePokemon.getName();
                battle.broadcastChatMessage(LocalizationUtilsKt.battleLang("ultra", pokemonName).formatted(Formatting.YELLOW));
                battle.getMinorBattleActions().put(battlePokemon.getUuid(), message);
                UltraBurstEvents.ULTRA_BURST.invoker().onUltraBurst(pokemonBattle, battlePokemon);
                return Unit.INSTANCE;
            });
        });
    }
}
