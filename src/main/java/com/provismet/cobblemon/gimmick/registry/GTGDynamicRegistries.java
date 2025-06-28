package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.FusionData;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class GTGDynamicRegistries {
    public static Registry<KeyItemData> keyItemsRegistry;
    public static Registry<FormChangeData> formChangeRegistry;
    public static Registry<FusionData> fusionRegistry;
    public static Registry<GmaxData> gmaxRegistry;
    public static Registry<HeldItem> heldItemsRegistry;
    public static Registry<MegaStone> megaRegistry;

    public static void loadRegistries(DynamicRegistryManager registryAccess) {
        final RegistryKey<Registry<KeyItemData>> KEY_ITEMS_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("key_items"));
        final RegistryKey<Registry<FormChangeData>> FORM_CHANGE_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("form_change"));
        final RegistryKey<Registry<FusionData>> FUSION_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("fusions"));
        final RegistryKey<Registry<GmaxData>> GMAX_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("gmax"));
        final RegistryKey<Registry<HeldItem>> HELD_ITEMS_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("held_items"));
        final RegistryKey<Registry<MegaStone>> MEGA_REGISTRY_KEY =
                RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("mega"));

        keyItemsRegistry = registryAccess.get(KEY_ITEMS_REGISTRY_KEY);
        formChangeRegistry = registryAccess.get(FORM_CHANGE_REGISTRY_KEY);
        fusionRegistry = registryAccess.get(FUSION_REGISTRY_KEY);
        gmaxRegistry = registryAccess.get(GMAX_REGISTRY_KEY);
        heldItemsRegistry = registryAccess.get(HELD_ITEMS_REGISTRY_KEY);
        megaRegistry = registryAccess.get(MEGA_REGISTRY_KEY);
    }
}
