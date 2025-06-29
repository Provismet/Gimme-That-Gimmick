package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class FormChangeFusionItemProvider extends FabricDynamicRegistryProvider {
    public FormChangeFusionItemProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
        BiConsumer<Identifier, FormChangeFusionDataItem> consumer = (id, fusion) -> entries.add(RegistryKey.of(GTGDynamicRegistryKeys.FUSION, id), fusion);
        this.generate(wrapperLookup, consumer);
    }

    @Override
    public String getName () {
        return "form change fusion item";
    }

    protected abstract void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, FormChangeFusionDataItem> consumer);
}
