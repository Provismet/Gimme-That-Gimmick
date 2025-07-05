package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import com.provismet.cobblemon.gimmick.api.data.codec.GmaxData;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;

public class GTGDynamicRegistries {
    public static Registry<GmaxData> gmaxRegistry;
    public static Registry<BattleForm> battleForms;

    public static void loadRegistries (DynamicRegistryManager registryAccess) {
        gmaxRegistry = registryAccess.get(GTGDynamicRegistryKeys.GMAX);
        battleForms = registryAccess.get(GTGDynamicRegistryKeys.BATTLE_FORM);
    }
}
