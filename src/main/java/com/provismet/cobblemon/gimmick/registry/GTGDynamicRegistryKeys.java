package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeToggleDataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;

public class GTGDynamicRegistryKeys {
    public static final RegistryKey<Registry<BattleForm>> BATTLE_FORM = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("battle_form"));
    public static final RegistryKey<Registry<FormChangeToggleDataItem>> FORM_TOGGLE = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("item/key_item/form_toggle"));
    public static final RegistryKey<Registry<FormChangeFusionDataItem>> FUSION = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("item/key_item/fusion"));
    public static final RegistryKey<Registry<HeldItem>> HELD_ITEM = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("item/held_items"));
    public static final RegistryKey<Registry<MegaStone>> MEGASTONE = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("item/megastone"));
    public static final RegistryKey<Registry<EffectsData>> EFFECTS = RegistryKey.ofRegistry(GimmeThatGimmickMain.identifier("effects"));

    public static void register() {
        DynamicRegistries.register(BATTLE_FORM, BattleForm.CODEC);
        DynamicRegistries.register(FORM_TOGGLE, FormChangeToggleDataItem.CODEC);
        DynamicRegistries.register(FUSION, FormChangeFusionDataItem.CODEC);
        DynamicRegistries.register(HELD_ITEM, HeldItem.CODEC);
        DynamicRegistries.register(MEGASTONE, MegaStone.CODEC);
        DynamicRegistries.register(EFFECTS, EffectsData.CODEC);
    }
}
