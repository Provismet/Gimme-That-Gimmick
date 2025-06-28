package com.provismet.cobblemon.gimmick.api.event;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.provismet.cobblemon.gimmick.util.phase.EventPhases;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface DynamaxEvents {
    Event<DynamaxStart> DYNAMAX_START = EventFactory.createWithPhases(
            DynamaxStart.class,
            listeners -> (battle, pokemon, gigantamax) -> {
                for (DynamaxStart listener : listeners) {
                    listener.onDynamaxStart(battle, pokemon, gigantamax);
                }
            },
            EventPhases.PHASE_ORDERING
    );

    Event<DynamaxEnd> DYNAMAX_END = EventFactory.createWithPhases(
            DynamaxEnd.class,
            listeners -> (battle, pokemon) -> {
                for (DynamaxEnd listener : listeners) {
                    listener.onDynamaxEnd(battle, pokemon);
                }
            },
            EventPhases.PHASE_ORDERING
    );

    @FunctionalInterface
    interface DynamaxStart {
        void onDynamaxStart(PokemonBattle battle, BattlePokemon pokemon, boolean gigantamax);
    }

    @FunctionalInterface
    interface DynamaxEnd {
        void onDynamaxEnd(PokemonBattle battle, BattlePokemon pokemon);
    }
}
