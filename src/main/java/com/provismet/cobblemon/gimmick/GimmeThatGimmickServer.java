package com.provismet.cobblemon.gimmick;

import com.provismet.cobblemon.gimmick.handlers.CobblemonEventHandler;
import com.provismet.cobblemon.gimmick.handlers.DynamaxEventHandler;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemGroup;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.DedicatedServerModInitializer;

public class GimmeThatGimmickServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer () {
        PolymerResourcePackUtils.markAsRequired();
        PolymerResourcePackUtils.addModAssets(GimmeThatGimmickMain.MODID);

        GTGItems.init();
        GTGItemDataComponents.init();
        GTGEnchantmentComponents.init();
        GTGItemGroup.register();

        CobblemonEventHandler.register();
        DynamaxEventHandler.register();
    }
}
