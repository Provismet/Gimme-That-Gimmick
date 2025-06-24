package com.provismet.cobblemon.gimmick.util;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.server.network.ServerPlayerEntity;

public class MegaHelper {
    public static boolean checkForMega(ServerPlayerEntity player) {
        PlayerPartyStore playerPartyStore = Cobblemon.INSTANCE.getStorage().getParty(player);
        PCStore playerPCStore = Cobblemon.INSTANCE.getStorage().getPC(player);

        for (Pokemon pokemon : playerPartyStore) {
            if (hasMegaAspect(pokemon)) {
                return true;
            }
        }

        for (Pokemon pokemon : playerPCStore) {
            if (hasMegaAspect(pokemon)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasMegaAspect(Pokemon pokemon) {
        return pokemon.getAspects().contains("mega_x") ||
                pokemon.getAspects().contains("mega_y") ||
                pokemon.getAspects().contains("mega");
    }
}
