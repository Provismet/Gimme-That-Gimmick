package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.event.UltraBurstEvents;

public abstract class UltraBurstEventHandler {
    public static void register () {
        UltraBurstEvents.ULTRA_BURST.register(UltraBurstEventHandler::onUltra);
    }

    private static void onUltra (PokemonBattle battle, BattlePokemon battlePokemon) {
        Pokemon pokemon = battlePokemon.getEffectedPokemon();

        if (pokemon.getSpecies().getName().equals("Necrozma")) {
            if (pokemon.getAspects().contains("dusk-fusion") || pokemon.getAspects().contains("dawn-fusion")) {
                if (pokemon.getFeature("prism_fusion") instanceof StringSpeciesFeature feature) {
                    pokemon.getPersistentData().putString("prism_fusion", feature.getValue());
                    new StringSpeciesFeature("prism_fusion", "ultra").apply(pokemon);
                }
            }
        }
    }
}
