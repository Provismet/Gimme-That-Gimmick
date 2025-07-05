package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class BattleFormProvider extends AbstractGTGProvider<BattleForm> {
    public BattleFormProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, GTGDynamicRegistryKeys.BATTLE_FORM);
    }

    @Override
    public String getName () {
        return "battle form";
    }
}
