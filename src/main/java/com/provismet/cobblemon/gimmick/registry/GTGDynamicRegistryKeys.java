package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.codec.FormChangeData;
import com.provismet.cobblemon.gimmick.api.data.codec.FusionData;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import com.provismet.cobblemon.gimmick.api.data.codec.KeyItemData;
import com.provismet.cobblemon.gimmick.api.data.registry.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class GTGDynamicRegistryKeys {
    public static final RegistryKey<Registry<FormChangeData>> FORM_CHANGE = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("form_change"));
    public static final RegistryKey<Registry<KeyItemData>> KEY_ITEM = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("key_items"));
    public static final RegistryKey<Registry<FormChangeFusionDataItem>> FUSION = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("fusions"));
    public static final RegistryKey<Registry<GmaxData>> GMAX = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("gmax"));
    public static final RegistryKey<Registry<HeldItem>> HELD_ITEM = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("held_items"));
    public static final RegistryKey<Registry<MegaStone>> MEGA = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("mega"));

    public static void register() {
        DynamicRegistries.register(FORM_CHANGE, FormChangeData.CODEC);
        DynamicRegistries.register(KEY_ITEM, KeyItemData.CODEC);
        DynamicRegistries.register(FUSION, FormChangeFusionDataItem.CODEC);
        DynamicRegistries.register(GMAX, GmaxData.CODEC);
        DynamicRegistries.register(HELD_ITEM, HeldItem.CODEC);
        DynamicRegistries.register(MEGA, MegaStone.CODEC);
    }
}
