package com.provismet.cobblemon.gimmick.api.event;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface UltraBurstEvents {
    Event<UltraBurst> ULTRA_BURST = EventFactory.createArrayBacked(
        UltraBurst.class,
        listeners -> (battle, pokemon) -> {
            for (UltraBurst listener : listeners) {
                listener.onUltraBurst(battle, pokemon);
            }
        }
    );

    @FunctionalInterface
    interface UltraBurst {
        void onUltraBurst (PokemonBattle battle, BattlePokemon pokemon);
    }
}
