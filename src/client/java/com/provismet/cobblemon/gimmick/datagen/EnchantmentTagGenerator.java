package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGEnchantments;
import com.provismet.cobblemon.gimmick.util.tag.GTGEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider {
    public EnchantmentTagGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(GTGEnchantmentTags.KEY_ITEM)
            .addOptional(GTGEnchantments.KEY_STONE.getKey())
            .addOptional(GTGEnchantments.Z_RING.getKey())
            .addOptional(GTGEnchantments.DYNAMAX_BAND.getKey())
            .addOptional(GTGEnchantments.TERA_ORB.getKey());
    }
}
