package com.provismet.cobblemon.gimmick.features;

import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.server.network.ServerPlayerEntity;

public class GlobalFeatureManager {
    public static void update (Pokemon pokemon, ServerPlayerEntity player) {
        DynamaxLevelHandler.update(pokemon, player);
    }
}
