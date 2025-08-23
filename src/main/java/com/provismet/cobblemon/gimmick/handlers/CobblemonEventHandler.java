package com.provismet.cobblemon.gimmick.handlers;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.battles.BattleStartedPostEvent;
import com.cobblemon.mod.common.api.events.battles.BattleStartedPreEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.FormeChangeEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.MegaEvolutionEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.TerastallizationEvent;
import com.cobblemon.mod.common.api.events.battles.instruction.ZMoveUsedEvent;
import com.cobblemon.mod.common.api.events.pokemon.HeldItemEvent;
import com.cobblemon.mod.common.api.events.pokemon.PokemonCapturedEvent;
import com.cobblemon.mod.common.api.events.pokemon.PokemonSentPostEvent;
import com.cobblemon.mod.common.api.events.pokemon.healing.PokemonHealedEvent;
import com.cobblemon.mod.common.api.item.HealingSource;
import com.cobblemon.mod.common.api.pokemon.feature.FlagSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.api.storage.player.GeneralPlayerData;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.battles.ActiveBattlePokemon;
import com.cobblemon.mod.common.battles.actor.PlayerBattleActor;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.net.messages.client.battle.BattleTransformPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.battle.BattleUpdateTeamPokemonPacket;
import com.cobblemon.mod.common.net.messages.client.pokemon.update.AbilityUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.pokemon.properties.AspectPropertyType;
import com.cobblemon.mod.common.pokemon.properties.UnaspectPropertyType;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.api.gimmick.GimmickCheck;
import com.provismet.cobblemon.gimmick.api.gimmick.Gimmicks;
import com.provismet.cobblemon.gimmick.item.forms.GenericFormChangeHeldItem;
import com.provismet.cobblemon.gimmick.item.zmove.TypedZCrystalItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGStatusEffects;
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
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

public abstract class CobblemonEventHandler {
    public static void register () {
        CobblemonEvents.MEGA_EVOLUTION.subscribe(Priority.NORMAL, CobblemonEventHandler::megaEvolutionUsed);
        CobblemonEvents.TERASTALLIZATION.subscribe(Priority.NORMAL, CobblemonEventHandler::terrastallizationUsed);
        CobblemonEvents.ZPOWER_USED.subscribe(Priority.NORMAL, CobblemonEventHandler::zmoveUsed);

        CobblemonEvents.BATTLE_STARTED_PRE.subscribe(Priority.NORMAL, CobblemonEventHandler::battleStarted);
        CobblemonEvents.BATTLE_STARTED_POST.subscribe(Priority.NORMAL, CobblemonEventHandler::battleEndHandler);

        CobblemonEvents.POKEMON_CAPTURED.subscribe(Priority.NORMAL, CobblemonEventHandler::fixTeraTyping);

        CobblemonEvents.HELD_ITEM_PRE.subscribe(Priority.NORMAL, CobblemonEventHandler::heldItemFormChange);
        CobblemonEvents.POKEMON_HEALED.subscribe(Priority.NORMAL, CobblemonEventHandler::pokemonHealed);
        CobblemonEvents.POKEMON_SENT_POST.subscribe(Priority.NORMAL, CobblemonEventHandler::pokemonSentOut);

        CobblemonEvents.FORME_CHANGE.subscribe(Priority.NORMAL, CobblemonEventHandler::formeChanges);
        UseEntityCallback.EVENT.register(CobblemonEventHandler::megaEvolveOutside);
    }

