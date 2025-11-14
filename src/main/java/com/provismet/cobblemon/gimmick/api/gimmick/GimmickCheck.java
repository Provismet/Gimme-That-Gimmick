package com.provismet.cobblemon.gimmick.api.gimmick;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.util.tag.GTGBlockTags;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Utility interface for checking if an item should be considered a gimmick item.
 */
public interface GimmickCheck {
    static boolean isKeyStone (ItemStack item) {
        return isUnenchantedKeyStone(item)
            || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.KEY_STONE);
    }

    static boolean isZRing (ItemStack item) {
        return item.isIn(GTGItemTags.Z_RINGS)
            || item.get(GTGItemDataComponents.Z_RING) != null
            || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.Z_RING);
    }

    static boolean isDynamaxBand (ItemStack item) {
        return item.isIn(GTGItemTags.DYNAMAX_BANDS)
            || item.get(GTGItemDataComponents.DYNAMAX_BAND) != null
            || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.DYNAMAX_BAND);
    }

    static boolean isTeraOrb (ItemStack item) {
        if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS) && item.isDamageable() && item.getDamage() == item.getMaxDamage()) return false;

        return item.isIn(GTGItemTags.TERA_ORBS)
            || item.get(GTGItemDataComponents.TERA_ORB) != null
            || EnchantmentHelper.hasAnyEnchantmentsWith(item, GTGEnchantmentComponents.TERA_ORB);
    }

    static boolean isUnenchantedKeyStone (ItemStack item) {
        return item.isIn(GTGItemTags.KEY_STONES) || item.get(GTGItemDataComponents.KEY_STONE) != null;
    }

    static void applyGimmicks (ServerPlayerEntity player) {
        GeneralPlayerData data = Cobblemon.INSTANCE.getPlayerDataManager().getGenericData(player);

        boolean hasKeyStone = false;
        boolean hasZRing = false;
        boolean hasDynamax = false;
        boolean hasTeraOrb = false;
        ItemStack teraOrb = null;
        for (ItemStack item : player.getEquippedItems()) {
            if (Options.enabledMegaEvolution() && GimmickCheck.isKeyStone(item)) hasKeyStone = true;
            if (Options.enabledZMoves() && GimmickCheck.isZRing(item)) hasZRing = true;
            if (Options.enabledDynamax() && GimmickCheck.isDynamaxBand(item)) hasDynamax = true;
            if (Options.enabledTerastal() && GimmickCheck.isTeraOrb(item)) {
                hasTeraOrb = true;
                teraOrb = item;
            }
        }
        if (hasTeraOrb) {
            PlayerPartyStore playerPartyStore = Cobblemon.INSTANCE.getStorage().getParty(player);
            for (Pokemon partyMons: playerPartyStore) {
                if (partyMons.getSpecies().getName().equals("Terapagos")) {
                    teraOrb.setDamage(0);
                    break;
                }
            }
        }

        if (hasKeyStone) data.getKeyItems().add(Gimmicks.KEY_STONE);
        else data.getKeyItems().remove(Gimmicks.KEY_STONE);

        if (hasZRing) data.getKeyItems().add(Gimmicks.Z_RING);
        else data.getKeyItems().remove(Gimmicks.Z_RING);

        hasDynamax = hasDynamax && (!Options.isPowerSpotRequired() || powerSpotInRange(player.getServerWorld(), player.getBlockPos(), Options.getPowerSpotRange()));
        if (hasDynamax && !hasTeraOrb) data.getKeyItems().add(Gimmicks.DYNAMAX_BAND);
        else data.getKeyItems().remove(Gimmicks.DYNAMAX_BAND);

        if (hasTeraOrb) data.getKeyItems().add(Gimmicks.TERA_ORB);
        else data.getKeyItems().remove(Gimmicks.TERA_ORB);
    }

    static boolean powerSpotInRange (World world, BlockPos pos, int radius) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = pos.add(dx, dy, dz);
                    if (world.getBlockState(checkPos).isIn(GTGBlockTags.POWER_SPOTS)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
