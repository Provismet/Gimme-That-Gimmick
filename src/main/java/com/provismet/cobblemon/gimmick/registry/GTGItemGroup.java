package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.config.Options;
import eu.pb4.polymer.core.api.item.PolymerItemGroupUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Text;

public abstract class GTGItemGroup {
    public static final ItemGroup MEGA_ITEMS = PolymerItemGroupUtils.builder()
        .icon(GTGItems.ABSOLITE::getDefaultStack)
        .displayName(Text.translatable("gimmethatgimmick.itemGroup.mega"))
        .entries((displayContext, entries) -> {
            entries.add(GTGItems.MEGA_BRACELET);

            entries.add(GTGItems.ABOMASITE);
            entries.add(GTGItems.ABSOLITE);
            entries.add(GTGItems.AERODACTYLITE);
            entries.add(GTGItems.AGGRONITE);
            entries.add(GTGItems.ALAKAZITE);
            entries.add(GTGItems.ALTARIANITE);
            entries.add(GTGItems.AMPHAROSITE);
            entries.add(GTGItems.AUDINITE);
            entries.add(GTGItems.BANETTITE);
            entries.add(GTGItems.BEEDRILLITE);
            entries.add(GTGItems.BLASTOISINITE);
            entries.add(GTGItems.BLAZIKENITE);
            entries.add(GTGItems.CAMERUPTITE);
            entries.add(GTGItems.CHARIZARDITE_X);
            entries.add(GTGItems.CHARIZARDITE_Y);
            entries.add(GTGItems.DIANCITE);
            entries.add(GTGItems.GALLADITE);
            entries.add(GTGItems.GARCHOMPITE);
            entries.add(GTGItems.GARDEVOIRITE);
            entries.add(GTGItems.GENGARITE);
            entries.add(GTGItems.GLALITITE);
            entries.add(GTGItems.GYARADOSITE);
            entries.add(GTGItems.HERACRONITE);
            entries.add(GTGItems.HOUNDOOMINITE);
            entries.add(GTGItems.KANGASKHANITE);
            entries.add(GTGItems.LATIASITE);
            entries.add(GTGItems.LATIOSITE);
            entries.add(GTGItems.LOPUNNITE);
            entries.add(GTGItems.LUCARIONITE);
            entries.add(GTGItems.MANECTITE);
            entries.add(GTGItems.MAWILITE);
            entries.add(GTGItems.MEDICHAMITE);
            entries.add(GTGItems.METAGROSSITE);
            entries.add(GTGItems.MEWTWONITE_X);
            entries.add(GTGItems.MEWTWONITE_Y);
            entries.add(GTGItems.PIDGEOTITE);
            entries.add(GTGItems.PINSIRITE);
            entries.add(GTGItems.SABLENITE);
            entries.add(GTGItems.SALAMENCITE);
            entries.add(GTGItems.SCEPTILITE);
            entries.add(GTGItems.SCIZORITE);
            entries.add(GTGItems.SHARPEDONITE);
            entries.add(GTGItems.SLOWBRONITE);
            entries.add(GTGItems.STEELIXITE);
            entries.add(GTGItems.SWAMPERTITE);
            entries.add(GTGItems.TYRANITARITE);
            entries.add(GTGItems.VENUSAURITE);
            
            if (FabricLoader.getInstance().isDevelopmentEnvironment() || Options.includeZAMegas()) {
                entries.add(GTGMegaStonesZA.ABSOLITE_Z);
                entries.add(GTGMegaStonesZA.BARBARACITE);
                entries.add(GTGMegaStonesZA.BAXCALIBRITE);
                entries.add(GTGMegaStonesZA.CHANDELURITE);
                entries.add(GTGMegaStonesZA.CHESNAUGHTITE);
                entries.add(GTGMegaStonesZA.CHIMECHITE);
                entries.add(GTGMegaStonesZA.CLEFABLITE);
                entries.add(GTGMegaStonesZA.CRABOMINITE);
                entries.add(GTGMegaStonesZA.DARKRANITE);
                entries.add(GTGMegaStonesZA.DELPHOXITE);
                entries.add(GTGMegaStonesZA.DRAGALGITE);
                entries.add(GTGMegaStonesZA.DRAGONINITE);
                entries.add(GTGMegaStonesZA.DRAMPANITE);
                entries.add(GTGMegaStonesZA.EELEKTROSSITE);
                entries.add(GTGMegaStonesZA.EMBOARITE);
                entries.add(GTGMegaStonesZA.EXCADRITE);
                entries.add(GTGMegaStonesZA.FALINKSITE);
                entries.add(GTGMegaStonesZA.FERALIGITE);
                entries.add(GTGMegaStonesZA.FLOETTITE);
                entries.add(GTGMegaStonesZA.FROSLASSITE);
                entries.add(GTGMegaStonesZA.GARCHOMPITE_Z);
                entries.add(GTGMegaStonesZA.GLIMMORANITE);
                entries.add(GTGMegaStonesZA.GOLISOPITE);
                entries.add(GTGMegaStonesZA.GOLURKITE);
                entries.add(GTGMegaStonesZA.GRENINJITE);
                entries.add(GTGMegaStonesZA.HAWLUCHANITE);
                entries.add(GTGMegaStonesZA.HEATRANITE);
                entries.add(GTGMegaStonesZA.LUCARIONITE_Z);
                entries.add(GTGMegaStonesZA.MAGEARNITE);
                entries.add(GTGMegaStonesZA.MALAMARITE);
                entries.add(GTGMegaStonesZA.MEGANIUMITE);
                entries.add(GTGMegaStonesZA.MEOWSTICITE);
                entries.add(GTGMegaStonesZA.PYROARITE);
                entries.add(GTGMegaStonesZA.RAICHUNITE_X);
                entries.add(GTGMegaStonesZA.RAICHUNITE_Y);
                entries.add(GTGMegaStonesZA.SCOLIPITE);
                entries.add(GTGMegaStonesZA.SCOVILLAINITE);
                entries.add(GTGMegaStonesZA.SCRAFTINITE);
                entries.add(GTGMegaStonesZA.SKARMORITE);
                entries.add(GTGMegaStonesZA.STARAPTITE);
                entries.add(GTGMegaStonesZA.STARMINITE);
                entries.add(GTGMegaStonesZA.TATSUGIRINITE);
                entries.add(GTGMegaStonesZA.VICTREEBELITE);
                entries.add(GTGMegaStonesZA.ZERAORITE);
                entries.add(GTGMegaStonesZA.ZYGARDITE);
            }
        })
        .build();

