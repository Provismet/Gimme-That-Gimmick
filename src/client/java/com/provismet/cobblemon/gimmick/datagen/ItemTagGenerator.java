package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
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

        this.getOrCreateTagBuilder(GTGItemTags.TERA_ORBS)
            .add(GTGItems.TERA_ORB);

        // Addons should add to this.
        this.getOrCreateTagBuilder(GTGItemTags.GIMMICK_ENCHANTABLE);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES_X)
            .add(GTGItems.CHARIZARDITE_X)
            .add(GTGItems.MEWTWONITE_X);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES_Y)
            .add(GTGItems.CHARIZARDITE_Y)
            .add(GTGItems.MEWTWONITE_Y);

        this.getOrCreateTagBuilder(GTGItemTags.MEGA_STONES)
            .addOptionalTag(GTGItemTags.MEGA_STONES_X)
            .addOptionalTag(GTGItemTags.MEGA_STONES_Y)
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
            .add(GTGItems.VENUSAURITE);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTALS)
            .addOptionalTag(GTGItemTags.Z_CRYSTAL_TYPE)
            .addOptionalTag(GTGItemTags.Z_CRYSTAL_SPECIES);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTAL_TYPE);

        this.getOrCreateTagBuilder(GTGItemTags.Z_CRYSTAL_SPECIES);

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
    }
}
