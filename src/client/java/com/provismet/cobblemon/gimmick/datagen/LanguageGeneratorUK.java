package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.lilylib.datagen.provider.LilyLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LanguageGeneratorUK extends LilyLanguageProvider {

    protected LanguageGeneratorUK (FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_gb", registryLookup);
    }

    @Override
    public void generateTranslations (RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        LanguageGenerator.addItemWithTooltip(translationBuilder, GTGItems.ROTOM_CATALOG, "Rotom Catalogue", "A catalogue of appliances for Rotom to possess");
        LanguageGenerator.zMove(translationBuilder, "zacidarmor", "Z-Acid Armour", "Resets the user's lowered stats in addition to its usual effect.");
    }
}