    public static final ItemGroup Z_ITEMS = PolymerItemGroupUtils.builder()
        .icon(GTGItems.ELECTRIUM_Z::getDefaultStack)
        .displayName(Text.translatable("gimmethatgimmick.itemGroup.z_crystals"))
        .entries((displayContext, entries) -> {
            entries.add(GTGItems.Z_RING);

            entries.add(GTGItems.ALORAICHIUM_Z);
            entries.add(GTGItems.DECIDIUM_Z);
            entries.add(GTGItems.EEVIUM_Z);
            entries.add(GTGItems.INCINIUM_Z);
            entries.add(GTGItems.KOMMONIUM_Z);
            entries.add(GTGItems.LUNALIUM_Z);
            entries.add(GTGItems.LYCANIUM_Z);
            entries.add(GTGItems.MARSHADIUM_Z);
            entries.add(GTGItems.MEWNIUM_Z);
            entries.add(GTGItems.MIMIKIUM_Z);
            entries.add(GTGItems.PIKANIUM_Z);
            entries.add(GTGItems.PIKASHUNIUM_Z);
            entries.add(GTGItems.PRIMARIUM_Z);
            entries.add(GTGItems.SNORLIUM_Z);
            entries.add(GTGItems.SOLGANIUM_Z);
            entries.add(GTGItems.TAPUNIUM_Z);
            entries.add(GTGItems.ULTRANECROZIUM_Z);
            entries.add(GTGItems.BUGINIUM_Z);
            entries.add(GTGItems.DARKINIUM_Z);
            entries.add(GTGItems.DRAGONIUM_Z);
            entries.add(GTGItems.ELECTRIUM_Z);
            entries.add(GTGItems.FAIRIUM_Z);
            entries.add(GTGItems.FIGHTINIUM_Z);
            entries.add(GTGItems.FIRIUM_Z);
            entries.add(GTGItems.FLYINIUM_Z);
            entries.add(GTGItems.GHOSTIUM_Z);
            entries.add(GTGItems.GRASSIUM_Z);
            entries.add(GTGItems.GROUNDIUM_Z);
            entries.add(GTGItems.ICIUM_Z);
            entries.add(GTGItems.NORMALIUM_Z);
            entries.add(GTGItems.POISONIUM_Z);
            entries.add(GTGItems.PSYCHIUM_Z);
            entries.add(GTGItems.ROCKIUM_Z);
            entries.add(GTGItems.STEELIUM_Z);
            entries.add(GTGItems.WATERIUM_Z);
        })
        .build();

