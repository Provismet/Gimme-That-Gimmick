package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;

public class GTGDynamicRegistries {
    public static Registry<KeyItemData> keyItemsRegistry;
    public static Registry<FormChangeData> formChangeRegistry;
    public static Registry<GmaxData> gmaxRegistry;
    public static Registry<HeldItem> heldItemsRegistry;
    public static Registry<MegaStone> megaRegistry;

    public static void loadRegistries (DynamicRegistryManager registryAccess) {
        keyItemsRegistry = registryAccess.get(GTGDynamicRegistryKeys.KEY_ITEM);
        formChangeRegistry = registryAccess.get(GTGDynamicRegistryKeys.FORM_CHANGE);
        gmaxRegistry = registryAccess.get(GTGDynamicRegistryKeys.GMAX);
        heldItemsRegistry = registryAccess.get(GTGDynamicRegistryKeys.HELD_ITEM);
        megaRegistry = registryAccess.get(GTGDynamicRegistryKeys.MEGA);
    }
}
