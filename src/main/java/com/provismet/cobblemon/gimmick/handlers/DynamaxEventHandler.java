package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.provismet.cobblemon.gimmick.api.event.DynamaxEvents;

public abstract class DynamaxEventHandler {
    public static void register () {
        DynamaxEvents.DYNAMAX_START.register(DynamaxEventHandler::startDynamax);
        DynamaxEvents.DYNAMAX_END.register(DynamaxEventHandler::endDynamax);
    }

    private static void startDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon, boolean gmax) {
        if (gmax) {
            new StringSpeciesFeature("dynamax_form", "gmax").apply(pokemon.getEffectedPokemon());
            CobblemonEventHandler.updatePokemonPackets(pokemonBattle, pokemon, false);
        }
    }

    private static void endDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon) {
        pokemon.getEffectedPokemon().getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("dynamax_form"));
        pokemon.getEffectedPokemon().updateAspects();
    }
}