    public static final ItemGroup DYNAMAX_ITEMS = PolymerItemGroupUtils.builder()
        .icon(GTGItems.DYNAMAX_BAND::getDefaultStack)
        .displayName(Text.translatable("gimmethatgimmick.itemGroup.dynamax"))
        .entries((displayContext, entries) -> {
            entries.add(GTGItems.DYNAMAX_BAND);
            entries.add(GTGItems.POWER_SPOT);
            entries.add(GTGItems.DYNAMAX_CANDY);
            entries.add(GTGItems.MAX_SOUP);
            entries.add(GTGItems.MAX_MUSHROOM);
        })
        .build();

    public static final ItemGroup TERA_ITEMS = PolymerItemGroupUtils.builder()
        .icon(GTGItems.TERA_ORB::getDefaultStack)
        .displayName(Text.translatable("gimmethatgimmick.itemGroup.tera"))
        .entries((displayContext, entries) -> {
            entries.add(GTGItems.TERA_ORB);

            entries.add(GTGItems.BUG_TERA_SHARD);
            entries.add(GTGItems.DARK_TERA_SHARD);
            entries.add(GTGItems.DRAGON_TERA_SHARD);
            entries.add(GTGItems.ELECTRIC_TERA_SHARD);
            entries.add(GTGItems.FAIRY_TERA_SHARD);
            entries.add(GTGItems.FIGHTING_TERA_SHARD);
            entries.add(GTGItems.FIRE_TERA_SHARD);
            entries.add(GTGItems.FLYING_TERA_SHARD);
            entries.add(GTGItems.GHOST_TERA_SHARD);
            entries.add(GTGItems.GRASS_TERA_SHARD);
            entries.add(GTGItems.GROUND_TERA_SHARD);
            entries.add(GTGItems.ICE_TERA_SHARD);
            entries.add(GTGItems.NORMAL_TERA_SHARD);
            entries.add(GTGItems.POISON_TERA_SHARD);
            entries.add(GTGItems.PSYCHIC_TERA_SHARD);
            entries.add(GTGItems.ROCK_TERA_SHARD);
            entries.add(GTGItems.STEEL_TERA_SHARD);
            entries.add(GTGItems.WATER_TERA_SHARD);
            entries.add(GTGItems.STELLAR_TERA_SHARD);
        })
        .build();

