package com.provismet.cobblemon.gimmick.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.registry.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.IdentifierArgumentType;
import net.minecraft.command.argument.RegistryEntryReferenceArgumentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GTGCommands {
    public static final List<String> VALID_ITEMS = new ArrayList<>();

    public static void register () {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("gimme-that-gimmick")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("give")
                    .then(CommandManager.argument("player", EntityArgumentType.player())
                        .then(CommandManager.literal("fusion")
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

                                    if (item.isPresent()) return executeGiveFusion(
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

                                        if (item.isPresent()) return executeGiveFusion(
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
                        .then(CommandManager.argument("item", StringArgumentType.word())
                            .suggests((context, builder) -> {
                                for (String item : VALID_ITEMS) {
                                    builder.suggest(item);
                                }
                                return builder.buildFuture();
                            })
                            // version with no count
                            .executes(context -> {
                                ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                String item = StringArgumentType.getString(context, "item");
                                return executeGive(player, item, 1);
                            })
                            // version with count
                            .then(CommandManager.argument("count", IntegerArgumentType.integer(1))
                                .executes(context -> {
                                    ServerPlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                    String item = StringArgumentType.getString(context, "item");
                                    int count = IntegerArgumentType.getInteger(context, "count");
                                    return executeGive(player, item, count);
                                })
                            )
                        )
                    )
                )
            );
        });
    }

    private static int executeGiveFusion (CommandContext<ServerCommandSource> context, ServerPlayerEntity player, FormChangeFusionDataItem item, int count) {
        ItemStack stack = item.create();
        for (int i = 0; i < count; ++i) {
            player.giveItemStack(stack);
        }
        context.getSource().sendFeedback(() -> Text.literal("Gave " + count + " ").append(stack.getName()).append(" to ").append(player.getName()), false);
        return 1;
    }

    private static int executeGive(ServerPlayerEntity player, String item, int count) {
        //MEGA
        for (MegaStone pokemon : GTGDynamicRegistries.megaRegistry) {
            if (pokemon.gtg_id().equals(item)) {
                item = pokemon.item_id();
                if (VALID_ITEMS.contains(item)) {
                    player.sendMessage(Text.literal("Invalid item: " + item).formatted(Formatting.RED), false);
                    return 0;
                }
                String[] itemId = item.split(":");
                Identifier msdItemId = Identifier.of(itemId[0], itemId[1]);
                Item msdItem = Registries.ITEM.get(msdItemId);
                ItemStack stack = new ItemStack(msdItem, count);
                stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(pokemon.custom_model_data()));
                stack.set(DataComponentTypes.CUSTOM_NAME, Text.translatable(pokemon.item_name()));
                List<Text> lore = new ArrayList<>();
                for (String line : pokemon.item_description()) {
                    lore.add(Text.translatable(line));
                }
                stack.set(DataComponentTypes.LORE, new LoreComponent(lore));
                player.giveItemStack(stack);
                player.sendMessage(Text.literal("You received: " + item).formatted(Formatting.GREEN), false);

                return 1;
            }
        }

        //HELD ITEMS
        for (HeldItem items : GTGDynamicRegistries.heldItemsRegistry) {
            if (items.gtg_id().equals(item)) {
                item = items.item_id();
                if (VALID_ITEMS.contains(item)) {
                    player.sendMessage(Text.literal("Invalid item: " + item).formatted(Formatting.RED), false);
                    return 0;
                }
                String[] itemId = item.split(":");
                Identifier msdItemId = Identifier.of(itemId[0], itemId[1]);
                Item msdItem = Registries.ITEM.get(msdItemId);
                ItemStack stack = new ItemStack(msdItem, count);
                stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(items.custom_model_data()));
                stack.set(DataComponentTypes.CUSTOM_NAME, Text.translatable(items.item_name()));
                List<Text> lore = new ArrayList<>();
                for (String line : items.item_description()) {
                    lore.add(Text.translatable(line));
                }
                stack.set(DataComponentTypes.LORE, new LoreComponent(lore));
                player.giveItemStack(stack);
                player.sendMessage(Text.literal("You received: " + item).formatted(Formatting.GREEN), false);

                return 1;
            }
        }

        //FORME CHANGE
        for (FormChangeData items : GTGDynamicRegistries.formChangeRegistry) {
            if (items.gtg_id().equals(item)) {
                item = items.item_id();
                if (VALID_ITEMS.contains(item)) {
                    player.sendMessage(Text.literal("Invalid item: " + item).formatted(Formatting.RED), false);
                    return 0;
                }
                String[] itemId = item.split(":");
                Identifier msdItemId = Identifier.of(itemId[0], itemId[1]);
                Item msdItem = Registries.ITEM.get(msdItemId);
                ItemStack stack = new ItemStack(msdItem, count);
                stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(items.custom_model_data()));
                stack.set(DataComponentTypes.CUSTOM_NAME, Text.translatable(items.item_name()));
                List<Text> lore = new ArrayList<>();
                for (String line : items.item_description()) {
                    lore.add(Text.translatable(line));
                }
                stack.set(DataComponentTypes.LORE, new LoreComponent(lore));
                player.giveItemStack(stack);
                player.sendMessage(Text.literal("You received: " + item).formatted(Formatting.GREEN), false);

                return 1;
            }
        }

        //KEY ITEMS
        for (KeyItemData keyItems : GTGDynamicRegistries.keyItemsRegistry) {
            if (keyItems.gtg_id().equals(item)) {
                item = keyItems.item_id();
                if (VALID_ITEMS.contains(item)) {
                    player.sendMessage(Text.literal("Invalid item: " + item).formatted(Formatting.RED), false);
                    return 0;
                }
                String[] itemId = item.split(":");
                Identifier msdItemId = Identifier.of(itemId[0], itemId[1]);
                Item msdItem = Registries.ITEM.get(msdItemId);
                ItemStack stack = new ItemStack(msdItem, count);
                stack.set(DataComponentTypes.CUSTOM_MODEL_DATA, new CustomModelDataComponent(keyItems.custom_model_data()));
                stack.set(DataComponentTypes.CUSTOM_NAME, Text.translatable(keyItems.item_name()));
                List<Text> lore = new ArrayList<>();
                for (String line : keyItems.item_description()) {
                    lore.add(Text.translatable(line));
                }
                stack.set(DataComponentTypes.LORE, new LoreComponent(lore));
                player.giveItemStack(stack);
                player.sendMessage(Text.literal("You received: " + item).formatted(Formatting.GREEN), false);

                return 1;
            }
        }
        return 0;
    }
}