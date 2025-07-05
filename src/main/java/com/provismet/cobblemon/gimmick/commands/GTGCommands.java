package com.provismet.cobblemon.gimmick.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.provismet.cobblemon.gimmick.api.data.DataItemStack;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeToggleDataItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class GTGCommands {
    public static void register () {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("gimme-that-gimmick")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("give")
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.literal("fusion_form")
                            .then(CommandManager.argument("item", IdentifierArgumentType.identifier())
                                .suggests((context, builder) -> {
                                    registryAccess.getWrapperOrThrow(GTGDynamicRegistryKeys.FUSION)
                                        .streamEntries()
                                        .filter(RegistryEntry.Reference::hasKeyAndValue)
                                        .forEach(entry -> builder.suggest(entry.getIdAsString()));

                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                    Optional<RegistryEntry.Reference<FormChangeFusionDataItem>> item = registryAccess
                                        .getWrapperOrThrow(GTGDynamicRegistryKeys.FUSION)
                                        .getOptional(FormChangeFusionDataItem.key(itemId));

                                    if (item.isPresent()) return executeGive(
                                        context,
                                        EntityArgumentType.getPlayer(context, "player"),
                                        item.get().value(),
                                        1
                                    );

                                    context.getSource().sendFeedback(() -> Text.literal("Fusion item " + itemId.toString() + " not found."), false);
                                    return 0;
                                })
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                        Optional<RegistryEntry.Reference<FormChangeFusionDataItem>> item = registryAccess
                                            .getWrapperOrThrow(GTGDynamicRegistryKeys.FUSION)
                                            .getOptional(FormChangeFusionDataItem.key(itemId));

                                        if (item.isPresent()) return executeGive(
                                            context,
                                            EntityArgumentType.getPlayer(context, "player"),
                                            item.get().value(),
                                            IntegerArgumentType.getInteger(context, "count")
                                        );

                                        context.getSource().sendFeedback(() -> Text.literal("Fusion item " + itemId.toString() + " not found."), false);
                                        return 0;
                                    })
                                )
                            )
                        )
                        .then(CommandManager.literal("toggle_form")
                            .then(CommandManager.argument("item", IdentifierArgumentType.identifier())
                                .suggests((context, builder) -> {
                                    registryAccess.getWrapperOrThrow(GTGDynamicRegistryKeys.FORM_TOGGLE)
                                        .streamEntries()
                                        .filter(RegistryEntry.Reference::hasKeyAndValue)
                                        .forEach(entry -> builder.suggest(entry.getIdAsString()));

                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                    Optional<RegistryEntry.Reference<FormChangeToggleDataItem>> item = registryAccess
                                        .getWrapperOrThrow(GTGDynamicRegistryKeys.FORM_TOGGLE)
                                        .getOptional(FormChangeToggleDataItem.key(itemId));

                                    if (item.isPresent()) return executeGive(
                                        context,
                                        EntityArgumentType.getPlayer(context, "player"),
                                        item.get().value(),
                                        1
                                    );

                                    context.getSource().sendFeedback(() -> Text.literal("Form toggle item " + itemId.toString() + " not found."), false);
                                    return 0;
                                })
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                        Optional<RegistryEntry.Reference<FormChangeToggleDataItem>> item = registryAccess
                                            .getWrapperOrThrow(GTGDynamicRegistryKeys.FORM_TOGGLE)
                                            .getOptional(FormChangeToggleDataItem.key(itemId));

                                        if (item.isPresent()) return executeGive(
                                            context,
                                            EntityArgumentType.getPlayer(context, "player"),
                                            item.get().value(),
                                            IntegerArgumentType.getInteger(context, "count")
                                        );

                                        context.getSource().sendFeedback(() -> Text.literal("Form toggle item " + itemId.toString() + " not found."), false);
                                        return 0;
                                    })
                                )
                            )
                        )
                        .then(CommandManager.literal("megastone")
                            .then(CommandManager.argument("item", IdentifierArgumentType.identifier())
                                .suggests((context, builder) -> {
                                    registryAccess.getWrapperOrThrow(GTGDynamicRegistryKeys.MEGASTONE)
                                        .streamEntries()
                                        .filter(RegistryEntry.Reference::hasKeyAndValue)
                                        .forEach(entry -> builder.suggest(entry.getIdAsString()));

                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                    Optional<RegistryEntry.Reference<MegaStone>> item = registryAccess
                                        .getWrapperOrThrow(GTGDynamicRegistryKeys.MEGASTONE)
                                        .getOptional(MegaStone.key(itemId));

                                    if (item.isPresent()) return executeGive(
                                        context,
                                        EntityArgumentType.getPlayer(context, "player"),
                                        item.get().value(),
                                        1
                                    );

                                    context.getSource().sendFeedback(() -> Text.literal("MegaStone item " + itemId.toString() + " not found."), false);
                                    return 0;
                                })
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                        Optional<RegistryEntry.Reference<MegaStone>> item = registryAccess
                                            .getWrapperOrThrow(GTGDynamicRegistryKeys.MEGASTONE)
                                            .getOptional(MegaStone.key(itemId));

                                        if (item.isPresent()) return executeGive(
                                            context,
                                            EntityArgumentType.getPlayer(context, "player"),
                                            item.get().value(),
                                            IntegerArgumentType.getInteger(context, "count")
                                        );

                                        context.getSource().sendFeedback(() -> Text.literal("MegaStone item " + itemId.toString() + " not found."), false);
                                        return 0;
                                    })
                                )
                            )
                        )
                        .then(CommandManager.literal("held_item")
                            .then(CommandManager.argument("item", IdentifierArgumentType.identifier())
                                .suggests((context, builder) -> {
                                    registryAccess.getWrapperOrThrow(GTGDynamicRegistryKeys.HELD_ITEM)
                                        .streamEntries()
                                        .filter(RegistryEntry.Reference::hasKeyAndValue)
                                        .forEach(entry -> builder.suggest(entry.getIdAsString()));

                                    return builder.buildFuture();
                                })
                                .executes(context -> {
                                    Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                    Optional<RegistryEntry.Reference<HeldItem>> item = registryAccess
                                        .getWrapperOrThrow(GTGDynamicRegistryKeys.HELD_ITEM)
                                        .getOptional(HeldItem.key(itemId));

                                    if (item.isPresent()) return executeGive(
                                        context,
                                        EntityArgumentType.getPlayer(context, "player"),
                                        item.get().value(),
                                        1
                                    );

                                    context.getSource().sendFeedback(() -> Text.literal("Held item " + itemId.toString() + " not found."), false);
                                    return 0;
                                })
                                .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                    .executes(context -> {
                                        Identifier itemId = IdentifierArgumentType.getIdentifier(context, "item");
                                        Optional<RegistryEntry.Reference<HeldItem>> item = registryAccess
                                            .getWrapperOrThrow(GTGDynamicRegistryKeys.HELD_ITEM)
                                            .getOptional(HeldItem.key(itemId));

                                        if (item.isPresent()) return executeGive(
                                            context,
                                            EntityArgumentType.getPlayer(context, "player"),
                                            item.get().value(),
                                            IntegerArgumentType.getInteger(context, "count")
                                        );

                                        context.getSource().sendFeedback(() -> Text.literal("Held item " + itemId.toString() + " not found."), false);
                                        return 0;
                                    })
                                )
                            )
                        )
                    )
                )
            );
        });
    }

    private static int executeGive (CommandContext<ServerCommandSource> context, ServerPlayerEntity player, DataItemStack item, int count) {
        ItemStack stack = item.create();
        if (stack == null) {
            context.getSource().sendFeedback(() -> Text.literal("Failed to generate requested item, the data may be malformed."), false);
            return 0;
        }

        for (int i = 0; i < count; ++i) {
            player.giveItemStack(stack);
        }
        context.getSource().sendFeedback(() -> Text.literal("Gave " + count + " ").append(item.name()).append(" to ").append(player.getName()), false);
        return 1;
    }
}