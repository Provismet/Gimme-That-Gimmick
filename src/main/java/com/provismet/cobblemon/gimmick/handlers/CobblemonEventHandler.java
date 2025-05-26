package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.battles.BattleFledEvent;
import com.cobblemon.mod.common.api.events.battles.BattleStartedPreEvent;
import com.cobblemon.mod.common.api.events.battles.BattleVictoryEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.MegaEvolutionEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.TerastallizationEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.ZMoveUsedEvent;
import com.cobblemon.mod.common.api.events.pokemon.PokemonCapturedEvent;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.battle.BattleUpdateTeamPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.pokemon.update.AbilityUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.api.gimmick.GimmickCheck;
import com.provismet.cobblemon.gimmick.api.gimmick.Gimmicks;
import com.provismet.cobblemon.gimmick.util.tag.GTGBlockTags;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import kotlin.Unit;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public abstract class CobblemonEventHandler {
    public static void register () {
        CobblemonEvents.MEGA_EVOLUTION.subscribe(Priority.NORMAL, CobblemonEventHandler::megaEvolutionUsed);
        CobblemonEvents.ZPOWER_USED.subscribe(Priority.NORMAL, CobblemonEventHandler::zMoveUsed);
        CobblemonEvents.TERASTALLIZATION.subscribe(Priority.NORMAL, CobblemonEventHandler::terrastallizationUsed);

        CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.NORMAL, CobblemonEventHandler::battleStarted);
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, CobblemonEventHandler::postBattleVictory);
        CobblemonEvents.BATTLE_FLED.subscribe(Priority.NORMAL, CobblemonEventHandler::postBattleFlee);

        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.NORMAL, CobblemonEventHandler::fixTeraTyping);
    }

    private static Unit battleStarted (BattleStartedPreEvent battleEvent) {
        for (ServerPlayerEntity player : battleEvent.getBattle().getPlayers()) {
            GeneralPlayerData data = Cobblemon.INSTANCE.getPlayerDataManager().getGenericData(player);

            boolean hasKeyStone = false;
            boolean hasZRing = false;
            boolean hasDynamax = false;
            boolean hasTeraOrb = false;
            for (ItemStack item : player.getEquippedItems()) {
                if (GimmickCheck.isKeyStone(item)) hasKeyStone = true;
                if (GimmickCheck.isZRing(item)) hasZRing = true;
                if (GimmickCheck.isDynamaxBand(item)) hasDynamax = true;
                if (GimmickCheck.isTeraOrb(item)) hasTeraOrb = true;
            }

            if (hasKeyStone) data.getKeyItems().add(Gimmicks.KEY_STONE);
            else data.getKeyItems().remove(Gimmicks.KEY_STONE);

            if (hasZRing) data.getKeyItems().add(Gimmicks.Z_RING);
            else data.getKeyItems().remove(Gimmicks.Z_RING);

            //TODO Change block that is gonna be used
            boolean powerSpotPossible = !Options.isPowerSpotRequired() || isBlockNearby(player, Options.getPowerSpotRange());
            if (hasDynamax && !hasTeraOrb && powerSpotPossible) data.getKeyItems().add(Gimmicks.DYNAMAX_BAND);
            else data.getKeyItems().remove(Gimmicks.DYNAMAX_BAND);

            if (hasTeraOrb) data.getKeyItems().add(Gimmicks.TERA_ORB);
            else data.getKeyItems().remove(Gimmicks.TERA_ORB);
        }

        return Unit.INSTANCE;
    }

    private static boolean isBlockNearby(ServerPlayerEntity player, int radius) {
        BlockPos playerPos = player.getBlockPos();
        ServerWorld world = player.getServerWorld();

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    BlockPos checkPos = playerPos.add(dx, dy, dz);
                    if (world.getBlockState(checkPos).isIn(GTGBlockTags.POWER_SPOTS)) {
                        return true;
                    }
                }
            }
        }

        return false; // Not found
    }

    public static Unit postBattleVictory (BattleVictoryEvent battleVictoryEvent) {
        battleVictoryEvent.getBattle().getPlayers().forEach(CobblemonEventHandler::resetBattlePokemon);
        return Unit.INSTANCE;
    }

    public static Unit postBattleFlee (BattleFledEvent battleFledEvent) {
        battleFledEvent.getBattle().getPlayers().forEach(CobblemonEventHandler::resetBattlePokemon);
        return Unit.INSTANCE;
    }

    private static Unit megaEvolutionUsed (MegaEvolutionEvent megaEvent) {
        Pokemon pokemon = megaEvent.getPokemon().getEffectedPokemon();
        ItemStack megaStone = pokemon.heldItem();
        if (megaStone.isIn(GTGItemTags.MEGA_STONES_X)) {
            new StringSpeciesFeature("mega_evolution", "mega_x").apply(pokemon);
        }
        else if (megaStone.isIn(GTGItemTags.MEGA_STONES_Y)) {
            new StringSpeciesFeature("mega_evolution", "mega_y").apply(pokemon);
        }
        else {
            new StringSpeciesFeature("mega_evolution", "mega").apply(pokemon);
        }

        megaEvent.getPokemon().sendUpdate();

        updatePokemonPackets(megaEvent.getBattle(), megaEvent.getPokemon(), true);
        return Unit.INSTANCE;
    }

    private static Unit zMoveUsed (ZMoveUsedEvent zMoveUsedEvent) {
        Pokemon pokemon = zMoveUsedEvent.getPokemon().getEffectedPokemon();

        if (pokemon.getSpecies().getName().equals("Necrozma") && pokemon.getHeldItem$common().isOf(GTGItems.ULTRANECROZIUM_Z)) {
            if (pokemon.getAspects().contains("dusk-fusion") || pokemon.getAspects().contains("dawn-fusion")) {
                if (pokemon.getFeature("prism_fusion") instanceof StringSpeciesFeature feature) {
                    pokemon.getPersistentData().putString("prism_fusion", feature.getValue());
                    new StringSpeciesFeature("prism_fusion", "ultra").apply(pokemon);
                    updatePokemonPackets(zMoveUsedEvent.getBattle(), zMoveUsedEvent.getPokemon(), true);
                }
            }
        }

        return Unit.INSTANCE;
    }

    private static Unit terrastallizationUsed (TerastallizationEvent terastallizationEvent) {
        Pokemon pokemon = terastallizationEvent.getPokemon().getEffectedPokemon();

        if (pokemon.getSpecies().getName().equals("Terapagos")) {
            new StringSpeciesFeature("tera_form", "stellar").apply(pokemon);
            updatePokemonPackets(terastallizationEvent.getBattle(), terastallizationEvent.getPokemon(), false);
        }

        if (pokemon.getSpecies().getName().equals("Ogerpon")) {
            new FlagSpeciesFeature("embody_aspect", true).apply(pokemon);
            updatePokemonPackets(terastallizationEvent.getBattle(), terastallizationEvent.getPokemon(), false);
        }

        return Unit.INSTANCE;
    }

    public static Unit fixTeraTyping (PokemonCapturedEvent pokemonCapturedEvent) {
        Pokemon pokemon = pokemonCapturedEvent.getPokemon();

        if (pokemon.getSpecies().getName().equals("Ogerpon")) {
            pokemon.setTeraType(TeraTypes.getGRASS());
        }
        else if (pokemon.getSpecies().getName().equals("Terapagos")) {
            pokemon.setTeraType(TeraTypes.getSTELLAR());
        }

        return Unit.INSTANCE;
    }

    public static void resetBattlePokemon (ServerPlayerEntity player) {
        PlayerPartyStore playerPartyStore = Cobblemon.INSTANCE.getStorage().getParty(player);
        for (Pokemon pokemon: playerPartyStore){
            CobblemonEventHandler.resetBattleForms(pokemon);
        }
    }

    public static void resetBattleForms (Pokemon pokemon) {
        if (pokemon.getAspects().contains("mega") || pokemon.getAspects().contains("mega_x") || pokemon.getAspects().contains("mega_y")) {
            pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("mega_evolution"));
        }

        if (pokemon.getAspects().contains("ultra-fusion")) {
            new StringSpeciesFeature("prism_fusion", pokemon.getPersistentData().getString("prism_fusion")).apply(pokemon);
            pokemon.getPersistentData().remove("prism_fusion");
        }

        if (pokemon.getAspects().contains("stellar-form") || pokemon.getAspects().contains("terastal-form")) {
            new StringSpeciesFeature("tera_form", "normal").apply(pokemon);
        }

        pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("embody_aspect"));
        pokemon.updateAspects();
    }

    /**
     *
     */
    public static void updatePokemonPackets (PokemonBattle battle, BattlePokemon battlePokemon, boolean abilities) {
        if (abilities) {
            battle.sendUpdate(new AbilityUpdatePacket(battlePokemon::getEffectedPokemon, battlePokemon.getEffectedPokemon().getAbility().getTemplate()));
            battle.sendUpdate(new BattleUpdateTeamPokemonPacket(battlePokemon.getEffectedPokemon()));
        }

        for (ActiveBattlePokemon activeBattlePokemon : battle.getActivePokemon()) {
            if (
                activeBattlePokemon.getBattlePokemon() != null
                && activeBattlePokemon.getBattlePokemon().getEffectedPokemon().getOwnerPlayer() == battlePokemon.getEffectedPokemon().getOwnerPlayer()
                && activeBattlePokemon.getBattlePokemon() == battlePokemon
            ) {
                battle.sendSidedUpdate(activeBattlePokemon.getActor(),
                    new BattleTransformPokemonPacket(activeBattlePokemon.getPNX(), battlePokemon, true),
                    new BattleTransformPokemonPacket(activeBattlePokemon.getPNX(), battlePokemon, false),
                    false
                );
            }
        }
    }
}
