package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class FormChangeFusionItemProvider extends AbstractGTGProvider<FormChangeFusionDataItem> {
    public FormChangeFusionItemProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, GTGDynamicRegistryKeys.FUSION);
    }

    @Override
    public String getName () {
        return "form change fusion item";
    }
}
