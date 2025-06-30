package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;

public class GTGDynamicRegistries {
    public static Registry<FormChangeData> formChangeRegistry;
    public static Registry<GmaxData> gmaxRegistry;

    public static void loadRegistries (DynamicRegistryManager registryAccess) {
        formChangeRegistry = registryAccess.get(GTGDynamicRegistryKeys.FORM_CHANGE);
        gmaxRegistry = registryAccess.get(GTGDynamicRegistryKeys.GMAX);
    }
}
