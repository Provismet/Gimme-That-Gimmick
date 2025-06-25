package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    protected BlockLootTableGenerator (FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate () {
        this.addDrop(GTGBlocks.POWER_SPOT);
        this.addDrop(GTGBlocks.MAX_MUSHROOM, GTGItems.MAX_MUSHROOM);
    }
}
