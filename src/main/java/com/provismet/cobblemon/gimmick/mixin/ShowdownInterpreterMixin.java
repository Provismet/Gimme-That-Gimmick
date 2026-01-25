package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.battles.interpreter.BattleMessage;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.battles.ShowdownInterpreter;
import com.cobblemon.mod.common.battles.dispatch.InstructionSet;
import com.cobblemon.mod.common.battles.dispatch.InterpreterInstruction;
import com.cobblemon.mod.common.battles.interpreter.instructions.IgnoredInstruction;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.util.LocalizationUtilsKt;
import com.provismet.cobblemon.gimmick.api.event.UltraBurstEvents;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function6;
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

    @Shadow @Final
    private static Map<String, Function6<PokemonBattle, BattleActor, InstructionSet, BattleMessage, BattleMessage, Iterator<BattleMessage>, InterpreterInstruction>> splitInstructionParser;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injectBurst(CallbackInfo ci) {
        // Hello unorignal ik you are here to copy the code since ChatGPT won't solve this one :)
        updateInstructionParser.put("split", (battle, instructionSet, message, messages) -> {
            BattleMessage privateMessage = messages.next();
            BattleMessage publicMessage = messages.next();
            BattleActor targetActor = battle.getActor(message.argumentAt(0));
            if (targetActor == null) {
                return new IgnoredInstruction();
            }
            String type;
            if (!publicMessage.getRawMessage().isEmpty()) {
                String[] parts = publicMessage.getRawMessage().split("\\|");
                type = parts.length > 1 ? parts[1] : "ignored";
            } else {
                type = "ignored";
            }

            if (splitInstructionParser.containsKey(type)) {
                return splitInstructionParser.get(type).invoke(battle, targetActor, instructionSet, publicMessage, privateMessage, messages);
            } else {
                return new IgnoredInstruction();
            }
        });

        updateInstructionParser.put("-candynamax", (battle, instructionSet, message, messageIterator)
                -> new IgnoredInstruction());

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
