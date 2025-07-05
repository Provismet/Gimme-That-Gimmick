package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class EffectsDataProvider extends AbstractGTGProvider<EffectsData> {
    public EffectsDataProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, GTGDynamicRegistryKeys.EFFECTS);
    }

    @Override
    public String getName () {
        return "effects data";
    }
}
