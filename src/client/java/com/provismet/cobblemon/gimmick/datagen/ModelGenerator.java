package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

import java.util.function.Consumer;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator (FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels (BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(GTGBlocks.POWER_SPOT);
        blockStateModelGenerator.registerSimpleCubeAll(GTGBlocks.METEORITE);
    }

    @Override
    public void generateItemModels (ItemModelGenerator itemModelGenerator) {
        Consumer<Item> register = item -> itemModelGenerator.register(item, Models.GENERATED);
        this.createKeyItems(register);
        this.createMegaStones(register);
        this.createZCrystals(register);
        this.createDynamax(register);
        this.createTeraShards(register);
        this.createFormChanges(register);
    }

    private void createKeyItems (Consumer<Item> register) {
        register.accept(GTGItems.MEGA_BRACELET);
        register.accept(GTGItems.Z_RING);
        register.accept(GTGItems.DYNAMAX_BAND);
        register.accept(GTGItems.TERA_ORB);
    }

    private void createMegaStones (Consumer<Item> register) {
        register.accept(GTGItems.ABOMASITE);
        register.accept(GTGItems.ABSOLITE);
        register.accept(GTGItems.AERODACTYLITE);
        register.accept(GTGItems.AGGRONITE);
        register.accept(GTGItems.ALAKAZITE);
        register.accept(GTGItems.ALTARIANITE);
        register.accept(GTGItems.AMPHAROSITE);
        register.accept(GTGItems.AUDINITE);
        register.accept(GTGItems.BANETTITE);
        register.accept(GTGItems.BEEDRILLITE);
        register.accept(GTGItems.BLASTOISINITE);
        register.accept(GTGItems.BLAZIKENITE);
        register.accept(GTGItems.CAMERUPTITE);
        register.accept(GTGItems.CHARIZARDITE_X);
        register.accept(GTGItems.CHARIZARDITE_Y);
        register.accept(GTGItems.DIANCITE);
        register.accept(GTGItems.GALLADITE);
        register.accept(GTGItems.GARCHOMPITE);
        register.accept(GTGItems.GARDEVOIRITE);
        register.accept(GTGItems.GENGARITE);
        register.accept(GTGItems.GLALITITE);
        register.accept(GTGItems.GYARADOSITE);
        register.accept(GTGItems.HERACRONITE);
        register.accept(GTGItems.HOUNDOOMINITE);
        register.accept(GTGItems.KANGASKHANITE);
        register.accept(GTGItems.LATIASITE);
        register.accept(GTGItems.LATIOSITE);
        register.accept(GTGItems.LOPUNNITE);
        register.accept(GTGItems.LUCARIONITE);
        register.accept(GTGItems.MANECTITE);
        register.accept(GTGItems.MAWILITE);
        register.accept(GTGItems.MEDICHAMITE);
        register.accept(GTGItems.METAGROSSITE);
        register.accept(GTGItems.MEWTWONITE_X);
        register.accept(GTGItems.MEWTWONITE_Y);
        register.accept(GTGItems.PIDGEOTITE);
        register.accept(GTGItems.PINSIRITE);
        register.accept(GTGItems.SABLENITE);
        register.accept(GTGItems.SALAMENCITE);
        register.accept(GTGItems.SCEPTILITE);
        register.accept(GTGItems.SCIZORITE);
        register.accept(GTGItems.SHARPEDONITE);
        register.accept(GTGItems.SLOWBRONITE);
        register.accept(GTGItems.STEELIXITE);
        register.accept(GTGItems.SWAMPERTITE);
        register.accept(GTGItems.TYRANITARITE);
        register.accept(GTGItems.VENUSAURITE);
    }

    private void createZCrystals (Consumer<Item> register) {
        register.accept(GTGItems.ALORAICHIUM_Z);
        register.accept(GTGItems.DECIDIUM_Z);
        register.accept(GTGItems.EEVIUM_Z);
        register.accept(GTGItems.INCINIUM_Z);
        register.accept(GTGItems.KOMMONIUM_Z);
        register.accept(GTGItems.LUNALIUM_Z);
        register.accept(GTGItems.LYCANIUM_Z);
        register.accept(GTGItems.MARSHADIUM_Z);
        register.accept(GTGItems.MEWNIUM_Z);
        register.accept(GTGItems.MIMIKIUM_Z);
        register.accept(GTGItems.PIKANIUM_Z);
        register.accept(GTGItems.PIKASHUNIUM_Z);
        register.accept(GTGItems.PRIMARIUM_Z);
        register.accept(GTGItems.SNORLIUM_Z);
        register.accept(GTGItems.SOLGANIUM_Z);
        register.accept(GTGItems.TAPUNIUM_Z);
        register.accept(GTGItems.ULTRANECROZIUM_Z);

        register.accept(GTGItems.BUGINIUM_Z);
        register.accept(GTGItems.DARKINIUM_Z);
        register.accept(GTGItems.DRAGONIUM_Z);
        register.accept(GTGItems.ELECTRIUM_Z);
        register.accept(GTGItems.FAIRIUM_Z);
        register.accept(GTGItems.FIGHTINIUM_Z);
        register.accept(GTGItems.FIRIUM_Z);
        register.accept(GTGItems.FLYINIUM_Z);
        register.accept(GTGItems.GHOSTIUM_Z);
        register.accept(GTGItems.GRASSIUM_Z);
        register.accept(GTGItems.GROUNDIUM_Z);
        register.accept(GTGItems.ICIUM_Z);
        register.accept(GTGItems.NORMALIUM_Z);
        register.accept(GTGItems.POISONIUM_Z);
        register.accept(GTGItems.PSYCHIUM_Z);
        register.accept(GTGItems.ROCKIUM_Z);
        register.accept(GTGItems.STEELIUM_Z);
        register.accept(GTGItems.WATERIUM_Z);
    }

    private void createDynamax (Consumer<Item> register) {
        register.accept(GTGItems.DYNAMAX_CANDY);
        register.accept(GTGItems.MAX_SOUP);
        register.accept(GTGItems.MAX_MUSHROOM);
    }

    private void createTeraShards (Consumer<Item> register) {
        register.accept(GTGItems.BUG_TERA_SHARD);
        register.accept(GTGItems.DARK_TERA_SHARD);
        register.accept(GTGItems.DRAGON_TERA_SHARD);
        register.accept(GTGItems.ELECTRIC_TERA_SHARD);
        register.accept(GTGItems.FAIRY_TERA_SHARD);
        register.accept(GTGItems.FIGHTING_TERA_SHARD);
        register.accept(GTGItems.FIRE_TERA_SHARD);
        register.accept(GTGItems.FLYING_TERA_SHARD);
        register.accept(GTGItems.GHOST_TERA_SHARD);
        register.accept(GTGItems.GRASS_TERA_SHARD);
        register.accept(GTGItems.GROUND_TERA_SHARD);
        register.accept(GTGItems.ICE_TERA_SHARD);
        register.accept(GTGItems.NORMAL_TERA_SHARD);
        register.accept(GTGItems.POISON_TERA_SHARD);
        register.accept(GTGItems.PSYCHIC_TERA_SHARD);
        register.accept(GTGItems.ROCK_TERA_SHARD);
        register.accept(GTGItems.STEEL_TERA_SHARD);
        register.accept(GTGItems.WATER_TERA_SHARD);
        register.accept(GTGItems.STELLAR_TERA_SHARD);
    }

    private void createFormChanges (Consumer<Item> register) {
        register.accept(GTGItems.ADAMANT_CRYSTAL);
        register.accept(GTGItems.LUSTROUS_GLOBE);
        register.accept(GTGItems.GRISEOUS_CORE);

        register.accept(GTGItems.BLUE_ORB);
        register.accept(GTGItems.RED_ORB);

        register.accept(GTGItems.RUSTED_SWORD);
        register.accept(GTGItems.RUSTED_SHIELD);

        register.accept(GTGItems.DRACO_PLATE);
        register.accept(GTGItems.DREAD_PLATE);
        register.accept(GTGItems.EARTH_PLATE);
        register.accept(GTGItems.FIST_PLATE);
        register.accept(GTGItems.FLAME_PLATE);
        register.accept(GTGItems.ICICLE_PLATE);
        register.accept(GTGItems.INSECT_PLATE);
        register.accept(GTGItems.IRON_PLATE);
        register.accept(GTGItems.MEADOW_PLATE);
        register.accept(GTGItems.MIND_PLATE);
        register.accept(GTGItems.PIXIE_PLATE);
        register.accept(GTGItems.SKY_PLATE);
        register.accept(GTGItems.SPLASH_PLATE);
        register.accept(GTGItems.SPOOKY_PLATE);
        register.accept(GTGItems.STONE_PLATE);
        register.accept(GTGItems.TOXIC_PLATE);
        register.accept(GTGItems.ZAP_PLATE);

        register.accept(GTGItems.BUG_MEMORY);
        register.accept(GTGItems.DARK_MEMORY);
        register.accept(GTGItems.DRAGON_MEMORY);
        register.accept(GTGItems.ELECTRIC_MEMORY);
        register.accept(GTGItems.FAIRY_MEMORY);
        register.accept(GTGItems.FIGHTING_MEMORY);
        register.accept(GTGItems.FIRE_MEMORY);
        register.accept(GTGItems.FLYING_MEMORY);
        register.accept(GTGItems.GHOST_MEMORY);
        register.accept(GTGItems.GRASS_MEMORY);
        register.accept(GTGItems.GROUND_MEMORY);
        register.accept(GTGItems.ICE_MEMORY);
        register.accept(GTGItems.POISON_MEMORY);
        register.accept(GTGItems.PSYCHIC_MEMORY);
        register.accept(GTGItems.ROCK_MEMORY);
        register.accept(GTGItems.STEEL_MEMORY);
        register.accept(GTGItems.WATER_MEMORY);

        register.accept(GTGItems.BURN_DRIVE);
        register.accept(GTGItems.CHILL_DRIVE);
        register.accept(GTGItems.DOUSE_DRIVE);
        register.accept(GTGItems.SHOCK_DRIVE);

        register.accept(GTGItems.WELLSPRING_MASK);
        register.accept(GTGItems.HEARTHFLAME_MASK);
        register.accept(GTGItems.CORNERSTONE_MASK);

        register.accept(GTGItems.REIGNS_OF_UNITY);
        register.accept(GTGItems.ROTOM_CATALOG);
        register.accept(GTGItems.GRACIDEA_FLOWER);
        register.accept(GTGItems.PRISON_BOTTLE);
        register.accept(GTGItems.REVEAL_GLASS);
        register.accept(GTGItems.DNA_SPLICERS);
        register.accept(GTGItems.N_LUNARIZER);
        register.accept(GTGItems.N_SOLARIZER);
        register.accept(GTGItems.ZYGARDE_CUBE);

        register.accept(GTGItems.PINK_NECTAR);
        register.accept(GTGItems.RED_NECTAR);
        register.accept(GTGItems.PURPLE_NECTAR);
        register.accept(GTGItems.YELLOW_NECTAR);
    }
}