    public static final ItemGroup FORM_ITEMS = PolymerItemGroupUtils.builder()
        .icon(GTGItems.WELLSPRING_MASK::getDefaultStack)
        .displayName(Text.translatable("gimmethatgimmick.itemGroup.form"))
        .entries((displayContext, entries) -> {
            entries.add(GTGItems.METEORITE);
            entries.add(GTGItems.REIGNS_OF_UNITY);
            entries.add(GTGItems.REVEAL_GLASS);
            entries.add(GTGItems.DNA_SPLICERS);
            entries.add(GTGItems.GRACIDEA_FLOWER);
            entries.add(GTGItems.PRISON_BOTTLE);
            entries.add(GTGItems.ZYGARDE_CUBE);
            entries.add(GTGItems.N_LUNARIZER);
            entries.add(GTGItems.N_SOLARIZER);
            entries.add(GTGItems.ROTOM_CATALOG);
            entries.add(GTGItems.ADAMANT_CRYSTAL);
            entries.add(GTGItems.LUSTROUS_GLOBE);
            entries.add(GTGItems.GRISEOUS_CORE);
            entries.add(GTGItems.BLUE_ORB);
            entries.add(GTGItems.RED_ORB);
            entries.add(GTGItems.RUSTED_SWORD);
            entries.add(GTGItems.RUSTED_SHIELD);
            entries.add(GTGItems.DRACO_PLATE);
            entries.add(GTGItems.DREAD_PLATE);
            entries.add(GTGItems.EARTH_PLATE);
            entries.add(GTGItems.FIST_PLATE);
            entries.add(GTGItems.FLAME_PLATE);
            entries.add(GTGItems.ICICLE_PLATE);
            entries.add(GTGItems.INSECT_PLATE);
            entries.add(GTGItems.IRON_PLATE);
            entries.add(GTGItems.MEADOW_PLATE);
            entries.add(GTGItems.MIND_PLATE);
            entries.add(GTGItems.PIXIE_PLATE);
            entries.add(GTGItems.SKY_PLATE);
            entries.add(GTGItems.SPLASH_PLATE);
            entries.add(GTGItems.SPOOKY_PLATE);
            entries.add(GTGItems.STONE_PLATE);
            entries.add(GTGItems.TOXIC_PLATE);
            entries.add(GTGItems.ZAP_PLATE);
            entries.add(GTGItems.BUG_MEMORY);
            entries.add(GTGItems.DARK_MEMORY);
            entries.add(GTGItems.DRAGON_MEMORY);
            entries.add(GTGItems.ELECTRIC_MEMORY);
            entries.add(GTGItems.FAIRY_MEMORY);
            entries.add(GTGItems.FIGHTING_MEMORY);
            entries.add(GTGItems.FIRE_MEMORY);
            entries.add(GTGItems.FLYING_MEMORY);
            entries.add(GTGItems.GHOST_MEMORY);
            entries.add(GTGItems.GRASS_MEMORY);
            entries.add(GTGItems.GROUND_MEMORY);
            entries.add(GTGItems.ICE_MEMORY);
            entries.add(GTGItems.POISON_MEMORY);
            entries.add(GTGItems.PSYCHIC_MEMORY);
            entries.add(GTGItems.ROCK_MEMORY);
            entries.add(GTGItems.STEEL_MEMORY);
            entries.add(GTGItems.WATER_MEMORY);
            entries.add(GTGItems.BURN_DRIVE);
            entries.add(GTGItems.CHILL_DRIVE);
            entries.add(GTGItems.DOUSE_DRIVE);
            entries.add(GTGItems.SHOCK_DRIVE);
            entries.add(GTGItems.WELLSPRING_MASK);
            entries.add(GTGItems.HEARTHFLAME_MASK);
            entries.add(GTGItems.CORNERSTONE_MASK);
            entries.add(GTGItems.PINK_NECTAR);
            entries.add(GTGItems.RED_NECTAR);
            entries.add(GTGItems.PURPLE_NECTAR);
            entries.add(GTGItems.YELLOW_NECTAR);
        })
        .build();

    public static void register () {
        PolymerItemGroupUtils.registerPolymerItemGroup(GimmeThatGimmickMain.identifier("mega_items"), MEGA_ITEMS);
        PolymerItemGroupUtils.registerPolymerItemGroup(GimmeThatGimmickMain.identifier("z_items"), Z_ITEMS);
        PolymerItemGroupUtils.registerPolymerItemGroup(GimmeThatGimmickMain.identifier("dynamax_items"), DYNAMAX_ITEMS);
        PolymerItemGroupUtils.registerPolymerItemGroup(GimmeThatGimmickMain.identifier("tera_items"), TERA_ITEMS);
        PolymerItemGroupUtils.registerPolymerItemGroup(GimmeThatGimmickMain.identifier("form_items"), FORM_ITEMS);
    }
}
