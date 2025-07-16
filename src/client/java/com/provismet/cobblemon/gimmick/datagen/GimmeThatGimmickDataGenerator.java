package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.datagen.debug.DebugEffectsGenerator;
import com.provismet.cobblemon.gimmick.datagen.debug.DebugFormToggleGenerator;
import com.provismet.cobblemon.gimmick.datagen.debug.DebugFusionGenerator;
import com.provismet.cobblemon.gimmick.datagen.debug.DebugHeldItemGenerator;
import com.provismet.cobblemon.gimmick.datagen.debug.DebugMegaStoneGenerator;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantments;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class GimmeThatGimmickDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator (FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EnchantmentGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(EnchantmentTagGenerator::new);
		pack.addProvider(LanguageGenerator::new);
		pack.addProvider(LanguageGeneratorUK::new);
		pack.addProvider(ModelGenerator::new);
		pack.addProvider(BlockLootTableGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(BattleFormGenerator::new);

		// Debug Only - disable when making a proper build
		//this.debugProviders(pack);
	}

	@Override
	public void buildRegistry (RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, GTGEnchantments::bootstrap);
	}

	private void debugProviders (FabricDataGenerator.Pack pack) {
		pack.addProvider(DebugEffectsGenerator::new);
		pack.addProvider(DebugFusionGenerator::new);
		pack.addProvider(DebugHeldItemGenerator::new);
		pack.addProvider(DebugMegaStoneGenerator::new);
		pack.addProvider(DebugFormToggleGenerator::new);
	}
}
