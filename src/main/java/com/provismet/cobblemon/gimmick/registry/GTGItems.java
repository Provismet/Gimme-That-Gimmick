package com.provismet.cobblemon.gimmick.registry;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty;
import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.mojang.datafixers.util.Pair;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.component.MegaEvolution;
import com.provismet.cobblemon.gimmick.item.PolymerBlockItemTextured;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import com.provismet.cobblemon.gimmick.item.dynamax.DMaxCandyItem;
import com.provismet.cobblemon.gimmick.item.dynamax.DynamaxBandItem;
import com.provismet.cobblemon.gimmick.item.dynamax.MaxSoupItem;
import com.provismet.cobblemon.gimmick.item.forms.*;
import com.provismet.cobblemon.gimmick.item.mega.MegaStoneItem;
import com.provismet.cobblemon.gimmick.item.tera.TeraOrbItem;
import com.provismet.cobblemon.gimmick.item.tera.TeraShardItem;
import com.provismet.cobblemon.gimmick.item.zmove.SpeciesZCrystalItem;
import com.provismet.cobblemon.gimmick.item.zmove.TypedZCrystalItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

import java.util.List;

public abstract class GTGItems {
    // Key Items
    public static final PolymerHeldItem MEGA_BRACELET = register("mega_bracelet", (settings, item, modelData) -> new PolymerHeldItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.KEY_STONE, Unit.INSTANCE), item, modelData, 1));
    public static final PolymerHeldItem Z_RING = register("z-ring", (settings, item, modelData) -> new PolymerHeldItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.Z_RING, Unit.INSTANCE), item, modelData, 1));
    public static final PolymerHeldItem DYNAMAX_BAND = register("dynamax_band", (settings, item, modelData) -> new DynamaxBandItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.DYNAMAX_BAND, Unit.INSTANCE), item, modelData));
    public static final TeraOrbItem TERA_ORB = register("tera_orb", (settings, item, modelData) -> new TeraOrbItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.TERA_ORB, Unit.INSTANCE).maxDamage(100), item, modelData));

    public static final PolymerBlockItemTextured POWER_SPOT = register("power_spot", Items.IRON_INGOT, GTGBlocks.POWER_SPOT, PolymerBlockItemTextured::new);

    // Mega Stones
    public static final MegaStoneItem ABOMASITE = registerMegaStone("abomasite", "abomasnow");
    public static final MegaStoneItem ABSOLITE = registerMegaStone("absolite", "absol");
    public static final MegaStoneItem AERODACTYLITE = registerMegaStone("aerodactylite", "aerodactyl");
    public static final MegaStoneItem AGGRONITE = registerMegaStone("aggronite", "aggron");
    public static final MegaStoneItem ALAKAZITE = registerMegaStone("alakazite", "alakazam");
    public static final MegaStoneItem ALTARIANITE = registerMegaStone("altarianite", "altaria");
    public static final MegaStoneItem AMPHAROSITE = registerMegaStone("ampharosite", "ampharos");
    public static final MegaStoneItem AUDINITE = registerMegaStone("audinite", "audino");
    public static final MegaStoneItem BANETTITE = registerMegaStone("banettite", "banette");
    public static final MegaStoneItem BEEDRILLITE = registerMegaStone("beedrillite", "beedrill");
    public static final MegaStoneItem BLASTOISINITE = registerMegaStone("blastoisinite", "blastoise");
    public static final MegaStoneItem BLAZIKENITE = registerMegaStone("blazikenite", "blaziken");
    public static final MegaStoneItem CAMERUPTITE = registerMegaStone("cameruptite", "camerupt");
    public static final MegaStoneItem CHARIZARDITE_X = registerMegaStone("charizarditex", "charizard", "mega_x");
    public static final MegaStoneItem CHARIZARDITE_Y = registerMegaStone("charizarditey", "charizard", "mega_y");
    public static final MegaStoneItem DIANCITE = registerMegaStone("diancite", "diancie");
    public static final MegaStoneItem GALLADITE = registerMegaStone("galladite", "gallade");
    public static final MegaStoneItem GARCHOMPITE = registerMegaStone("garchompite", "garchomp");
    public static final MegaStoneItem GARDEVOIRITE = registerMegaStone("gardevoirite", "gardevoir");
    public static final MegaStoneItem GENGARITE = registerMegaStone("gengarite", "gengar");
    public static final MegaStoneItem GLALITITE = registerMegaStone("glalitite", "glalie");
    public static final MegaStoneItem GYARADOSITE = registerMegaStone("gyaradosite", "gyarados");
    public static final MegaStoneItem HERACRONITE = registerMegaStone("heracronite", "heracross");
    public static final MegaStoneItem HOUNDOOMINITE = registerMegaStone("houndoominite", "houndoom");
    public static final MegaStoneItem KANGASKHANITE = registerMegaStone("kangaskhanite", "kangaskhan");
    public static final MegaStoneItem LATIASITE = registerMegaStone("latiasite", "latias");
    public static final MegaStoneItem LATIOSITE = registerMegaStone("latiosite", "latios");
    public static final MegaStoneItem LOPUNNITE = registerMegaStone("lopunnite", "lopunny");
    public static final MegaStoneItem LUCARIONITE = registerMegaStone("lucarionite", "lucario");
    public static final MegaStoneItem MANECTITE = registerMegaStone("manectite", "manectric");
    public static final MegaStoneItem MAWILITE = registerMegaStone("mawilite", "mawile");
    public static final MegaStoneItem MEDICHAMITE = registerMegaStone("medichamite", "medicham");
    public static final MegaStoneItem METAGROSSITE = registerMegaStone("metagrossite", "metagross");
    public static final MegaStoneItem MEWTWONITE_X = registerMegaStone("mewtwonitex", "mewtwo", "mega_x");
    public static final MegaStoneItem MEWTWONITE_Y = registerMegaStone("mewtwonitey", "mewtwo", "mega_y");
    public static final MegaStoneItem PIDGEOTITE = registerMegaStone("pidgeotite", "pidgeot");
    public static final MegaStoneItem PINSIRITE = registerMegaStone("pinsirite", "pinsir");
    public static final MegaStoneItem SABLENITE = registerMegaStone("sablenite", "sableye");
    public static final MegaStoneItem SALAMENCITE = registerMegaStone("salamencite", "salamence");
    public static final MegaStoneItem SCEPTILITE = registerMegaStone("sceptilite", "sceptile");
    public static final MegaStoneItem SCIZORITE = registerMegaStone("scizorite", "scizor");
    public static final MegaStoneItem SHARPEDONITE = registerMegaStone("sharpedonite", "sharpedo");
    public static final MegaStoneItem SLOWBRONITE = registerMegaStone("slowbronite", "slowbro");
    public static final MegaStoneItem STEELIXITE = registerMegaStone("steelixite", "steelix");
    public static final MegaStoneItem SWAMPERTITE = registerMegaStone("swampertite", "swampert");
    public static final MegaStoneItem TYRANITARITE = registerMegaStone("tyranitarite", "tyranitar");
    public static final MegaStoneItem VENUSAURITE = registerMegaStone("venusaurite", "venusaur");

    // Z-Crystals
    public static final SpeciesZCrystalItem ALORAICHIUM_Z = registerSpeciesZCrystal("aloraichiumz", ElementalTypes.ELECTRIC);
    public static final SpeciesZCrystalItem DECIDIUM_Z = registerSpeciesZCrystal("decidiumz", ElementalTypes.GHOST);
    public static final SpeciesZCrystalItem EEVIUM_Z = registerSpeciesZCrystal("eeviumz", ElementalTypes.NORMAL);
    public static final SpeciesZCrystalItem INCINIUM_Z = registerSpeciesZCrystal("inciniumz", ElementalTypes.DARK);
    public static final SpeciesZCrystalItem KOMMONIUM_Z = registerSpeciesZCrystal("kommoniumz", ElementalTypes.DRAGON);
    public static final SpeciesZCrystalItem LUNALIUM_Z = registerSpeciesZCrystal("lunaliumz", ElementalTypes.GHOST);
    public static final SpeciesZCrystalItem LYCANIUM_Z = registerSpeciesZCrystal("lycaniumz", ElementalTypes.ROCK);
    public static final SpeciesZCrystalItem MARSHADIUM_Z = registerSpeciesZCrystal("marshadiumz", ElementalTypes.GHOST);
    public static final SpeciesZCrystalItem MEWNIUM_Z = registerSpeciesZCrystal("mewniumz", ElementalTypes.PSYCHIC);
    public static final SpeciesZCrystalItem MIMIKIUM_Z = registerSpeciesZCrystal("mimikiumz", ElementalTypes.FAIRY);
    public static final SpeciesZCrystalItem PIKANIUM_Z = registerSpeciesZCrystal("pikaniumz", ElementalTypes.ELECTRIC);
    public static final SpeciesZCrystalItem PIKASHUNIUM_Z = registerSpeciesZCrystal("pikashuniumz", ElementalTypes.ELECTRIC);
    public static final SpeciesZCrystalItem PRIMARIUM_Z = registerSpeciesZCrystal("primariumz", ElementalTypes.WATER);
    public static final SpeciesZCrystalItem SNORLIUM_Z = registerSpeciesZCrystal("snorliumz", ElementalTypes.NORMAL);
    public static final SpeciesZCrystalItem SOLGANIUM_Z = registerSpeciesZCrystal("solganiumz", ElementalTypes.STEEL);
    public static final SpeciesZCrystalItem TAPUNIUM_Z = registerSpeciesZCrystal("tapuniumz", ElementalTypes.FAIRY);
    public static final SpeciesZCrystalItem ULTRANECROZIUM_Z = registerSpeciesZCrystal("ultranecroziumz", ElementalTypes.PSYCHIC);

    public static final TypedZCrystalItem BUGINIUM_Z = registerZCrystal("buginiumz", ElementalTypes.BUG);
    public static final TypedZCrystalItem DARKINIUM_Z = registerZCrystal("darkiniumz", ElementalTypes.DARK);
    public static final TypedZCrystalItem DRAGONIUM_Z = registerZCrystal("dragoniumz", ElementalTypes.DRAGON);
    public static final TypedZCrystalItem ELECTRIUM_Z = registerZCrystal("electriumz", ElementalTypes.ELECTRIC);
    public static final TypedZCrystalItem FAIRIUM_Z = registerZCrystal("fairiumz", ElementalTypes.FAIRY);
    public static final TypedZCrystalItem FIGHTINIUM_Z = registerZCrystal("fightiniumz", ElementalTypes.FIGHTING);
    public static final TypedZCrystalItem FIRIUM_Z = registerZCrystal("firiumz", ElementalTypes.FIRE);
    public static final TypedZCrystalItem FLYINIUM_Z = registerZCrystal("flyiniumz", ElementalTypes.FLYING);
    public static final TypedZCrystalItem GHOSTIUM_Z = registerZCrystal("ghostiumz", ElementalTypes.GHOST);
    public static final TypedZCrystalItem GRASSIUM_Z = registerZCrystal("grassiumz", ElementalTypes.GRASS);
    public static final TypedZCrystalItem GROUNDIUM_Z = registerZCrystal("groundiumz", ElementalTypes.GROUND);
    public static final TypedZCrystalItem ICIUM_Z = registerZCrystal("iciumz", ElementalTypes.ICE);
    public static final TypedZCrystalItem NORMALIUM_Z = registerZCrystal("normaliumz", ElementalTypes.NORMAL);
    public static final TypedZCrystalItem POISONIUM_Z = registerZCrystal("poisoniumz", ElementalTypes.POISON);
    public static final TypedZCrystalItem PSYCHIUM_Z = registerZCrystal("psychiumz", ElementalTypes.PSYCHIC);
    public static final TypedZCrystalItem ROCKIUM_Z = registerZCrystal("rockiumz", ElementalTypes.ROCK);
    public static final TypedZCrystalItem STEELIUM_Z = registerZCrystal("steeliumz", ElementalTypes.STEEL);
    public static final TypedZCrystalItem WATERIUM_Z = registerZCrystal("wateriumz", ElementalTypes.WATER);

    // Dynamax
    public static final DMaxCandyItem DYNAMAX_CANDY = register("dynamax_candy", DMaxCandyItem::new);
    public static final MaxSoupItem MAX_SOUP = register("max_soup", MaxSoupItem::new);
    public static final PolymerBlockItemTextured MAX_MUSHROOM = register("max_mushroom", Items.IRON_INGOT, GTGBlocks.MAX_MUSHROOM, PolymerBlockItemTextured::new);

    // Tera Shards
    public static final TeraShardItem BUG_TERA_SHARD = registerTeraShard("bug", TeraTypes.getBUG());
    public static final TeraShardItem DARK_TERA_SHARD = registerTeraShard("dark", TeraTypes.getDARK());
    public static final TeraShardItem DRAGON_TERA_SHARD = registerTeraShard("dragon", TeraTypes.getDRAGON());
    public static final TeraShardItem ELECTRIC_TERA_SHARD = registerTeraShard("electric", TeraTypes.getELECTRIC());
    public static final TeraShardItem FAIRY_TERA_SHARD = registerTeraShard("fairy", TeraTypes.getFAIRY());
    public static final TeraShardItem FIGHTING_TERA_SHARD = registerTeraShard("fighting", TeraTypes.getFIGHTING());
    public static final TeraShardItem FIRE_TERA_SHARD = registerTeraShard("fire", TeraTypes.getFIRE());
    public static final TeraShardItem FLYING_TERA_SHARD = registerTeraShard("flying", TeraTypes.getFLYING());
    public static final TeraShardItem GHOST_TERA_SHARD = registerTeraShard("ghost", TeraTypes.getGHOST());
    public static final TeraShardItem GRASS_TERA_SHARD = registerTeraShard("grass", TeraTypes.getGRASS());
    public static final TeraShardItem GROUND_TERA_SHARD = registerTeraShard("ground", TeraTypes.getGROUND());
    public static final TeraShardItem ICE_TERA_SHARD = registerTeraShard("ice", TeraTypes.getICE());
    public static final TeraShardItem NORMAL_TERA_SHARD = registerTeraShard("normal", TeraTypes.getNORMAL());
    public static final TeraShardItem POISON_TERA_SHARD = registerTeraShard("poison", TeraTypes.getPOISON());
    public static final TeraShardItem PSYCHIC_TERA_SHARD = registerTeraShard("psychic", TeraTypes.getPSYCHIC());
    public static final TeraShardItem ROCK_TERA_SHARD = registerTeraShard("rock", TeraTypes.getROCK());
    public static final TeraShardItem STEEL_TERA_SHARD = registerTeraShard("steel", TeraTypes.getSTEEL());
    public static final TeraShardItem WATER_TERA_SHARD = registerTeraShard("water", TeraTypes.getWATER());
    public static final TeraShardItem STELLAR_TERA_SHARD = registerTeraShard("stellar", TeraTypes.getSTELLAR());

    // Form Change Items
    public static final MeteoriteItem METEORITE = register("meteorite", Items.IRON_INGOT, GTGBlocks.METEORITE, (block, settings, virtualItem, model) -> new MeteoriteItem(block, settings.rarity(Rarity.RARE), virtualItem, model));
    public static final ReinsOfUnityItem REIGNS_OF_UNITY = register("reigns_of_unity", ReinsOfUnityItem::new);
    public static final RotomCatalogItem ROTOM_CATALOG = register("rotom_catalog", RotomCatalogItem::new);
    public static final RevealGlassItem REVEAL_GLASS = register("reveal_glass", RevealGlassItem::new);
    public static final DNASplicersItem DNA_SPLICERS = register("dna_splicers", DNASplicersItem::new);
    public static final GracideaFlowerItem GRACIDEA_FLOWER = register("gracidea_flower", GracideaFlowerItem::new);
    public static final PrisonBottleItem PRISON_BOTTLE = register("prison_bottle", PrisonBottleItem::new);
    public static final NLunarizerItem N_LUNARIZER = register("n_lunarizer", NLunarizerItem::new);
    public static final NSolarizerItem N_SOLARIZER = register("n_solarizer", NSolarizerItem::new);
    public static final ZygardeCubeItem ZYGARDE_CUBE = register("zygarde_cube", ZygardeCubeItem::new);

    // Form Change Held Items
    public static final GenericFormChangeHeldItem ADAMANT_CRYSTAL = registerFormChangeChoice("adamantcrystal", "dialga", "orb_forme", "origin", "altered", 1);
    public static final GenericFormChangeHeldItem LUSTROUS_GLOBE = registerFormChangeChoice("lustrousglobe", "palkia", "orb_forme", "origin", "altered", 1);
    public static final GenericFormChangeHeldItem GRISEOUS_CORE = registerFormChangeChoice("griseouscore", "giratina", "orb_forme", "origin", "altered", 1);

    public static final GenericFormChangeHeldItem BLUE_ORB = registerFormChangeChoice("blueorb", "kyogre", "reversion_state", "primal", "standard", 1);
    public static final GenericFormChangeHeldItem RED_ORB = registerFormChangeChoice("redorb", "groudon", "reversion_state", "primal", "standard", 1);

    public static final GenericFormChangeHeldItem RUSTED_SWORD = registerFormChangeChoiceWithMoves("rustedsword", "zacian", "behemoth_warrior", "crowned", "hero", 1, List.of("behemothblade"), List.of("ironhead"), List.of("ironhead"), List.of("behemothblade"));
    public static final GenericFormChangeHeldItem RUSTED_SHIELD = registerFormChangeChoiceWithMoves("rustedshield", "zamazenta", "behemoth_warrior", "crowned", "hero", 1, List.of("behemothbash"), List.of("ironhead"), List.of("ironhead"), List.of("behemothbash"));

    // Arceus Plates
    public static final GenericFormChangeHeldItem DRACO_PLATE = registerArceus("dracoplate", "dragon");
    public static final GenericFormChangeHeldItem DREAD_PLATE = registerArceus("dreadplate", "dark");
    public static final GenericFormChangeHeldItem EARTH_PLATE = registerArceus("earthplate", "ground");
    public static final GenericFormChangeHeldItem FIST_PLATE = registerArceus("fistplate", "fighting");
    public static final GenericFormChangeHeldItem FLAME_PLATE = registerArceus("flameplate", "fire");
    public static final GenericFormChangeHeldItem ICICLE_PLATE = registerArceus("icicleplate", "ice");
    public static final GenericFormChangeHeldItem INSECT_PLATE = registerArceus("insectplate", "bug");
    public static final GenericFormChangeHeldItem IRON_PLATE = registerArceus("ironplate", "steel");
    public static final GenericFormChangeHeldItem MEADOW_PLATE = registerArceus("meadowplate", "grass");
    public static final GenericFormChangeHeldItem MIND_PLATE = registerArceus("mindplate", "psychic");
    public static final GenericFormChangeHeldItem PIXIE_PLATE = registerArceus("pixieplate", "fairy");
    public static final GenericFormChangeHeldItem SKY_PLATE = registerArceus("skyplate", "flying");
    public static final GenericFormChangeHeldItem SPLASH_PLATE = registerArceus("splashplate", "water");
    public static final GenericFormChangeHeldItem SPOOKY_PLATE = registerArceus("spookyplate", "ghost");
    public static final GenericFormChangeHeldItem STONE_PLATE = registerArceus("stoneplate", "rock");
    public static final GenericFormChangeHeldItem TOXIC_PLATE = registerArceus("toxicplate", "poison");
    public static final GenericFormChangeHeldItem ZAP_PLATE = registerArceus("zapplate", "electric");

    // Silvally Memories
    public static final GenericFormChangeHeldItem BUG_MEMORY = registerSilvally("bug");
    public static final GenericFormChangeHeldItem DARK_MEMORY = registerSilvally("dark");
    public static final GenericFormChangeHeldItem DRAGON_MEMORY = registerSilvally("dragon");
    public static final GenericFormChangeHeldItem ELECTRIC_MEMORY = registerSilvally("electric");
    public static final GenericFormChangeHeldItem FAIRY_MEMORY = registerSilvally("fairy");
    public static final GenericFormChangeHeldItem FIGHTING_MEMORY = registerSilvally("fighting");
    public static final GenericFormChangeHeldItem FIRE_MEMORY = registerSilvally("fire");
    public static final GenericFormChangeHeldItem FLYING_MEMORY = registerSilvally("flying");
    public static final GenericFormChangeHeldItem GHOST_MEMORY = registerSilvally("ghost");
    public static final GenericFormChangeHeldItem GRASS_MEMORY = registerSilvally("grass");
    public static final GenericFormChangeHeldItem GROUND_MEMORY = registerSilvally("ground");
    public static final GenericFormChangeHeldItem ICE_MEMORY = registerSilvally("ice");
    public static final GenericFormChangeHeldItem POISON_MEMORY = registerSilvally("poison");
    public static final GenericFormChangeHeldItem PSYCHIC_MEMORY = registerSilvally("psychic");
    public static final GenericFormChangeHeldItem ROCK_MEMORY = registerSilvally("rock");
    public static final GenericFormChangeHeldItem STEEL_MEMORY = registerSilvally("steel");
    public static final GenericFormChangeHeldItem WATER_MEMORY = registerSilvally("water");

    // Genesect Drives
    public static final GenericFormChangeHeldItem BURN_DRIVE = registerGenesect("burn", "fire");
    public static final GenericFormChangeHeldItem CHILL_DRIVE = registerGenesect("chill", "ice");
    public static final GenericFormChangeHeldItem DOUSE_DRIVE = registerGenesect("douse", "water");
    public static final GenericFormChangeHeldItem SHOCK_DRIVE = registerGenesect("shock", "electric");

    // Oricorio Nectars
    public static final NectarItem PINK_NECTAR = registerOricorio("pink", "pa'u");
    public static final NectarItem PURPLE_NECTAR = registerOricorio("purple", "sensu");
    public static final NectarItem RED_NECTAR = registerOricorio("red", "baile");
    public static final NectarItem YELLOW_NECTAR = registerOricorio("yellow", "pom_pom");

    // Ogerpon Masks
    public static final GenericFormChangeHeldItem WELLSPRING_MASK = registerOgerpon("wellspring", TeraTypes.getWATER());
    public static final GenericFormChangeHeldItem HEARTHFLAME_MASK = registerOgerpon("hearthflame", TeraTypes.getFIRE());
    public static final GenericFormChangeHeldItem CORNERSTONE_MASK = registerOgerpon("cornerstone", TeraTypes.getROCK());

    // Data-Driven Items
    public static final DataDrivenFusionItem DATA_DRIVEN_FUSION = register("data_driven_fusion", DataDrivenFusionItem::new);
    public static final DataDrivenToggleItem DATA_DRIVEN_TOGGLE = register("data_driven_toggle", DataDrivenToggleItem::new);

    private static MegaStoneItem registerMegaStone (String name, String species, String megaAspect) {
        return registerShowdownItem(
            name,
            (settings, vanillaBaseItem, modelData) -> new MegaStoneItem(
                settings.component(GTGItemDataComponents.MEGA_EVOLUTION, MegaEvolution.create(species, "normal", megaAspect)),
                vanillaBaseItem,
                modelData
            )
        );
    }

    private static MegaStoneItem registerMegaStone (String name, String species) {
        return registerMegaStone(name, species, "mega");
    }

    private static TypedZCrystalItem registerZCrystal (String name, ElementalType type) {
        return registerShowdownItem(name, (settings, item, modelData) -> new TypedZCrystalItem(settings, item, modelData, type));
    }

    private static SpeciesZCrystalItem registerSpeciesZCrystal (String name, ElementalType type) {
        return registerShowdownItem(name, (settings, item, modelData) -> new SpeciesZCrystalItem(settings, item, modelData, type));
    }

    private static TeraShardItem registerTeraShard (String type, TeraType teraType) {
        return register(type + "_tera_shard", (settings, item, modelData) -> new TeraShardItem(settings.maxCount(50), item, modelData, teraType));
    }

    private static PolymerHeldItem registerShowdownItem (String name) {
        return registerShowdownItem(name, PolymerHeldItem::new);
    }

    private static <T extends PolymerHeldItem> T registerShowdownItem (String name, ItemConstructor<T> itemConstructor) {
        T item = register(name, itemConstructor);
        CobblemonHeldItemManager.INSTANCE.registerRemap(item, name);
        return item;
    }

    private static GenericFormChangeHeldItem registerOgerpon (String mask, TeraType teraType) {
        return registerShowdownItem(mask + "mask", (settings, vanillaBaseItem, modelData) -> new OgerponMaskItem(settings, vanillaBaseItem, modelData, mask, teraType));
    }

    private static GenericFormChangeHeldItem registerGenesect (String driveName, String type) {
        return registerFormChangeChoice(driveName + "drive", "genesect", "techno_drive", type, "none", 1);
    }

    private static GenericFormChangeHeldItem registerSilvally (String type) {
        return registerFormChangeChoice(type + "memory", "silvally", "rks_memory", type, "normal", 1);
    }

    private static NectarItem registerOricorio(String color, String type) {
        return register(color + "nectar", (settings, item, modelData) -> new NectarItem(settings, item, modelData, type));
    }

    private static GenericFormChangeHeldItem registerArceus (String name, String type) {
        return registerFormChangeChoice(name, "arceus", "multitype", type, "normal", 1);
    }

    private static MoveChangingFormChangeHeldItem registerFormChangeChoiceWithMoves (String name, String species, String propertyName, String appliedChoice, String defaultChoice, int tooltipLines, List<String> movesGainedOnGive, List<String> movesLostOnGive, List<String> movesGainedOnTake, List<String> movesLostOnTake) {
        return registerShowdownItem(name, (settings, item, modelData) -> new MoveChangingFormChangeHeldItem(settings, item, modelData, tooltipLines, MiscUtilsKt.cobblemonResource(species), new StringSpeciesFeature(propertyName, appliedChoice), new StringSpeciesFeature(propertyName, defaultChoice), movesGainedOnGive, movesLostOnGive, movesGainedOnTake, movesLostOnTake));
    }

    private static GenericFormChangeHeldItem registerFormChangeChoice (String name, String species, String propertyName, String appliedChoice, String defaultChoice, int tooltipLines) {
        return registerFormChange(name, species, new StringSpeciesFeature(propertyName, appliedChoice), new StringSpeciesFeature(propertyName, defaultChoice), tooltipLines);
    }

    private static GenericFormChangeHeldItem registerFormChange (String name, String species, CustomPokemonProperty apply, CustomPokemonProperty remove, int tooltipLines) {
        return registerShowdownItem(name, (settings, item, modelData) -> new GenericFormChangeHeldItem(settings, item, modelData, tooltipLines, MiscUtilsKt.cobblemonResource(species), apply, remove));
    }

    private static <T extends PolymerHeldItem> T register (String name, ItemConstructor<T> itemConstructor) {
        return register(name, Items.IRON_INGOT, new Item.Settings().maxCount(64).rarity(Rarity.RARE), itemConstructor);
    }

    private static <T extends PolymerHeldItem> T register (String name, Item baseItem, Item.Settings settings, ItemConstructor<T> itemConstructor) {
        Identifier itemId = GimmeThatGimmickMain.identifier(name);
        PolymerModelData model = PolymerResourcePackUtils.requestModel(baseItem, itemId.withPrefixedPath("item/"));
        return Registry.register(Registries.ITEM, itemId, itemConstructor.get(settings, baseItem, model));
    }

    private static <T extends PolymerBlockItemTextured> T register (String name, Item baseItem, Block block, BlockItemConstructor<T> blockItemConstructor) {
        Identifier itemId = GimmeThatGimmickMain.identifier(name);
        PolymerModelData model = PolymerResourcePackUtils.requestModel(baseItem, itemId.withPrefixedPath("item/"));
        return Registry.register(Registries.ITEM, itemId, blockItemConstructor.get(block, new Item.Settings().maxCount(64), baseItem, model));
    }

    public static void init () {
        CobblemonHeldItemManager.INSTANCE.registerStackRemap(stack -> stack.get(GTGItemDataComponents.SHOWDOWN_ID));
        CobblemonHeldItemManager.INSTANCE.registerStackRemap(stack -> {
            Pair<String, Integer> id = EnchantmentHelper.getEffectListAndLevel(stack, GTGEnchantmentComponents.SHOWDOWN_ID);
            if (id == null) return null;
            return id.getFirst();
        });
    }

    @FunctionalInterface
    public interface ItemConstructor<T extends PolymerHeldItem> {
        T get (Item.Settings settings, Item vanillaBaseItem, PolymerModelData modelData);
    }

    @FunctionalInterface
    public interface BlockItemConstructor<T extends PolymerBlockItemTextured> {
        T get (Block block, Item.Settings settings, Item virtualItem, PolymerModelData model);
    }
}
