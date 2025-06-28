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
import com.cobblemon.mod.common.api.events.pokemon.HeldItemEvent;
import com.cobblemon.mod.common.api.events.pokemon.PokemonCapturedEvent;
import com.cobblemon.mod.common.api.events.pokemon.healing.PokemonHealedEvent;
import com.cobblemon.mod.common.api.item.HealingSource;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.battle.BattleUpdateTeamPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.pokemon.update.AbilityUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.api.gimmick.GimmickCheck;
import com.provismet.cobblemon.gimmick.api.gimmick.Gimmicks;
import com.provismet.cobblemon.gimmick.item.forms.GenericFormChangeHeldItem;
import com.provismet.cobblemon.gimmick.util.GlowHandler;
import com.provismet.cobblemon.gimmick.util.MegaHelper;
import com.provismet.cobblemon.gimmick.util.tag.GTGBlockTags;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import kotlin.Unit;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class CobblemonEventHandler {
    public static void register () {
        CobblemonEvents.MEGA_EVOLUTION.subscribe(Priority.NORMAL, CobblemonEventHandler::megaEvolutionUsed);
        CobblemonEvents.TERASTALLIZATION.subscribe(Priority.NORMAL, CobblemonEventHandler::terrastallizationUsed);
        CobblemonEvents.ZPOWER_USED.subscribe(Priority.NORMAL, CobblemonEventHandler::zmoveUsed);

        CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.NORMAL, CobblemonEventHandler::battleStarted);
        CobblemonEvents.BATTLE_VICTORY.subscribe(Priority.NORMAL, CobblemonEventHandler::postBattleVictory);
        CobblemonEvents.BATTLE_FLED.subscribe(Priority.NORMAL, CobblemonEventHandler::postBattleFlee);

        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.NORMAL, CobblemonEventHandler::fixTeraTyping);

        CobblemonEvents.HELD_ITEM_PRE.subscribe(Priority.NORMAL, CobblemonEventHandler::heldItemFormChange);
        CobblemonEvents.POKEMON_HEALED.subscribe(Priority.NORMAL, CobblemonEventHandler::pokemonHealed);

        UseEntityCallback.EVENT.register(CobblemonEventHandler::megaEvolveOutside);
    }

    private static ActionResult megaEvolveOutside (PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult entityHitResult) {
        if (player.getStackInHand(hand).isIn(GTGItemTags.KEY_STONES) && entity instanceof PokemonEntity pokemonEntity && !player.isSneaking()) {
            Pokemon pokemon = pokemonEntity.getPokemon();
            if (pokemon.getPersistentData().contains("last_mega", NbtElement.LONG_TYPE) && Math.abs(world.getTime() - pokemon.getPersistentData().getLong("last_mega")) < 20) {
                return ActionResult.FAIL;
            }

            player.swingHand(hand, true);
            if (!MegaHelper.hasMegaAspect(pokemon)) {
                if (MegaHelper.checkForMega((ServerPlayerEntity) player)) {
                    player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.mega_exists").formatted(Formatting.RED), true);
                    return ActionResult.SUCCESS;
                }

                if (!MegaHelper.megaEvolve(pokemon)) {
                    player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.no_stone").formatted(Formatting.RED), true);
                    return ActionResult.FAIL;
                }
            }
            else {
                new StringSpeciesFeature("mega_evolution", "none").apply(pokemon);
                if (pokemon.getPersistentData().contains("tempUntradeable")) {
                    pokemon.setTradeable(true);
                    pokemon.getPersistentData().remove("tempUntradeable");
                }
            }
            // This event triggers twice each time? Just adding a delay.
            pokemon.getPersistentData().putLong("last_mega", world.getTime());
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static Unit zmoveUsed (ZMoveUsedEvent zMoveUsedEvent) {
        PokemonEntity pokemonEntity = zMoveUsedEvent.getPokemon().getEntity();
        if (pokemonEntity != null) GlowHandler.applyZGlow(pokemonEntity);
        return Unit.INSTANCE;
    }

    private static Unit battleStarted (BattleStartedPreEvent battleEvent) {
        for (ServerPlayerEntity player : battleEvent.getBattle().getPlayers()) {
            CobblemonEventHandler.resetBattlePokemon(player);
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
        // Allows sidemods to dispatch animations before the transformation triggers.
        megaEvent.getBattle().dispatchToFront(() -> {
            MegaHelper.megaEvolve(megaEvent.getPokemon().getEffectedPokemon());
            megaEvent.getPokemon().sendUpdate();
            updatePokemonPackets(megaEvent.getBattle(), megaEvent.getPokemon(), true);

            return new UntilDispatch(() -> true);
        });
        return Unit.INSTANCE;
    }

    private static Unit terrastallizationUsed (TerastallizationEvent terastallizationEvent) {
        Pokemon pokemon = terastallizationEvent.getPokemon().getEffectedPokemon();
        ServerPlayerEntity player = pokemon.getOwnerPlayer();

        if (Options.canBreakTeraOrb() && player != null) {
            for (ItemStack item : player.getEquippedItems()) {
                if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS)) {
                    item.setDamage(item.getDamage() + 20);
                    break;
                }
            }
        }

        if (pokemon.getSpecies().getName().equals("Terapagos")) {
            terastallizationEvent.getBattle().dispatchToFront(() -> {
                new StringSpeciesFeature("tera_form", "stellar").apply(pokemon);
                updatePokemonPackets(terastallizationEvent.getBattle(), terastallizationEvent.getPokemon(), false);
                return new UntilDispatch(() -> true);
            });
        }

        if (pokemon.getSpecies().getName().equals("Ogerpon")) {
            terastallizationEvent.getBattle().dispatchToFront(() -> {
                new FlagSpeciesFeature("embody_aspect", true).apply(pokemon);
                updatePokemonPackets(terastallizationEvent.getBattle(), terastallizationEvent.getPokemon(), false);
                return new UntilDispatch(() -> true);
            });
        }

        if (pokemon.getEntity() != null) GlowHandler.applyTeraGlow(pokemon.getEntity());
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

        if (pokemon.getAspects().contains("gmax")) {
            pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("dynamax_form"));
        }

        if (pokemon.getAspects().contains("stellar-form") || pokemon.getAspects().contains("terastal-form")) {
            new StringSpeciesFeature("tera_form", "normal").apply(pokemon);
        }

        if (pokemon.getEntity() != null) {
            pokemon.getEntity().removeStatusEffect(StatusEffects.GLOWING);
            DynamaxEventHandler.startGradualScaling(pokemon.getEntity(), 1.0f);
        }

        pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("embody_aspect"));
        pokemon.updateAspects();
    }

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

    private static Unit heldItemFormChange(HeldItemEvent.Pre heldItemEvent) {
        if (heldItemEvent.getReturning().getItem() instanceof GenericFormChangeHeldItem formChanger) {
            formChanger.removeFromPokemon(heldItemEvent.getPokemon());
        }
        if (heldItemEvent.getReceiving().getItem() instanceof GenericFormChangeHeldItem formChanger) {
            formChanger.giveToPokemon(heldItemEvent.getPokemon());
        }

        return Unit.INSTANCE;
    }

    private static Unit pokemonHealed (PokemonHealedEvent pokemonHealedEvent) {
        ServerPlayerEntity player = pokemonHealedEvent.getPokemon().getOwnerPlayer();
        if(player == null || pokemonHealedEvent.getSource() != HealingSource.Force.INSTANCE){
            return Unit.INSTANCE;
        }
        for (ItemStack item : player.getEquippedItems()) {
            if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS)) {
                item.setDamage(0);
                break;
            }
        }
        return Unit.INSTANCE;
    }
}
