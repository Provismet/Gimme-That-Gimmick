package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeToggleDataItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class FormChangeToggleItemProvider extends AbstractGTGProvider<FormChangeToggleDataItem> {
    public FormChangeToggleItemProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, GTGDynamicRegistryKeys.FORM_TOGGLE);
    }

    @Override
    public String getName () {
        return "form change toggle item";
    }
}
