package com.provismet.cobblemon.gimmick;

import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemGroup;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class GimmeThatGimmickClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			GTGItems.init();
			GTGItemDataComponents.init();
			GTGEnchantmentComponents.init();
			GTGItemGroup.register();
		}
	}
}