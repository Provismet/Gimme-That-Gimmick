package com.provismet.cobblemon.gimmick.api.datagen;

import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public abstract class HeldItemProvider extends AbstractGTGProvider<HeldItem> {
    public HeldItemProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture, GTGDynamicRegistryKeys.HELD_ITEM);
    }

    @Override
    public String getName () {
        return "held item";
    }
}
