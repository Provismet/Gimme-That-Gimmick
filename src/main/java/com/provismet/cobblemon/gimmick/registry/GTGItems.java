package com.provismet.cobblemon.gimmick.registry;

import com.cobblemon.mod.common.api.types.ElementalType;
import com.cobblemon.mod.common.api.types.ElementalTypes;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.api.types.tera.TeraTypes;
import com.cobblemon.mod.common.pokemon.helditem.CobblemonHeldItemManager;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import com.provismet.cobblemon.gimmick.item.mega.MegaStoneItem;
import com.provismet.cobblemon.gimmick.item.tera.TeraOrbItem;
import com.provismet.cobblemon.gimmick.item.tera.TeraShardItem;
import com.provismet.cobblemon.gimmick.item.zmove.SpeciesZCrystalItem;
import com.provismet.cobblemon.gimmick.item.zmove.TypedZCrystalItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

public abstract class GTGItems {
    // Key Items
    public static final PolymerHeldItem MEGA_BRACELET = register("mega_bracelet", (settings, item, modelData) -> new PolymerHeldItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.KEY_STONE, Unit.INSTANCE), item, modelData, 1));
    public static final PolymerHeldItem Z_RING = register("z-ring", (settings, item, modelData) -> new PolymerHeldItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.Z_RING, Unit.INSTANCE), item, modelData, 1));
    public static final PolymerHeldItem DYNAMAX_BAND = register("dynamax_band", (settings, item, modelData) -> new PolymerHeldItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.DYNAMAX_BAND, Unit.INSTANCE), item, modelData, 1));
    public static final TeraOrbItem TERA_ORB = register("tera_orb", (settings, item, modelData) -> new TeraOrbItem(settings.rarity(Rarity.EPIC).maxCount(1).component(GTGItemDataComponents.TERA_ORB, Unit.INSTANCE), item, modelData));

    // Mega Stones
    public static final MegaStoneItem ABOMASITE = registerMegaStone("abomasite");
    public static final MegaStoneItem ABSOLITE = registerMegaStone("absolite");
    public static final MegaStoneItem AERODACTYLITE = registerMegaStone("aerodactylite");
    public static final MegaStoneItem AGGRONITE = registerMegaStone("aggronite");
    public static final MegaStoneItem ALAKAZITE = registerMegaStone("alakazite");
    public static final MegaStoneItem ALTARIANITE = registerMegaStone("altarianite");
    public static final MegaStoneItem AMPHAROSITE = registerMegaStone("ampharosite");
    public static final MegaStoneItem AUDINITE = registerMegaStone("audinite");
    public static final MegaStoneItem BANETTITE = registerMegaStone("banettite");
    public static final MegaStoneItem BEEDRILLITE = registerMegaStone("beedrillite");
    public static final MegaStoneItem BLASTOISINITE = registerMegaStone("blastoisinite");
    public static final MegaStoneItem BLAZIKENITE = registerMegaStone("blazikenite");
    public static final MegaStoneItem CAMERUPTITE = registerMegaStone("cameruptite");
    public static final MegaStoneItem CHARIZARDITE_X = registerMegaStone("charizarditex");
    public static final MegaStoneItem CHARIZARDITE_Y = registerMegaStone("charizarditey");
    public static final MegaStoneItem DIANCITE = registerMegaStone("diancite");
    public static final MegaStoneItem GALLADITE = registerMegaStone("galladite");
    public static final MegaStoneItem GARCHOMPITE = registerMegaStone("garchompite");
    public static final MegaStoneItem GARDEVOIRITE = registerMegaStone("gardevoirite");
    public static final MegaStoneItem GENGARITE = registerMegaStone("gengarite");
    public static final MegaStoneItem GLALITITE = registerMegaStone("glalitite");
    public static final MegaStoneItem GYARADOSITE = registerMegaStone("gyaradosite");
    public static final MegaStoneItem HERACRONITE = registerMegaStone("heracronite");
    public static final MegaStoneItem HOUNDOOMINITE = registerMegaStone("houndoominite");
    public static final MegaStoneItem KANGASKHANITE = registerMegaStone("kangaskhanite");
    public static final MegaStoneItem LATIASITE = registerMegaStone("latiasite");
    public static final MegaStoneItem LATIOSITE = registerMegaStone("latiosite");
    public static final MegaStoneItem LOPUNNITE = registerMegaStone("lopunnite");
    public static final MegaStoneItem LUCARIONITE = registerMegaStone("lucarionite");
    public static final MegaStoneItem MANECTITE = registerMegaStone("manectite");
    public static final MegaStoneItem MAWILITE = registerMegaStone("mawilite");
    public static final MegaStoneItem MEDICHAMITE = registerMegaStone("medichamite");
    public static final MegaStoneItem METAGROSSITE = registerMegaStone("metagrossite");
    public static final MegaStoneItem MEWTWONITE_X = registerMegaStone("mewtwonitex");
    public static final MegaStoneItem MEWTWONITE_Y = registerMegaStone("mewtwonitey");
    public static final MegaStoneItem PIDGEOTITE = registerMegaStone("pidgeotite");
    public static final MegaStoneItem PINSIRITE = registerMegaStone("pinsirite");
    public static final MegaStoneItem SABLENITE = registerMegaStone("sablenite");
    public static final MegaStoneItem SALAMENCITE = registerMegaStone("salamencite");
    public static final MegaStoneItem SCEPTILITE = registerMegaStone("sceptilite");
    public static final MegaStoneItem SCIZORITE = registerMegaStone("scizorite");
    public static final MegaStoneItem SHARPEDONITE = registerMegaStone("sharpedonite");
    public static final MegaStoneItem SLOWBRONITE = registerMegaStone("slowbronite");
    public static final MegaStoneItem STEELIXITE = registerMegaStone("steelixite");
    public static final MegaStoneItem SWAMPERTITE = registerMegaStone("swampertite");
    public static final MegaStoneItem TYRANITARITE = registerMegaStone("tyranitarite");
    public static final MegaStoneItem VENUSAURITE = registerMegaStone("venusaurite");

    // Z-Crystals
    public static final SpeciesZCrystalItem ALORAICHIUM_Z = registerSpeciesZCrystal("aloraichiumz", ElementalTypes.INSTANCE.getELECTRIC());
    public static final SpeciesZCrystalItem DECIDIUM_Z = registerSpeciesZCrystal("decidiumz", ElementalTypes.INSTANCE.getGHOST());
    public static final SpeciesZCrystalItem EEVIUM_Z = registerSpeciesZCrystal("eeviumz", ElementalTypes.INSTANCE.getNORMAL());
    public static final SpeciesZCrystalItem INCINIUM_Z = registerSpeciesZCrystal("inciniumz", ElementalTypes.INSTANCE.getDARK());
    public static final SpeciesZCrystalItem KOMMONIUM_Z = registerSpeciesZCrystal("kommoniumz", ElementalTypes.INSTANCE.getDRAGON());
    public static final SpeciesZCrystalItem LUNALIUM_Z = registerSpeciesZCrystal("lunaliumz", ElementalTypes.INSTANCE.getGHOST());
    public static final SpeciesZCrystalItem LYCANIUM_Z = registerSpeciesZCrystal("lycaniumz", ElementalTypes.INSTANCE.getROCK());
    public static final SpeciesZCrystalItem MARSHADIUM_Z = registerSpeciesZCrystal("marshadiumz", ElementalTypes.INSTANCE.getGHOST());
    public static final SpeciesZCrystalItem MEWNIUM_Z = registerSpeciesZCrystal("mewniumz", ElementalTypes.INSTANCE.getPSYCHIC());
    public static final SpeciesZCrystalItem MIMIKIUM_Z = registerSpeciesZCrystal("mimikiumz", ElementalTypes.INSTANCE.getFAIRY());
    public static final SpeciesZCrystalItem PIKANIUM_Z = registerSpeciesZCrystal("pikaniumz", ElementalTypes.INSTANCE.getELECTRIC());
    public static final SpeciesZCrystalItem PIKASHUNIUM_Z = registerSpeciesZCrystal("pikashuniumz", ElementalTypes.INSTANCE.getELECTRIC());
    public static final SpeciesZCrystalItem PRIMARIUM_Z = registerSpeciesZCrystal("primariumz", ElementalTypes.INSTANCE.getWATER());
    public static final SpeciesZCrystalItem SNORLIUM_Z = registerSpeciesZCrystal("snorliumz", ElementalTypes.INSTANCE.getNORMAL());
    public static final SpeciesZCrystalItem SOLGANIUM_Z = registerSpeciesZCrystal("solganiumz", ElementalTypes.INSTANCE.getSTEEL());
    public static final SpeciesZCrystalItem TAPUNIUM_Z = registerSpeciesZCrystal("tapuniumz", ElementalTypes.INSTANCE.getFAIRY());
    public static final SpeciesZCrystalItem ULTRANECROZIUM_Z = registerSpeciesZCrystal("ultranecroziumz", ElementalTypes.INSTANCE.getPSYCHIC());

    public static final TypedZCrystalItem BUGINIUM_Z = registerZCrystal("buginiumz", ElementalTypes.INSTANCE.getBUG());
    public static final TypedZCrystalItem DARKINIUM_Z = registerZCrystal("darkiniumz", ElementalTypes.INSTANCE.getDARK());
    public static final TypedZCrystalItem DRAGONIUM_Z = registerZCrystal("dragoniumz", ElementalTypes.INSTANCE.getDRAGON());
    public static final TypedZCrystalItem ELECTRIUM_Z = registerZCrystal("electriumz", ElementalTypes.INSTANCE.getELECTRIC());
    public static final TypedZCrystalItem FAIRIUM_Z = registerZCrystal("fairiumz", ElementalTypes.INSTANCE.getFAIRY());
    public static final TypedZCrystalItem FIGHTINIUM_Z = registerZCrystal("fightiniumz", ElementalTypes.INSTANCE.getFIGHTING());
    public static final TypedZCrystalItem FIRIUM_Z = registerZCrystal("firiumz", ElementalTypes.INSTANCE.getFIRE());
    public static final TypedZCrystalItem FLYINIUM_Z = registerZCrystal("flyiniumz", ElementalTypes.INSTANCE.getFLYING());
    public static final TypedZCrystalItem GHOSTIUM_Z = registerZCrystal("ghostiumz", ElementalTypes.INSTANCE.getGHOST());
    public static final TypedZCrystalItem GRASSIUM_Z = registerZCrystal("grassiumz", ElementalTypes.INSTANCE.getGRASS());
    public static final TypedZCrystalItem GROUNDIUM_Z = registerZCrystal("groundiumz", ElementalTypes.INSTANCE.getGROUND());
    public static final TypedZCrystalItem ICIUM_Z = registerZCrystal("iciumz", ElementalTypes.INSTANCE.getICE());
    public static final TypedZCrystalItem NORMALIUM_Z = registerZCrystal("normaliumz", ElementalTypes.INSTANCE.getNORMAL());
    public static final TypedZCrystalItem POISONIUM_Z = registerZCrystal("poisoniumz", ElementalTypes.INSTANCE.getPOISON());
    public static final TypedZCrystalItem PSYCHIUM_Z = registerZCrystal("psychiumz", ElementalTypes.INSTANCE.getPSYCHIC());
    public static final TypedZCrystalItem ROCKIUM_Z = registerZCrystal("rockiumz", ElementalTypes.INSTANCE.getROCK());
    public static final TypedZCrystalItem STEELIUM_Z = registerZCrystal("steeliumz", ElementalTypes.INSTANCE.getSTEEL());
    public static final TypedZCrystalItem WATERIUM_Z = registerZCrystal("wateriumz", ElementalTypes.INSTANCE.getWATER());

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

    private static MegaStoneItem registerMegaStone (String name) {
        return registerShowdownItem(name, MegaStoneItem::new);
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

    private static <T extends PolymerHeldItem> T register (String name, ItemConstructor<T> itemConstructor) {
        return register(name, Items.IRON_INGOT, new Item.Settings().maxCount(64).rarity(Rarity.RARE), itemConstructor);
    }

    private static <T extends PolymerHeldItem> T register (String name, Item baseItem, Item.Settings settings, ItemConstructor<T> itemConstructor) {
        Identifier itemId = GimmeThatGimmickMain.identifier(name);
        PolymerModelData model = PolymerResourcePackUtils.requestModel(baseItem, itemId.withPrefixedPath("item/"));
        return Registry.register(Registries.ITEM, itemId, itemConstructor.get(settings, baseItem, model));
    }

    public static void init () {}

    @FunctionalInterface
    public interface ItemConstructor<T extends PolymerHeldItem> {
        T get(Item.Settings settings, Item vanillaBaseItem, PolymerModelData modelData);
    }
}
