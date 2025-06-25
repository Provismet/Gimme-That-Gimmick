package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.block.AbstractPolymerTexturedBlock;
import com.provismet.cobblemon.gimmick.block.MaxMushroomBlock;
import com.provismet.cobblemon.gimmick.block.PowerSpotBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;

public abstract class GTGBlocks {
    public static final Block POWER_SPOT = register("power_spot", AbstractBlock.Settings.create().solid().mapColor(MapColor.DULL_RED), PowerSpotBlock::new);
    public static final Block MAX_MUSHROOM = register("max_mushroom", AbstractBlock.Settings.create().noCollision().mapColor(MapColor.DULL_RED), MaxMushroomBlock::new);

    private static Block register (String name, AbstractBlock.Settings settings, BiFunction<AbstractBlock.Settings, Identifier, AbstractPolymerTexturedBlock> constructor) {
        Identifier id = GimmeThatGimmickMain.identifier(name);
        return Registry.register(Registries.BLOCK, id, constructor.apply(settings, id));
    }

    public static void init () {}
}
