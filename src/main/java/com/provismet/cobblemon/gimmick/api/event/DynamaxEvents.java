package com.provismet.cobblemon.gimmick.api.event;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class DynamaxEvents {
    public static final Event<DynamaxStart> DYNAMAX_START = EventFactory.createArrayBacked(
        DynamaxStart.class,
        listeners -> (battle, pokemon, gigantamax) -> {
            for (DynamaxStart listener : listeners) {
                listener.onDynamaxStart(battle, pokemon, gigantamax);
            }
        }
    );

    public static final Event<DynamaxEnd> DYNAMAX_END = EventFactory.createArrayBacked(
        DynamaxEnd.class,
        listeners -> (battle, pokemon) -> {
            for (DynamaxEnd listener : listeners) {
                listener.onDynamaxEnd(battle, pokemon);
            }
        }
    );

    public interface DynamaxStart {
        void onDynamaxStart (PokemonBattle battle, BattlePokemon pokemon, boolean gigantamax);
    }

    public interface DynamaxEnd {
        void onDynamaxEnd (PokemonBattle battle, BattlePokemon pokemon);
    }
}
