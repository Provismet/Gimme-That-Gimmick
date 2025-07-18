package com.provismet.cobblemon.gimmick.features;

import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeature;
import com.cobblemon.mod.common.net.messages.client.pokemon.update.SpeciesFeatureUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.config.Options;

public class DynamaxLevelHandler {
    public static void update (Pokemon pokemon) {
        IntSpeciesFeature dmaxLevel = pokemon.getFeature("dynamax_level");
        if (dmaxLevel == null) {
            pokemon.getFeatures().add(new IntSpeciesFeature("dynamax_level", pokemon.getDmaxLevel()));
        }
        else {
            dmaxLevel.setValue(pokemon.getDmaxLevel());
        }
        pokemon.updateAspects();

        if (Options.shouldShowDynamaxLevel()) {
            pokemon.notify(new SpeciesFeatureUpdatePacket(
                () -> pokemon,
                pokemon.getSpecies().resourceIdentifier,
                new IntSpeciesFeature("dynamax_level", pokemon.getDmaxLevel())
            ));
        }
    }
}