    private static ActionResult megaEvolveOutside (PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult entityHitResult) {
        if (GimmickCheck.isUnenchantedKeyStone(player.getStackInHand(hand)) && entity instanceof PokemonEntity pokemonEntity && !player.isSneaking()) {
            Pokemon pokemon = pokemonEntity.getPokemon();
            if (pokemon.getPersistentData().contains("last_mega", NbtElement.LONG_TYPE) && Math.abs(world.getTime() - pokemon.getPersistentData().getLong("last_mega")) < 20) {
                return ActionResult.FAIL;
            }

            player.swingHand(hand, true);
            if (!MegaHelper.hasMegaAspect(pokemon)) {
                if (!Options.shouldAllowMultipleOutOfCombatMegas() && MegaHelper.checkForMega((ServerPlayerEntity) player)) {
                    player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.mega_exists").formatted(Formatting.RED), true);
                    return ActionResult.SUCCESS;
                }

                if (MegaHelper.megaEvolve(pokemon, false)) {
                    List<String> prioritisedEffects = List.of(
                        "mega_evolution_outside" + pokemon.showdownId(),
                        "mega_evolution_outside"
                    );

                    for (String id : prioritisedEffects) {
                        Optional<RegistryEntry.Reference<EffectsData>> effectsData = EffectsData.get(pokemonEntity.getRegistryManager(), GimmeThatGimmickMain.identifier(id));
                        if (effectsData.isPresent()) {
                            effectsData.get().value().run(pokemonEntity);
                            break;
                        }
                    }
                }
                else {
                    player.sendMessage(Text.translatable("message.overlay.gimme-that-gimmick.no_stone").formatted(Formatting.RED), true);
                    return ActionResult.FAIL;
                }
            }
            else {
                MegaHelper.megaDevolve(pokemon);
            }
            // This event triggers twice each time? Just adding a delay.
            pokemon.getPersistentData().putLong("last_mega", world.getTime());
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private static Unit zmoveUsed (ZMoveUsedEvent zMoveUsedEvent) {
        PokemonEntity pokemonEntity = zMoveUsedEvent.getPokemon().getEntity();
        if (pokemonEntity != null) {
            if (Options.shouldApplyBasicZGlow()) GlowHandler.applyZGlow(pokemonEntity);

            String type = (zMoveUsedEvent.getPokemon().getEffectedPokemon().heldItem().getItem() instanceof TypedZCrystalItem crystal ? crystal.type.getName() : zMoveUsedEvent.getPokemon().getEffectedPokemon().getPrimaryType().getName());
            String species = zMoveUsedEvent.getPokemon().getEffectedPokemon().getSpecies().showdownId();
            List<String> prioritisedEffects = List.of(
                "z_move_" + species + "_" + type,
                "z_move_" + species,
                "z_move_" + type,
                "z_move"
            );

            for (String effectName : prioritisedEffects) {
                Identifier key = GimmeThatGimmickMain.identifier(effectName);
                Optional<RegistryEntry.Reference<EffectsData>> effect = EffectsData.get(pokemonEntity.getRegistryManager(), key);
                if (effect.isPresent()) {
                    Optional<PokemonEntity> other = StreamSupport.stream(zMoveUsedEvent.getBattle().getActivePokemon().spliterator(), false)
                        .map(ActiveBattlePokemon::getBattlePokemon)
                        .filter(active -> zMoveUsedEvent.getPokemon().getFacedOpponents().contains(active))
                        .filter(Objects::nonNull)
                        .map(BattlePokemon::getEntity)
                        .filter(Objects::nonNull)
                        .findAny();

                    effect.get().value().run(pokemonEntity, other.orElse(null), zMoveUsedEvent.getBattle());
                    break;
                }
            }
        }
        return Unit.INSTANCE;
    }

    private static Unit battleStarted (BattleStartedPreEvent battleEvent) {
        for (BattleActor actor : battleEvent.getBattle().getActors()) {
            if (!(actor instanceof PlayerBattleActor)) continue;
            actor.getPokemonList().forEach(battlePokemon -> CobblemonEventHandler.resetBattleForms(battlePokemon.getEffectedPokemon()));
        }

        for (ServerPlayerEntity player : battleEvent.getBattle().getPlayers()) {
            CobblemonEventHandler.resetBattlePokemon(player);
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

            hasDynamax = hasDynamax && (!Options.isPowerSpotRequired() || isPowerSpotNearby(player, Options.getPowerSpotRange()));
            if (hasDynamax && !hasTeraOrb) data.getKeyItems().add(Gimmicks.DYNAMAX_BAND);
            else data.getKeyItems().remove(Gimmicks.DYNAMAX_BAND);

            if (hasTeraOrb) data.getKeyItems().add(Gimmicks.TERA_ORB);
            else data.getKeyItems().remove(Gimmicks.TERA_ORB);
        }

        return Unit.INSTANCE;
    }

    private static Unit battleEndHandler(BattleStartedPostEvent battleStartedPostEvent) {
        battleStartedPostEvent.getBattle().getOnEndHandlers().add(battle -> {
            battle.getPlayers().forEach(CobblemonEventHandler::resetBattlePokemon);
            return Unit.INSTANCE;
        });
        return Unit.INSTANCE;
    }

    private static boolean isPowerSpotNearby (ServerPlayerEntity player, int radius) {
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

    private static Unit megaEvolutionUsed (MegaEvolutionEvent megaEvent) {
        megaEvent.getBattle().dispatchToFront(() -> {
            MegaHelper.megaEvolve(megaEvent.getPokemon().getEffectedPokemon(), true);
            megaEvent.getPokemon().sendUpdate();
            updatePokemonPackets(megaEvent.getBattle(), megaEvent.getPokemon(), true);

            return new UntilDispatch(() -> true);
        });

        Pokemon pokemon = megaEvent.getPokemon().getEffectedPokemon();
        if (pokemon.getEntity() != null) {
            List<String> prioritisedEffects = List.of(
                "mega_evolution_" + megaEvent.getPokemon().getEffectedPokemon().showdownId(),
                "mega_evolution"
            );

            for (String effectName : prioritisedEffects) {
                Identifier key = GimmeThatGimmickMain.identifier(effectName);
                Optional<RegistryEntry.Reference<EffectsData>> effect = EffectsData.get(pokemon.getEntity().getRegistryManager(), key);
                if (effect.isPresent()) {
                    Optional<PokemonEntity> other = StreamSupport.stream(megaEvent.getBattle().getActivePokemon().spliterator(), false)
                        .map(ActiveBattlePokemon::getBattlePokemon)
                        .filter(active -> megaEvent.getPokemon().getFacedOpponents().contains(active))
                        .filter(Objects::nonNull)
                        .map(BattlePokemon::getEntity)
                        .filter(Objects::nonNull)
                        .findAny();

                    effect.get().value().run(pokemon.getEntity(), other.orElse(null), megaEvent.getBattle());
                    break;
                }
            }
        }

        return Unit.INSTANCE;
    }

    private static Unit terrastallizationUsed (TerastallizationEvent terastallizationEvent) {
        Pokemon pokemon = terastallizationEvent.getPokemon().getEffectedPokemon();
        ServerPlayerEntity player = pokemon.getOwnerPlayer();

        if (Options.canBreakTeraOrb() && player != null) {
            PlayerPartyStore playerPartyStore = Cobblemon.INSTANCE.getStorage().getParty(player);
            boolean hasTerapagos = false;
            for(Pokemon partyMons: playerPartyStore){
                if(partyMons.getSpecies().getName().equals("Terapagos")){
                    hasTerapagos = true;
                    break;
                }
            }

            if(!hasTerapagos){
                for (ItemStack item : player.getEquippedItems()) {
                    if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS)) {
                        item.setDamage(item.getDamage() + 20);
                        break;
                    }
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

        if (pokemon.getEntity() != null) {
            if (Options.shouldApplyBasicTeraGlow()) {
                GlowHandler.applyTeraGlow(pokemon.getEntity());
                pokemon.getPersistentData().putBoolean("tera", true);
            }
            String teraAspect = "tera_" + pokemon.getTeraType().showdownId();
            pokemon.getPersistentData().putString("tera_aspect", teraAspect);
            terastallizationEvent.getBattle().dispatchToFront(() -> {
                AspectPropertyType.INSTANCE.fromString(teraAspect).apply(pokemon);
                return new UntilDispatch(() -> true);
            });

            List<String> prioritisedEffects = List.of(
                "terastallization_" + pokemon.getSpecies().showdownId() + "_" + pokemon.getTeraType().showdownId(),
                "terastallization_" + pokemon.getSpecies().showdownId(),
                "terastallization_" + pokemon.getTeraType().showdownId(),
                "terastallization"
            );

            for (String effectName : prioritisedEffects) {
                Identifier key = GimmeThatGimmickMain.identifier(effectName);
                Optional<RegistryEntry.Reference<EffectsData>> effect = EffectsData.get(pokemon.getEntity().getRegistryManager(), key);
                if (effect.isPresent()) {
                    Optional<PokemonEntity> other = StreamSupport.stream(terastallizationEvent.getBattle().getActivePokemon().spliterator(), false)
                        .map(ActiveBattlePokemon::getBattlePokemon)
                        .filter(active -> terastallizationEvent.getPokemon().getFacedOpponents().contains(active))
                        .filter(Objects::nonNull)
                        .map(BattlePokemon::getEntity)
                        .filter(Objects::nonNull)
                        .findAny();

                    effect.get().value().run(pokemon.getEntity(), other.orElse(null), terastallizationEvent.getBattle());
                    break;
                }
            }
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
        MegaHelper.megaDevolve(pokemon);
        pokemon.getPersistentData().remove("is_tera");
        if (pokemon.getPersistentData().contains("tera_aspect")) {
            String aspect = pokemon.getPersistentData().getString("tera_aspect");
            UnaspectPropertyType.INSTANCE.fromString(aspect).apply(pokemon);
        }

        GTGDynamicRegistries.battleForms.getOrEmpty(pokemon.getSpecies().getResourceIdentifier())
            .ifPresent(form -> form.defaultForm().features().apply(pokemon));

        if (pokemon.getAspects().contains("complete-percent")) { // Zygarde
            new StringSpeciesFeature("percent_cells", pokemon.getPersistentData().getString("percent_cells")).apply(pokemon);
            pokemon.getPersistentData().remove("percent_cells");
        }

        if (pokemon.getAspects().contains("ultra-fusion")) { // Necrozma
            new StringSpeciesFeature("prism_fusion", pokemon.getPersistentData().getString("prism_fusion")).apply(pokemon);
            pokemon.getPersistentData().remove("prism_fusion");
        }

        if (pokemon.getSpecies().showdownId().equalsIgnoreCase("greninja") && pokemon.getAspects().contains("ash")) {
            new StringSpeciesFeature("battle_bond", "bond").apply(pokemon);
        }

        if (pokemon.getAspects().contains("gmax")) {
            pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("dynamax_form"));
        }

        if (pokemon.getAspects().contains("stellar-form") || pokemon.getAspects().contains("terastal-form")) { // Terapagos
            new StringSpeciesFeature("tera_form", "normal").apply(pokemon);
        }

        if (pokemon.getEntity() != null) {
            DynamaxEventHandler.scaleDownDynamax(pokemon.getEntity());
            if (!pokemon.getEntity().hasStatusEffect(GTGStatusEffects.DYNAMAX)) { // DMax glow should only be removed after shrinking, only clear tera glow
                pokemon.getEntity().removeStatusEffect(StatusEffects.GLOWING);
            }
        }

        pokemon.getFeatures().removeIf(speciesFeature -> speciesFeature.getName().equalsIgnoreCase("embody_aspect")); // Ogerpon
        pokemon.updateAspects();
        pokemon.getAnyChangeObservable().emit(pokemon);
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

    public static Unit formeChanges (FormeChangeEvent formeChangeEvent) {
        if (formeChangeEvent.getFormeName().equals("x")
            || formeChangeEvent.getFormeName().equals("y")
            || formeChangeEvent.getFormeName().equals("mega")
            || formeChangeEvent.getFormeName().equals("tera")) {
            return Unit.INSTANCE;
        }

        Optional<PokemonEntity> other = StreamSupport.stream(formeChangeEvent.getBattle().getActivePokemon().spliterator(), false)
            .map(ActiveBattlePokemon::getBattlePokemon)
            .filter(active -> formeChangeEvent.getPokemon().getFacedOpponents().contains(active))
            .filter(Objects::nonNull)
            .map(BattlePokemon::getEntity)
            .filter(Objects::nonNull)
            .findAny();

        Pokemon pokemon = formeChangeEvent.getPokemon().getEffectedPokemon();
        if (pokemon.getSpecies().showdownId().equalsIgnoreCase("zygarde") && formeChangeEvent.getFormeName().equalsIgnoreCase("complete")) {
            formeChangeEvent.getBattle().dispatchToFront(() -> {
                if (pokemon.getAspects().contains("10-percent")) {
                    pokemon.getPersistentData().putString("percent_cells", "10");
                } else {
                    pokemon.getPersistentData().putString("percent_cells", "50");
                }
                new StringSpeciesFeature("percent_cells", "complete").apply(pokemon);
                return new UntilDispatch(() -> true);
            });

            if (pokemon.getEntity() != null) {
                EffectsData.run(pokemon.getEntity(), other.orElse(null), formeChangeEvent.getBattle(), MiscUtilsKt.cobblemonResource("zygarde_complete"));
            }
            return Unit.INSTANCE;
        }
        if (pokemon.getSpecies().showdownId().equalsIgnoreCase("greninja") && formeChangeEvent.getFormeName().equalsIgnoreCase("ash")) {
            formeChangeEvent.getBattle().dispatchToFront(() -> {
                new StringSpeciesFeature("battle_bond", "ash").apply(pokemon);
                return new UntilDispatch(() -> true);
            });

            if (pokemon.getEntity() != null) {
                EffectsData.run(pokemon.getEntity(), other.orElse(null), formeChangeEvent.getBattle(), MiscUtilsKt.cobblemonResource("greninja_ash"));
            }
            return Unit.INSTANCE;
        }

        if (pokemon.getEntity() != null) {
            World world = pokemon.getEntity().getWorld();
            world.getRegistryManager().getOptionalWrapper(GTGDynamicRegistryKeys.BATTLE_FORM)
                .flatMap(registry -> registry.getOptional(BattleForm.key(pokemon)))
                .ifPresent(battleForm -> battleForm.value().applyForm(pokemon.getEntity(), other.orElse(null), formeChangeEvent.getBattle(), formeChangeEvent.getFormeName()));
        }

        return Unit.INSTANCE;
    }

    private static Unit heldItemFormChange (HeldItemEvent.Pre heldItemEvent) {
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
        if (player != null && pokemonHealedEvent.getSource() == HealingSource.Force.INSTANCE) {
            for (ItemStack item : player.getEquippedItems()) {
                if (item.isIn(GTGItemTags.BREAKABLE_TERA_ORBS)) {
                    item.setDamage(0);
                    break;
                }
            }
        }
        return Unit.INSTANCE;
    }

    private static Unit pokemonSentOut (PokemonSentPostEvent event) {
        if (Options.shouldApplyBasicTeraGlow() && event.getPokemon().getPersistentData().contains("is_tera")) {
            GlowHandler.applyTeraGlow(event.getPokemonEntity());
        }
        return Unit.INSTANCE;
    }
}
