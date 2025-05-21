package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGEnchantments;
import com.provismet.lilylib.datagen.provider.LilyEnchantmentProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnchantmentGenerator extends LilyEnchantmentProvider {
    protected EnchantmentGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, EnchantmentBuilder enchantmentBuilder) {
        enchantmentBuilder.add(GTGEnchantments.KEY_STONE);
        enchantmentBuilder.add(GTGEnchantments.Z_RING);
        enchantmentBuilder.add(GTGEnchantments.DYNAMAX_BAND);
        enchantmentBuilder.add(GTGEnchantments.TERA_ORB);
    }
}
