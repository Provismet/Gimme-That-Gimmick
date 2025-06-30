package com.provismet.cobblemon.gimmick.handlers.datapack;

import com.cobblemon.mod.common.api.events.battles.instruction.FormeChangeEvent;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import kotlin.Unit;

public class BattleFormChangeHandler {
    public static Unit formeChanges (FormeChangeEvent formeChangeEvent) {
        if (formeChangeEvent.getFormeName().equals("x") || formeChangeEvent.getFormeName().equals("y")
                || formeChangeEvent.getFormeName().equals("mega") || formeChangeEvent.getFormeName().equals("tera")) {
            return Unit.INSTANCE;
        }

        Pokemon pokemon = formeChangeEvent.getPokemon().getEffectedPokemon();

        //DATAPACK
        for (FormChangeData forme : GTGDynamicRegistries.formChangeRegistry) {
            if (forme.battle_mode_only()) {
                if (forme.pokemons().contains(formeChangeEvent.getPokemon().getEffectedPokemon().getSpecies().getName())
                        && formeChangeEvent.getFormeName().equals(forme.form_name())) {
                    for (String aspects : forme.aspects()) {
                        String[] aspectsDiv = aspects.split("=");
                        if (aspectsDiv[1].equals("true") || aspectsDiv[1].equals("false")) {
                            new FlagSpeciesFeature(aspectsDiv[0], Boolean.parseBoolean(aspectsDiv[1])).apply(pokemon);
                        } else {
                            new StringSpeciesFeature(aspectsDiv[0], aspectsDiv[1]).apply(pokemon);
                        }
                    }
                    if (pokemon.getEntity() != null) {
                        HandlerUtils.particleEffect(pokemon.getEntity(), forme.effects(), true);
                    }
                    break;
                }
            }
        }

        pokemon.updateAspects();

        return Unit.INSTANCE;
    }
}
