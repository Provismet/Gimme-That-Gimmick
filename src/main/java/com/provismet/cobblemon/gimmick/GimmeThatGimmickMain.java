package com.provismet.cobblemon.gimmick;

import com.provismet.cobblemon.gimmick.handlers.datapack.ItemHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
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
    public void onInitialize() {
        UseItemCallback.EVENT.register(ItemHandler::useItem);
    }
}