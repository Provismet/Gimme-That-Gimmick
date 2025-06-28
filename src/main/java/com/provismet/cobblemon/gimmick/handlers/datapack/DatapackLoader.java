package com.provismet.cobblemon.gimmick.handlers.datapack;

import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.FusionData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import com.provismet.cobblemon.gimmick.commands.GTGCommands;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DatapackLoader {
    public static void registerCustomShowdown() {
        GTGCommands.VALID_ITEMS.clear();

        //MEGA
        for (MegaStone pokemon : GTGDynamicRegistries.megaRegistry) {
            //COMMAND UTILS
            GTGCommands.VALID_ITEMS.add(pokemon.gtg_id());
            //

            Identifier custom_stone_item_id = Identifier.tryParse(pokemon.item_id());
            Item customStone = Registries.ITEM.get(custom_stone_item_id);
            CobblemonHeldItemManager.INSTANCE.registerStackRemap(stack -> {
                if (stack.getItem().equals(customStone) &&
                        ((stack.get(DataComponentTypes.CUSTOM_MODEL_DATA) != null &&
                                stack.get(DataComponentTypes.CUSTOM_MODEL_DATA).value() == pokemon.custom_model_data()) ||
                                pokemon.custom_model_data() == 0)) {
                    return pokemon.showdown_id();
                }
                return null;
            });
        }

        //HELD ITEMS
        for (HeldItem items : GTGDynamicRegistries.heldItemsRegistry) {
            //COMMAND UTILS
            GTGCommands.VALID_ITEMS.add(items.gtg_id());
            //

            String[] parts = items.item_id().split(":");
            Identifier custom_held_item_id = Identifier.of(parts[0], parts[1]);
            Item customHeldItem = Registries.ITEM.get(custom_held_item_id);

            CobblemonHeldItemManager.INSTANCE.registerStackRemap(stack -> {
                if (stack.getItem().equals(customHeldItem) &&
                        ((stack.get(DataComponentTypes.CUSTOM_MODEL_DATA) != null &&
                                stack.get(DataComponentTypes.CUSTOM_MODEL_DATA).value() == items.custom_model_data()) || items.custom_model_data() == 0)) {
                    return items.showdown_id();
                }
                return null;
            });
        }

        //BATTLE ONLY FORME CHANGE
        for (FormChangeData items : GTGDynamicRegistries.formChangeRegistry) {
            //COMMAND UTILS
            GTGCommands.VALID_ITEMS.add(items.gtg_id());
            //

            if (items.battle_mode_only()) {
                String[] parts = items.item_id().split(":");
                Identifier custom_held_item_id = Identifier.of(parts[0], parts[1]);
                Item customHeldItem = Registries.ITEM.get(custom_held_item_id);

                CobblemonHeldItemManager.INSTANCE.registerStackRemap(stack -> {
                    if (stack.getItem().equals(customHeldItem) &&
                            ((stack.get(DataComponentTypes.CUSTOM_MODEL_DATA) != null &&
                                    stack.get(DataComponentTypes.CUSTOM_MODEL_DATA).value() == items.custom_model_data())
                                    || items.custom_model_data() == 0)) {
                        return items.showdown_id();
                    }
                    return null;
                });
            }
        }

        //FUSIONS
        for (FusionData fusion : GTGDynamicRegistries.fusionRegistry) {
            GTGCommands.VALID_ITEMS.add(fusion.gtg_id());
        }

        //KEY ITEMS
        for (KeyItemData keyItems : GTGDynamicRegistries.keyItemsRegistry) {
            GTGCommands.VALID_ITEMS.add(keyItems.gtg_id());
        }
    }
}
