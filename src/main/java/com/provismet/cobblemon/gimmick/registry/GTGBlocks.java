package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.block.GenericPolymerTexturedBlock;
import com.provismet.cobblemon.gimmick.block.MaxMushroomBlock;
import com.provismet.cobblemon.gimmick.block.MeteoriteBlock;
import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;

public abstract class GTGBlocks {
    public static final Block POWER_SPOT = register("power_spot", BlockModelType.FULL_BLOCK, AbstractBlock.Settings.create().solid().mapColor(MapColor.DULL_RED).strength(10f, 6f), GenericPolymerTexturedBlock::new);
    public static final Block METEORITE = register("meteorite", AbstractBlock.Settings.create().solid().mapColor(MapColor.GRAY).strength(10f, 6f), MeteoriteBlock::new);
    public static final Block MAX_MUSHROOM = register("max_mushroom", AbstractBlock.Settings.create().noCollision().mapColor(MapColor.DULL_RED), MaxMushroomBlock::new);

    private static Block register (String name, BlockModelType modelType, AbstractBlock.Settings settings, TriFunction<AbstractBlock.Settings, Identifier, BlockModelType, GenericPolymerTexturedBlock> constructor) {
        Identifier id = GimmeThatGimmickMain.identifier(name);
        return Registry.register(Registries.BLOCK, id, constructor.apply(settings, id, modelType));
    }

    private static <T extends Block & PolymerTexturedBlock> Block register (String name, AbstractBlock.Settings settings, BiFunction<AbstractBlock.Settings, Identifier, T> constructor) {
        Identifier id = GimmeThatGimmickMain.identifier(name);
        return Registry.register(Registries.BLOCK, id, constructor.apply(settings, id));
    }

    public static void init () {}
}
