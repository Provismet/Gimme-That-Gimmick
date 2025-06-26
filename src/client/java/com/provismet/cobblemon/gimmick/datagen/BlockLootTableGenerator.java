package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.block.MaxMushroomBlock;
import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootTableGenerator extends FabricBlockLootTableProvider {
    protected BlockLootTableGenerator (FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate () {
        RegistryWrapper.Impl<Enchantment> enchantmentLookup = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        this.addDrop(GTGBlocks.POWER_SPOT);
        this.addDrop(GTGBlocks.METEORITE);
        this.addDrop(
            GTGBlocks.MAX_MUSHROOM,
            this.applyExplosionDecay(
                GTGBlocks.MAX_MUSHROOM,
                LootTable.builder()
                    .pool(LootPool.builder().with(ItemEntry.builder(GTGItems.MAX_MUSHROOM)))
                    .pool(
                        LootPool.builder()
                            .conditionally(
                                BlockStatePropertyLootCondition.builder(GTGBlocks.MAX_MUSHROOM)
                                    .properties(StatePredicate.Builder.create().exactMatch(MaxMushroomBlock.AGE, MaxMushroomBlock.MAX_AGE)))
                            .with(
                                ItemEntry.builder(GTGItems.MAX_MUSHROOM)
                                    .apply(ApplyBonusLootFunction.binomialWithBonusCount(enchantmentLookup.getOrThrow(Enchantments.FORTUNE), 0.5714286f, 2)))
                    )
            )
        );
    }
}
