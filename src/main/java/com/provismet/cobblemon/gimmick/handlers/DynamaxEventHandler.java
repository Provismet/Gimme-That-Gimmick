package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.provismet.cobblemon.gimmick.api.event.DynamaxEvents;
import net.fabricmc.fabric.api.event.Event;

public abstract class DynamaxEventHandler {
    public static void register () {
        DynamaxEvents.DYNAMAX_START.register(Event.DEFAULT_PHASE, DynamaxEventHandler::startDynamax);
        DynamaxEvents.DYNAMAX_END.register(Event.DEFAULT_PHASE, DynamaxEventHandler::endDynamax);
    }

    private static void startDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon, boolean gmax) {
        if (gmax) {
            pokemonBattle.dispatchToFront(() -> {
                new StringSpeciesFeature("dynamax_form", "gmax").apply(pokemon.getEffectedPokemon());
                CobblemonEventHandler.updatePokemonPackets(pokemonBattle, pokemon, false);
                return new UntilDispatch(() -> true);
            });
        }
    }

    private static void endDynamax (PokemonBattle pokemonBattle, BattlePokemon pokemon) {
        pokemonBattle.dispatchToFront(() -> {
            pokemon.getEffectedPokemon().getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("dynamax_form"));
            pokemon.getEffectedPokemon().updateAspects();
            return new UntilDispatch(() -> true);
        });
    }
}
