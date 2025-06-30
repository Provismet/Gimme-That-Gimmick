package com.provismet.cobblemon.gimmick.handlers.datapack;

import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.commands.GTGCommands;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class DatapackLoader {
    public static void registerCustomShowdown() {
        GTGCommands.VALID_ITEMS.clear();

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
    }
}
