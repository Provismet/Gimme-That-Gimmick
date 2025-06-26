package com.provismet.cobblemon.gimmick.util;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import net.minecraft.item.ItemStack;
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

    public static boolean megaEvolve (Pokemon pokemon) {
        ItemStack megaStone = pokemon.heldItem();
        if (megaStone.isIn(GTGItemTags.MEGA_STONES_X)) {
            new StringSpeciesFeature("mega_evolution", "mega_x").apply(pokemon);
        }
        else if (megaStone.isIn(GTGItemTags.MEGA_STONES_Y)) {
            new StringSpeciesFeature("mega_evolution", "mega_y").apply(pokemon);
        }
        else if (megaStone.isIn(GTGItemTags.MEGA_STONES)) {
            new StringSpeciesFeature("mega_evolution", "mega").apply(pokemon);
        }
        else {
            return false;
        }

        // Other mods will use this flag to prevent the trading of certain mons, only touch it conditionally.
        if (!pokemon.getTradeable()) {
            pokemon.setTradeable(false);
            pokemon.getPersistentData().putBoolean("tempUntradeable", true);
        }

        return true;
    }
}
