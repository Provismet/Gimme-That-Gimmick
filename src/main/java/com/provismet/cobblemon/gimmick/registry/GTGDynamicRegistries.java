package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;

public class GTGDynamicRegistries {
    public static Registry<BattleForm> battleForms;

    public static void loadRegistries (DynamicRegistryManager registryAccess) {
        battleForms = registryAccess.get(GTGDynamicRegistryKeys.BATTLE_FORM);
    }
}
