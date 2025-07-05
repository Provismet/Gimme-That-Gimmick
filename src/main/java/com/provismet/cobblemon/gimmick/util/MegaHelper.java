package com.provismet.cobblemon.gimmick.util;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.component.MegaEvolution;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class MegaHelper {
    public static final List<String> MEGA_ASPECTS = List.of("mega", "mega_x", "mega_y");

    public static boolean checkForMega (ServerPlayerEntity player) {
        PlayerPartyStore playerPartyStore = Cobblemon.INSTANCE.getStorage().getParty(player);
        PCStore playerPCStore = Cobblemon.INSTANCE.getStorage().getPC(player);

        for (Pokemon pokemon : playerPartyStore) {
            boolean canHaveAnyMegaForm = pokemon.getSpecies().getForms().stream()
                    .flatMap(form -> form.getLabels().stream())
                    .anyMatch(MEGA_ASPECTS::contains);

            if (canHaveAnyMegaForm && hasMegaAspect(pokemon)) {
                return true;
            }
        }

        for (Pokemon pokemon : playerPCStore) {
            boolean canHaveAnyMegaForm = pokemon.getSpecies().getForms().stream()
                .flatMap(form -> form.getLabels().stream())
                .anyMatch(MEGA_ASPECTS::contains);

            if (canHaveAnyMegaForm && hasMegaAspect(pokemon)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasMegaAspect (Pokemon pokemon) {
        return pokemon.getAspects().stream().anyMatch(MEGA_ASPECTS::contains);
    }

    public static boolean megaEvolve (Pokemon pokemon) {
        ItemStack megaStone = pokemon.heldItem();
        MegaEvolution mega = megaStone.getOrDefault(GTGItemDataComponents.MEGA_EVOLUTION, MegaEvolution.DEFAULT);

        if (mega.pokemon().matches(pokemon)) {
            mega.onApply().apply(pokemon);

            // Other mods will use this flag to prevent the trading of certain mons, only touch it conditionally.
            if (pokemon.getTradeable()) {
                pokemon.getPersistentData().putBoolean("megaNoTrade", true);
                pokemon.setTradeable(false);
            }

            return true;
        }
        return false;
    }

    public static void megaDevolve (Pokemon pokemon) {
        ItemStack megaStone = pokemon.heldItem();
        MegaEvolution mega = megaStone.getOrDefault(GTGItemDataComponents.MEGA_EVOLUTION, MegaEvolution.DEFAULT);
        mega.onRemove().apply(pokemon);

        if (pokemon.getPersistentData().contains("megaNoTrade")) {
            pokemon.setTradeable(true);
            pokemon.getPersistentData().remove("megaNoTrade");
        }
    }
}
