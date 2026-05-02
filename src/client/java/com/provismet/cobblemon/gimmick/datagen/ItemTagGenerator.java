package com.provismet.cobblemon.gimmick.datagen;

import com.cobblemon.mod.common.api.tags.CobblemonItemTags;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public ItemTagGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture, null);
    }

    @Override
    protected void configure (RegistryWrapper.WrapperLookup wrapperLookup) {
        this.getOrCreateTagBuilder(GTGItemTags.GIMMICK_KEY_ITEMS)
            .addOptionalTag(GTGItemTags.KEY_STONES)
            .addOptionalTag(GTGItemTags.Z_RINGS)
            .addOptionalTag(GTGItemTags.DYNAMAX_BANDS)
            .addOptionalTag(GTGItemTags.TERA_ORBS);

        this.getOrCreateTagBuilder(GTGItemTags.KEY_STONES)
            .add(GTGItems.MEGA_BRACELET);

        this.getOrCreateTagBuilder(GTGItemTags.Z_RINGS)
            .add(GTGItems.Z_RING);

        this.getOrCreateTagBuilder(GTGItemTags.DYNAMAX_BANDS)
            .add(GTGItems.DYNAMAX_BAND);

        this.getOrCreateTagBuilder(GTGItemTags.BREAKABLE_TERA_ORBS)
            .add(GTGItems.TERA_ORB);

        this.getOrCreateTagBuilder(GTGItemTags.TERA_ORBS)
            .addOptionalTag(GTGItemTags.BREAKABLE_TERA_ORBS);

        // Addons should add to this.
        this.getOrCreateTagBuilder(GTGItemTags.GIMMICK_ENCHANTABLE);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES_X)
            .add(GTGItems.CHARIZARDITE_X)
            .add(GTGItems.MEWTWONITE_X)
            .add(GTGItems.RAICHUNITE_X);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES_Y)
            .add(GTGItems.CHARIZARDITE_Y)
            .add(GTGItems.MEWTWONITE_Y)
            .add(GTGItems.RAICHUNITE_Y);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES_Z)
            .add(GTGItems.ABSOLITE_Z)
            .add(GTGItems.GARCHOMPITE_Z)
            .add(GTGItems.LUCARIONITE_Z);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES)
            .addOptionalTag(GTGItemTags.MEGA_STONES_X)
            .addOptionalTag(GTGItemTags.MEGA_STONES_Y)
            .addOptionalTag(GTGItemTags.MEGA_STONES_Z)
            .add(GTGItems.ABOMASITE)
            .add(GTGItems.ABSOLITE)
            .add(GTGItems.AERODACTYLITE)
            .add(GTGItems.AGGRONITE)
            .add(GTGItems.ALAKAZITE)
            .add(GTGItems.ALTARIANITE)
            .add(GTGItems.AMPHAROSITE)
            .add(GTGItems.AUDINITE)
            .add(GTGItems.BANETTITE)
            .add(GTGItems.BEEDRILLITE)
            .add(GTGItems.BLASTOISINITE)
            .add(GTGItems.BLAZIKENITE)
            .add(GTGItems.CAMERUPTITE)
            .add(GTGItems.DIANCITE)
            .add(GTGItems.GALLADITE)
            .add(GTGItems.GARCHOMPITE)
            .add(GTGItems.GARDEVOIRITE)
            .add(GTGItems.GENGARITE)
            .add(GTGItems.GLALITITE)
            .add(GTGItems.GYARADOSITE)
            .add(GTGItems.HERACRONITE)
            .add(GTGItems.HOUNDOOMINITE)
            .add(GTGItems.KANGASKHANITE)
            .add(GTGItems.LATIASITE)
            .add(GTGItems.LATIOSITE)
            .add(GTGItems.LOPUNNITE)
            .add(GTGItems.LUCARIONITE)
            .add(GTGItems.MANECTITE)
            .add(GTGItems.MAWILITE)
            .add(GTGItems.MEDICHAMITE)
            .add(GTGItems.METAGROSSITE)
            .add(GTGItems.PIDGEOTITE)
            .add(GTGItems.PINSIRITE)
            .add(GTGItems.SABLENITE)
            .add(GTGItems.SALAMENCITE)
            .add(GTGItems.SCEPTILITE)
            .add(GTGItems.SCIZORITE)
            .add(GTGItems.SHARPEDONITE)
            .add(GTGItems.SLOWBRONITE)
            .add(GTGItems.STEELIXITE)
            .add(GTGItems.SWAMPERTITE)
            .add(GTGItems.TYRANITARITE)
            .add(GTGItems.VENUSAURITE)
            .add(GTGItems.BARBARACITE)
            .add(GTGItems.BAXCALIBRITE)
            .add(GTGItems.CHANDELURITE)
            .add(GTGItems.CHESNAUGHTITE)
            .add(GTGItems.CHIMECHITE)
            .add(GTGItems.CLEFABLITE)
            .add(GTGItems.CRABOMINITE)
            .add(GTGItems.DARKRANITE)
            .add(GTGItems.DELPHOXITE)
            .add(GTGItems.DRAGALGITE)
            .add(GTGItems.DRAGONINITE)
            .add(GTGItems.DRAMPANITE)
            .add(GTGItems.EELEKTROSSITE)
            .add(GTGItems.EMBOARITE)
            .add(GTGItems.EXCADRITE)
            .add(GTGItems.FALINKSITE)
            .add(GTGItems.FERALIGITE)
            .add(GTGItems.FLOETTITE)
            .add(GTGItems.FROSLASSITE)
            .add(GTGItems.GLIMMORANITE)
            .add(GTGItems.GOLISOPITE)
            .add(GTGItems.GOLURKITE)
            .add(GTGItems.GRENINJITE)
            .add(GTGItems.HAWLUCHANITE)
            .add(GTGItems.HEATRANITE)
            .add(GTGItems.MAGEARNITE)
            .add(GTGItems.MALAMARITE)
            .add(GTGItems.MEGANIUMITE)
            .add(GTGItems.MEOWSTICITE)
            .add(GTGItems.PYROARITE)
            .add(GTGItems.SCOLIPITE)
            .add(GTGItems.SCOVILLAINITE)
            .add(GTGItems.SCRAFTINITE)
            .add(GTGItems.SKARMORITE)
            .add(GTGItems.STARAPTITE)
            .add(GTGItems.STARMINITE)
            .add(GTGItems.TATSUGIRINITE)
            .add(GTGItems.VICTREEBELITE)
            .add(GTGItems.ZERAORITE)
            .add(GTGItems.ZYGARDITE);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTALS)
            .addOptionalTag(GTGItemTags.Z_CRYSTAL_TYPE)
            .addOptionalTag(GTGItemTags.Z_CRYSTAL_SPECIES);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTAL_TYPE)
            .add(GTGItems.BUGINIUM_Z)
            .add(GTGItems.DARKINIUM_Z)
            .add(GTGItems.DRAGONIUM_Z)
            .add(GTGItems.ELECTRIUM_Z)
            .add(GTGItems.FAIRIUM_Z)
            .add(GTGItems.FIGHTINIUM_Z)
            .add(GTGItems.FIRIUM_Z)
            .add(GTGItems.FLYINIUM_Z)
            .add(GTGItems.GHOSTIUM_Z)
            .add(GTGItems.GRASSIUM_Z)
            .add(GTGItems.GROUNDIUM_Z)
            .add(GTGItems.ICIUM_Z)
            .add(GTGItems.NORMALIUM_Z)
            .add(GTGItems.POISONIUM_Z)
            .add(GTGItems.PSYCHIUM_Z)
            .add(GTGItems.ROCKIUM_Z)
            .add(GTGItems.STEELIUM_Z)
            .add(GTGItems.WATERIUM_Z);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTAL_SPECIES)
            .add(GTGItems.ALORAICHIUM_Z)
            .add(GTGItems.DECIDIUM_Z)
            .add(GTGItems.EEVIUM_Z)
            .add(GTGItems.INCINIUM_Z)
            .add(GTGItems.KOMMONIUM_Z)
            .add(GTGItems.LUNALIUM_Z)
            .add(GTGItems.LYCANIUM_Z)
            .add(GTGItems.MARSHADIUM_Z)
            .add(GTGItems.MEWNIUM_Z)
            .add(GTGItems.MIMIKIUM_Z)
            .add(GTGItems.PIKANIUM_Z)
            .add(GTGItems.PIKASHUNIUM_Z)
            .add(GTGItems.PRIMARIUM_Z)
            .add(GTGItems.SNORLIUM_Z)
            .add(GTGItems.SOLGANIUM_Z)
            .add(GTGItems.TAPUNIUM_Z)
            .add(GTGItems.ULTRANECROZIUM_Z);

        this.getOrCreateTagBuilder(GTGItemTags.TERA_SHARDS)
            .add(GTGItems.BUG_TERA_SHARD)
            .add(GTGItems.DARK_TERA_SHARD)
            .add(GTGItems.DRAGON_TERA_SHARD)
            .add(GTGItems.ELECTRIC_TERA_SHARD)
            .add(GTGItems.FAIRY_TERA_SHARD)
            .add(GTGItems.FIGHTING_TERA_SHARD)
            .add(GTGItems.FIRE_TERA_SHARD)
            .add(GTGItems.FLYING_TERA_SHARD)
            .add(GTGItems.GHOST_TERA_SHARD)
            .add(GTGItems.GRASS_TERA_SHARD)
            .add(GTGItems.GROUND_TERA_SHARD)
            .add(GTGItems.ICE_TERA_SHARD)
            .add(GTGItems.NORMAL_TERA_SHARD)
            .add(GTGItems.POISON_TERA_SHARD)
            .add(GTGItems.PSYCHIC_TERA_SHARD)
            .add(GTGItems.ROCK_TERA_SHARD)
            .add(GTGItems.STEEL_TERA_SHARD)
            .add(GTGItems.WATER_TERA_SHARD)
            .add(GTGItems.STELLAR_TERA_SHARD);

        this.getOrCreateTagBuilder(GTGItemTags.ARCEUS_PLATES)
            .add(GTGItems.DRACO_PLATE)
            .add(GTGItems.DREAD_PLATE)
            .add(GTGItems.EARTH_PLATE)
            .add(GTGItems.FIST_PLATE)
            .add(GTGItems.FLAME_PLATE)
            .add(GTGItems.ICICLE_PLATE)
            .add(GTGItems.INSECT_PLATE)
            .add(GTGItems.IRON_PLATE)
            .add(GTGItems.MEADOW_PLATE)
            .add(GTGItems.MIND_PLATE)
            .add(GTGItems.PIXIE_PLATE)
            .add(GTGItems.SKY_PLATE)
            .add(GTGItems.SPLASH_PLATE)
            .add(GTGItems.SPOOKY_PLATE)
            .add(GTGItems.STONE_PLATE)
            .add(GTGItems.TOXIC_PLATE)
            .add(GTGItems.ZAP_PLATE);

        this.getOrCreateTagBuilder(GTGItemTags.SILVALLY_MEMORIES)
            .add(GTGItems.BUG_MEMORY)
            .add(GTGItems.DARK_MEMORY)
            .add(GTGItems.DRAGON_MEMORY)
            .add(GTGItems.ELECTRIC_MEMORY)
            .add(GTGItems.FAIRY_MEMORY)
            .add(GTGItems.FIGHTING_MEMORY)
            .add(GTGItems.FIRE_MEMORY)
            .add(GTGItems.FLYING_MEMORY)
            .add(GTGItems.GHOST_MEMORY)
            .add(GTGItems.GRASS_MEMORY)
            .add(GTGItems.GROUND_MEMORY)
            .add(GTGItems.ICE_MEMORY)
            .add(GTGItems.POISON_MEMORY)
            .add(GTGItems.PSYCHIC_MEMORY)
            .add(GTGItems.ROCK_MEMORY)
            .add(GTGItems.STEEL_MEMORY)
            .add(GTGItems.WATER_MEMORY);

        this.getOrCreateTagBuilder(GTGItemTags.GENESECT_DRIVES)
            .add(GTGItems.BURN_DRIVE)
            .add(GTGItems.CHILL_DRIVE)
            .add(GTGItems.DOUSE_DRIVE)
            .add(GTGItems.SHOCK_DRIVE);

        this.getOrCreateTagBuilder(GTGItemTags.ORICORIO_NECTAR)
            .add(GTGItems.PINK_NECTAR)
            .add(GTGItems.PURPLE_NECTAR)
            .add(GTGItems.RED_NECTAR)
            .add(GTGItems.YELLOW_NECTAR);

        this.getOrCreateTagBuilder(GTGItemTags.OGERPON_MASKS)
            .add(GTGItems.WELLSPRING_MASK)
            .add(GTGItems.HEARTHFLAME_MASK)
            .add(GTGItems.CORNERSTONE_MASK);

        this.getOrCreateTagBuilder(GTGItemTags.HOENN_ORBS)
            .add(GTGItems.RED_ORB)
            .add(GTGItems.BLUE_ORB);

        this.getOrCreateTagBuilder(GTGItemTags.SINNOH_ORBS)
            .add(GTGItems.ADAMANT_CRYSTAL)
            .add(GTGItems.LUSTROUS_GLOBE)
            .add(GTGItems.GRISEOUS_CORE);

        this.getOrCreateTagBuilder(GTGItemTags.GALAR_RUSTED)
            .add(GTGItems.RUSTED_SWORD)
            .add(GTGItems.RUSTED_SHIELD);

        this.getOrCreateTagBuilder(GTGItemTags.FORM_CHANGE)
            .addOptionalTag(GTGItemTags.Z_CRYSTAL_TYPE) // Arceus
            .addOptionalTag(GTGItemTags.ARCEUS_PLATES)
            .addOptionalTag(GTGItemTags.SILVALLY_MEMORIES)
            .addOptionalTag(GTGItemTags.GENESECT_DRIVES)
            .addOptionalTag(GTGItemTags.ORICORIO_NECTAR)
            .addOptionalTag(GTGItemTags.OGERPON_MASKS)
            .addOptionalTag(GTGItemTags.HOENN_ORBS)
            .addOptionalTag(GTGItemTags.SINNOH_ORBS)
            .addOptionalTag(GTGItemTags.GALAR_RUSTED);

        this.getOrCreateTagBuilder(CobblemonItemTags.ANY_HELD_ITEM)
            .addOptionalTag(GTGItemTags.MEGA_STONES)
            .addOptionalTag(GTGItemTags.Z_CRYSTALS)
            .addOptionalTag(GTGItemTags.ARCEUS_PLATES)
            .addOptionalTag(GTGItemTags.SILVALLY_MEMORIES)
            .addOptionalTag(GTGItemTags.GENESECT_DRIVES)
            .addOptionalTag(GTGItemTags.OGERPON_MASKS);

        this.getOrCreateTagBuilder(ConventionalItemTags.MUSHROOMS)
            .add(GTGItems.MAX_MUSHROOM);
    }
}
