package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.FusionData;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import com.provismet.cobblemon.gimmick.api.data.codec.HeldItemData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.codec.MegaData;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class GTGDynamicRegistryKeys {
    public static final RegistryKey<Registry<FormChangeData>> FORM_CHANGE_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("form_change"));
    public static final RegistryKey<Registry<KeyItemData>> KEY_ITEM_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("key_items"));
    public static final RegistryKey<Registry<FusionData>> FUSION_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("fusions"));
    public static final RegistryKey<Registry<GmaxData>> GMAX_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("gmax"));
    public static final RegistryKey<Registry<HeldItemData>> HELD_ITEM_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("held_items"));
    public static final RegistryKey<Registry<MegaData>> MEGA_REGISTRY_KEY = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("mega"));

    public static void register() {
        DynamicRegistries.register(FORM_CHANGE_REGISTRY_KEY, FormChangeData.CODEC);
        DynamicRegistries.register(KEY_ITEM_REGISTRY_KEY, KeyItemData.CODEC);
        DynamicRegistries.register(FUSION_REGISTRY_KEY, FusionData.CODEC);
        DynamicRegistries.register(GMAX_REGISTRY_KEY, GmaxData.CODEC);
        DynamicRegistries.register(HELD_ITEM_REGISTRY_KEY, HeldItemData.CODEC);
        DynamicRegistries.register(MEGA_REGISTRY_KEY, MegaData.CODEC);
    }
}
