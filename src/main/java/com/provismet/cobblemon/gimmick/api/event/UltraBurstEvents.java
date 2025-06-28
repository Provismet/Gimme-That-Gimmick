package com.provismet.cobblemon.gimmick.api.event;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.provismet.cobblemon.gimmick.util.phase.EventPhases;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface UltraBurstEvents {
    Event<UltraBurst> ULTRA_BURST = EventFactory.createWithPhases(
            UltraBurst.class,
            listeners -> (battle, pokemon) -> {
                for (UltraBurst listener : listeners) {
                    listener.onUltraBurst(battle, pokemon);
                }
            },
            EventPhases.PHASE_ORDERING
    );

    @FunctionalInterface
    interface UltraBurst {
        void onUltraBurst(PokemonBattle battle, BattlePokemon pokemon);
    }
}
