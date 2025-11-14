package com.provismet.cobblemon.gimmick;

import com.provismet.cobblemon.gimmick.commands.GTGCommands;
import com.provismet.cobblemon.gimmick.handlers.BattleEventHandler;
import com.provismet.cobblemon.gimmick.handlers.CobblemonEventHandler;
import com.provismet.cobblemon.gimmick.handlers.DynamaxEventHandler;
import com.provismet.cobblemon.gimmick.handlers.UltraBurstEventHandler;
import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistries;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantmentComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemDataComponents;
import com.provismet.cobblemon.gimmick.registry.GTGItemGroup;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.registry.GTGStatusEffects;
import com.provismet.cobblemon.gimmick.util.DelayedTicker;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GimmeThatGimmickMain implements ModInitializer {
    public static final String MODID = "gimme-that-gimmick";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public static Identifier identifier(String path) {
        return Identifier.of(MODID, path);
    }

    @Override
    public void onInitialize () {
        PolymerResourcePackUtils.addModAssets(GimmeThatGimmickMain.MODID);

        GTGDynamicRegistryKeys.register();

        GTGItems.init();
        GTGBlocks.init();
        GTGItemDataComponents.init();
        GTGEnchantmentComponents.init();
        GTGStatusEffects.init();
        GTGItemGroup.register();
        GTGCommands.register();

        CobblemonEventHandler.register();
        UltraBurstEventHandler.register();
        DynamaxEventHandler.register();
        BattleEventHandler.register();

        ServerLifecycleEvents.SERVER_STARTED.register(server -> GTGDynamicRegistries.loadRegistries(server.getRegistryManager()));
        ServerTickEvents.START_SERVER_TICK.register(server -> DelayedTicker.runAll());
    }
}