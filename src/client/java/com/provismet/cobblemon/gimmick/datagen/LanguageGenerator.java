package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantments;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import com.provismet.lilylib.datagen.provider.LilyLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class LanguageGenerator extends LilyLanguageProvider {
    protected LanguageGenerator (FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations (RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        keyItems(translationBuilder);
        megaStones(translationBuilder);
        zCrystals(translationBuilder);
        dynamax(translationBuilder);
        teraShards(translationBuilder);

        translationBuilder.add(GTGItemTags.GIMMICK_KEY_ITEMS, "Gimmick Key Items");
        translationBuilder.add(GTGItemTags.KEY_STONES, "Grants Mega Evolution");
        translationBuilder.add(GTGItemTags.Z_RINGS, "Grants Z-Moves");
        translationBuilder.add(GTGItemTags.DYNAMAX_BANDS, "Grants Dynamax");
        translationBuilder.add(GTGItemTags.TERA_ORBS, "Grants Terastallization");
        translationBuilder.add(GTGItemTags.MEGA_STONES, "Mega Stones");
        translationBuilder.add(GTGItemTags.MEGA_STONES_X, "Mega Stones (X)");
        translationBuilder.add(GTGItemTags.MEGA_STONES_Y, "Mega Stones (Y)");
        translationBuilder.add(GTGItemTags.Z_CRYSTALS, "Z-Crystals");
        translationBuilder.add(GTGItemTags.Z_CRYSTAL_TYPE, "Elemental Z-Crystals");
        translationBuilder.add(GTGItemTags.Z_CRYSTAL_SPECIES, "Exclusive Z-Crystals");
        translationBuilder.add(GTGItemTags.TERA_SHARDS, "Tera Shards");
        translationBuilder.add(GTGItemTags.GIMMICK_ENCHANTABLE, "Gimmick Enchantable");

        translationBuilder.add("message.overlay.gimmethatgimmick.yes", "Yes");
        translationBuilder.add("message.overlay.gimmethatgimmick.no", "No");

        translationBuilder.add("gimmethatgimmick.itemGroup.name", "Gimme That Gimmick");
    }

    private static void addItemWithTooltip (TranslationBuilder translationBuilder, PolymerHeldItem item, String name, String... tooltip) {
        translationBuilder.add(item, name);
        for (int i = 0; i < tooltip.length; ++i) {
            translationBuilder.add(item.getTooltipTranslationKey(i + 1), tooltip[i]);
        }
    }

    private void keyItems (TranslationBuilder translationBuilder) {
        addItemWithTooltip(translationBuilder, GTGItems.MEGA_BRACELET, "Mega Bracelet", "Hold this in your hand to gain access to Mega Evolution.");
        addItemWithTooltip(translationBuilder, GTGItems.Z_RING, "Z-Ring", "Hold this in your hand to gain access to Z-Moves.");
        addItemWithTooltip(translationBuilder, GTGItems.DYNAMAX_BAND, "Dynamax Band", "Hold this in your hand to gain access to Dynamax.");
        addItemWithTooltip(translationBuilder, GTGItems.TERA_ORB, "Tera Orb", "Hold this in your hand to gain access to Terastallization.");
        translationBuilder.add(GTGBlocks.POWER_SPOT, "Power Spot");

        this.addEnchantment(translationBuilder, GTGEnchantments.KEY_STONE, "Key Stone", "Grants access to Mega Evolution.");
        this.addEnchantment(translationBuilder, GTGEnchantments.Z_RING, "Z-Power", "Grants access to Z-Moves.");
        this.addEnchantment(translationBuilder, GTGEnchantments.DYNAMAX_BAND, "Dynamax", "Grants access to Dynamax");
        this.addEnchantment(translationBuilder, GTGEnchantments.TERA_ORB, "Terastal", "Grants access to Terastallization.");
    }

    private void megaStones (TranslationBuilder translationBuilder) {
        addItemWithTooltip(translationBuilder, GTGItems.ABOMASITE, "Abomasite", "One of a variety of mysterious Mega Stones.", "Have Abomasnow hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.ABSOLITE, "Absolite", "One of a variety of mysterious Mega Stones.", "Have Absol hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.AERODACTYLITE, "Aerodactylite", "One of a variety of mysterious Mega Stones.", "Have Aerodactyl hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.AGGRONITE, "Aggronite", "One of a variety of mysterious Mega Stones.", "Have Aggron hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.ALAKAZITE, "Alakazite", "One of a variety of mysterious Mega Stones.", "Have Alakazam hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.ALTARIANITE, "Altarianite", "One of a variety of mysterious Mega Stones.", "Have Altaria hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.AMPHAROSITE, "Ampharosite", "One of a variety of mysterious Mega Stones.", "Have Ampharos hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.AUDINITE, "Audinite", "One of a variety of mysterious Mega Stones.", "Have Audino hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.BANETTITE, "Banettite", "One of a variety of mysterious Mega Stones.", "Have Banette hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.BEEDRILLITE, "Beedrillite", "One of a variety of mysterious Mega Stones.", "Have Beedrill hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.BLASTOISINITE, "Blastoisinite", "One of a variety of mysterious Mega Stones.", "Have Blastoise hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.BLAZIKENITE, "Blazikenite", "One of a variety of mysterious Mega Stones.", "Have Blaziken hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.CAMERUPTITE, "Cameruptite", "One of a variety of mysterious Mega Stones.", "Have Camerupt hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.CHARIZARDITE_X, "Charizardite X", "One of a variety of mysterious Mega Stones.", "Have Charizard hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.CHARIZARDITE_Y, "Charizardite Y", "One of a variety of mysterious Mega Stones.", "Have Charizard hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.DIANCITE, "Diancite", "One of a variety of mysterious Mega Stones.", "Have Diancie hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GALLADITE, "Galladite", "One of a variety of mysterious Mega Stones.", "Have Gallade hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GARCHOMPITE, "Garchompite", "One of a variety of mysterious Mega Stones.", "Have Garchomp hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GARDEVOIRITE, "Gardevoirite", "One of a variety of mysterious Mega Stones.", "Have Gardevoir hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GENGARITE, "Gengarite", "One of a variety of mysterious Mega Stones.", "Have Gengar hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GLALITITE, "Glalitite", "One of a variety of mysterious Mega Stones.", "Have Glalie hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.GYARADOSITE, "Gyaradosite", "One of a variety of mysterious Mega Stones.", "Have Gyarados hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.HERACRONITE, "Heracronite", "One of a variety of mysterious Mega Stones.", "Have Heracross hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.HOUNDOOMINITE, "Houndoominite", "One of a variety of mysterious Mega Stones.", "Have Houndoom hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.KANGASKHANITE, "Kangaskhanite", "One of a variety of mysterious Mega Stones.", "Have Kangaskhan hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.LATIASITE, "Latiasite", "One of a variety of mysterious Mega Stones.", "Have Latias hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.LATIOSITE, "Latiosite", "One of a variety of mysterious Mega Stones.", "Have Latios hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.LOPUNNITE, "Lopunnite", "One of a variety of mysterious Mega Stones.", "Have Lopunny hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.LUCARIONITE, "Lucarionite", "One of a variety of mysterious Mega Stones.", "Have Lucario hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.MANECTITE, "Manectite", "One of a variety of mysterious Mega Stones.", "Have Manectric hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.MAWILITE, "Mawilite", "One of a variety of mysterious Mega Stones.", "Have Mawile hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.MEDICHAMITE, "Medichamite", "One of a variety of mysterious Mega Stones.", "Have Medicham hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.METAGROSSITE, "Metagrossite", "One of a variety of mysterious Mega Stones.", "Have Metagross hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.MEWTWONITE_X, "Mewtwonite X", "One of a variety of mysterious Mega Stones.", "Have Mewtwo hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.MEWTWONITE_Y, "Mewtwonite Y", "One of a variety of mysterious Mega Stones.", "Have Mewtwo hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.PIDGEOTITE, "Pidgeotite", "One of a variety of mysterious Mega Stones.", "Have Pidgeot hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.PINSIRITE, "Pinsirite", "One of a variety of mysterious Mega Stones.", "Have Pinsir hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SABLENITE, "Sablenite", "One of a variety of mysterious Mega Stones.", "Have Sableye hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SALAMENCITE, "Salamencite", "One of a variety of mysterious Mega Stones.", "Have Salamence hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SCEPTILITE, "Sceptilite", "One of a variety of mysterious Mega Stones.", "Have Sceptile hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SCIZORITE, "Scizorite", "One of a variety of mysterious Mega Stones.", "Have Scizor hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SHARPEDONITE, "Sharpedonite", "One of a variety of mysterious Mega Stones.", "Have Sharpedo hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SLOWBRONITE, "Slowbronite", "One of a variety of mysterious Mega Stones.", "Have Slowbro hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.STEELIXITE, "Steelixite", "One of a variety of mysterious Mega Stones.", "Have Steelix hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.SWAMPERTITE, "Swampertite", "One of a variety of mysterious Mega Stones.", "Have Swampert hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.TYRANITARITE, "Tyranitarite", "One of a variety of mysterious Mega Stones.", "Have Tyranitar hold it, and this stone will enable it to Mega Evolve during battle.");
        addItemWithTooltip(translationBuilder, GTGItems.VENUSAURITE, "Venusaurite", "One of a variety of mysterious Mega Stones.", "Have Venusaur hold it, and this stone will enable it to Mega Evolve during battle.");
    }

    private void zCrystals (TranslationBuilder translationBuilder) {
        addItemWithTooltip(translationBuilder, GTGItems.ALORAICHIUM_Z, "Aloraichium-Z", zCrystalTooltip("Alolan Raichu", "Thunderbolt"));
        addItemWithTooltip(translationBuilder, GTGItems.DECIDIUM_Z, "Decidium-Z", zCrystalTooltip("Decidueye", "Spirit Shackle"));
        addItemWithTooltip(translationBuilder, GTGItems.EEVIUM_Z, "Eevium-Z", zCrystalTooltip("Eevee", "Last Resort"));
        addItemWithTooltip(translationBuilder, GTGItems.INCINIUM_Z, "Incinium-Z", zCrystalTooltip("Inciniroar", "Darkest Lariat"));
        addItemWithTooltip(translationBuilder, GTGItems.KOMMONIUM_Z, "Kommonium-Z", zCrystalTooltip("Kommo-o", "Clanging Scales"));
        addItemWithTooltip(translationBuilder, GTGItems.LUNALIUM_Z, "Lunalium-Z", zCrystalTooltip("Lunala",  "Moongeist Beam"));
        addItemWithTooltip(translationBuilder, GTGItems.LYCANIUM_Z, "Lycanium-Z", zCrystalTooltip("Lycanroc", "Stone Edge"));
        addItemWithTooltip(translationBuilder, GTGItems.MARSHADIUM_Z, "Marshadium-Z", zCrystalTooltip("Marshadow", "Spectral Thief"));
        addItemWithTooltip(translationBuilder, GTGItems.MEWNIUM_Z, "Mewnium-Z", zCrystalTooltip("Mew", "Psychic"));
        addItemWithTooltip(translationBuilder, GTGItems.MIMIKIUM_Z, "Mimikium-Z", zCrystalTooltip("Mimikyu", "Play Rough"));
        addItemWithTooltip(translationBuilder, GTGItems.PIKANIUM_Z, "Pikanium-Z", zCrystalTooltip("Pikachu", "Volt Tackle"));
        addItemWithTooltip(translationBuilder, GTGItems.PIKASHUNIUM_Z, "Pikashunium-Z", "This is a crystallized form of Z-Power.", "It upgrades Thunderbolt to a Z-Move when used by a Pikachu wearing a cap.");
        addItemWithTooltip(translationBuilder, GTGItems.PRIMARIUM_Z, "Primarium-Z", zCrystalTooltip("Primarina", "Sparkling Aria"));
        addItemWithTooltip(translationBuilder, GTGItems.SNORLIUM_Z, "Snorlium-Z", zCrystalTooltip("Snorlax", "Giga Impact"));
        addItemWithTooltip(translationBuilder, GTGItems.SOLGANIUM_Z, "Solganium-Z", zCrystalTooltip("Solgaleo", "Sunsteel Strike"));
        addItemWithTooltip(translationBuilder, GTGItems.TAPUNIUM_Z, "Tapunium-Z", zCrystalTooltip("the tapu", "Nature's Madness"));
        addItemWithTooltip(translationBuilder, GTGItems.ULTRANECROZIUM_Z, "Ultranecrozium-Z", "It's a crystal that turns Necrozma fused with Solgaleo or Lunala into a new form.");

        addItemWithTooltip(translationBuilder, GTGItems.BUGINIUM_Z, "Buginium-Z", zCrystalTooltip("Bug"));
        addItemWithTooltip(translationBuilder, GTGItems.DARKINIUM_Z, "Darkinium-Z", zCrystalTooltip("Dark"));
        addItemWithTooltip(translationBuilder, GTGItems.DRAGONIUM_Z, "Dragonium-Z", zCrystalTooltip("Dragon"));
        addItemWithTooltip(translationBuilder, GTGItems.ELECTRIUM_Z, "Electrium-Z", zCrystalTooltip("Electric"));
        addItemWithTooltip(translationBuilder, GTGItems.FAIRIUM_Z, "Fairium-Z", zCrystalTooltip("Fairy"));
        addItemWithTooltip(translationBuilder, GTGItems.FIGHTINIUM_Z, "Fightinium-Z", zCrystalTooltip("Fighting"));
        addItemWithTooltip(translationBuilder, GTGItems.FIRIUM_Z, "Firium-Z", zCrystalTooltip("Fire"));
        addItemWithTooltip(translationBuilder, GTGItems.FLYINIUM_Z, "Flyinium-Z", zCrystalTooltip("Flying"));
        addItemWithTooltip(translationBuilder, GTGItems.GHOSTIUM_Z, "Ghostium-Z", zCrystalTooltip("Ghost"));
        addItemWithTooltip(translationBuilder, GTGItems.GRASSIUM_Z, "Grassium-Z", zCrystalTooltip("Grass"));
        addItemWithTooltip(translationBuilder, GTGItems.GROUNDIUM_Z, "Groundium-Z", zCrystalTooltip("Ground"));
        addItemWithTooltip(translationBuilder, GTGItems.ICIUM_Z, "Icium-Z", zCrystalTooltip("Ice"));
        addItemWithTooltip(translationBuilder, GTGItems.NORMALIUM_Z, "Normalium-Z", zCrystalTooltip("Normal"));
        addItemWithTooltip(translationBuilder, GTGItems.POISONIUM_Z, "Poisonium-Z", zCrystalTooltip("Poison"));
        addItemWithTooltip(translationBuilder, GTGItems.PSYCHIUM_Z, "Psychium-Z", zCrystalTooltip("Psychic"));
        addItemWithTooltip(translationBuilder, GTGItems.ROCKIUM_Z, "Rockium-Z", zCrystalTooltip("Rock"));
        addItemWithTooltip(translationBuilder, GTGItems.STEELIUM_Z, "Steelium-Z", zCrystalTooltip("Steel"));
        addItemWithTooltip(translationBuilder, GTGItems.WATERIUM_Z, "Waterium-Z", zCrystalTooltip("Water"));
    }

    private void dynamax (TranslationBuilder translationBuilder) {
        addItemWithTooltip(translationBuilder, GTGItems.DYNAMAX_CANDY, "Dynamax Candy", "A candy that is packed with energy. If consumed, it raises the Dynamax Level of a Pokémon by one.", "A higher level means higher HP when Dynamaxed. ");
        addItemWithTooltip(translationBuilder, GTGItems.MAX_SOUP, "Max Soup", "Grants the Gigantamax factor to eligible Pokemon or removes it if already present.");
        translationBuilder.add(GTGItems.MAX_MUSHROOM, "Max Mushroom");

        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax", "DMax Level: %1$s, GMax: %2$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.candy", "%1$s has reached Dynamax Level: %2$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.soup.yes", "%1$s can now Gigantamax");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.soup.no", "%1$s can no longer Gigantamax");
    }

    private void teraShards (TranslationBuilder translationBuilder) {
        translationBuilder.add(GTGItems.BUG_TERA_SHARD, "Bug Tera Shard");
        translationBuilder.add(GTGItems.DARK_TERA_SHARD, "Dark Tera Shard");
        translationBuilder.add(GTGItems.DRAGON_TERA_SHARD, "Dragon Tera Shard");
        translationBuilder.add(GTGItems.ELECTRIC_TERA_SHARD, "Electric Tera Shard");
        translationBuilder.add(GTGItems.FAIRY_TERA_SHARD, "Fairy Tera Shard");
        translationBuilder.add(GTGItems.FIGHTING_TERA_SHARD, "Fighting Tera Shard");
        translationBuilder.add(GTGItems.FIRE_TERA_SHARD, "Fire Tera Shard");
        translationBuilder.add(GTGItems.FLYING_TERA_SHARD, "Flying Tera Shard");
        translationBuilder.add(GTGItems.GHOST_TERA_SHARD, "Ghost Tera Shard");
        translationBuilder.add(GTGItems.GRASS_TERA_SHARD, "Grass Tera Shard");
        translationBuilder.add(GTGItems.GROUND_TERA_SHARD, "Ground Tera Shard");
        translationBuilder.add(GTGItems.ICE_TERA_SHARD, "Ice Tera Shard");
        translationBuilder.add(GTGItems.NORMAL_TERA_SHARD, "Normal Tera Shard");
        translationBuilder.add(GTGItems.POISON_TERA_SHARD, "Poison Tera Shard");
        translationBuilder.add(GTGItems.PSYCHIC_TERA_SHARD, "Psychic Tera Shard");
        translationBuilder.add(GTGItems.ROCK_TERA_SHARD, "Rock Tera Shard");
        translationBuilder.add(GTGItems.STEEL_TERA_SHARD, "Steel Tera Shard");
        translationBuilder.add(GTGItems.WATER_TERA_SHARD, "Water Tera Shard");
        translationBuilder.add(GTGItems.STELLAR_TERA_SHARD, "Stellar Tera Shard");

        translationBuilder.add("message.overlay.gimmethatgimmick.tera", "Tera Type: %1$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.tera.already_has", "%1$s already has this Tera type");
        translationBuilder.add("message.overlay.gimmethatgimmick.tera.set", "Set the Tera type of %1$s to %2$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.tera.no_shards", "You need 50 shards to change the Tera type");
    }

    private static String[] zCrystalTooltip (String type) {
        return new String[]{"This is a crystallized form of Z-Power.", "It upgrades " + type + "-type moves to Z-Moves."};
    }

    private static String[] zCrystalTooltip (String pokemon, String move) {
        return new String[]{"This is a crystallized form of Z-Power.", "It upgrades " + pokemon + "'s " + move + " to a Z-Move."};
    }
}
