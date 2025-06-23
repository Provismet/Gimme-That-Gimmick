package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.block.PowerSpotBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public abstract class GTGBlocks {
    public static final Block POWER_SPOT = Registry.register(Registries.BLOCK, GimmeThatGimmickMain.identifier("power_spot"),
            new PowerSpotBlock(
                    AbstractBlock.Settings.create()
                            .solid()
                            .mapColor(MapColor.DULL_RED),
                    GimmeThatGimmickMain.identifier("power_spot")
            )
    );

    public static void init() {
    }
}
