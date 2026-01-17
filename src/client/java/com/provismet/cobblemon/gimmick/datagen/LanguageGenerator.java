package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.item.NumericalTooltipItem;
import com.provismet.cobblemon.gimmick.registry.GTGBlocks;
import com.provismet.cobblemon.gimmick.registry.GTGEnchantments;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import com.provismet.lilylib.datagen.provider.LilyLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

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

        zMoves(translationBuilder);
        gmaxEffects(translationBuilder);
        formChanges(translationBuilder);
        battleMessages(translationBuilder);

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
        translationBuilder.add("command.gimme-that-gimmick.reload.success", "Reloaded Gimme That Gimmick config.");
    }

    public static <T extends Item & NumericalTooltipItem> void addItemWithTooltip (TranslationBuilder translationBuilder, T item, String name, String... tooltip) {
        translationBuilder.add(item, name);
        for (int i = 0; i < tooltip.length; ++i) {
            translationBuilder.add(item.getTooltipTranslationKey(i + 1), tooltip[i]);
        }
    }

    private void keyItems (TranslationBuilder translationBuilder) {
        addItemWithTooltip(translationBuilder, GTGItems.MEGA_BRACELET, "Mega Bracelet", "Hold this in your hand to gain access to Mega Evolution");
        addItemWithTooltip(translationBuilder, GTGItems.Z_RING, "Z-Ring", "Hold this in your hand to gain access to Z-Moves");
        addItemWithTooltip(translationBuilder, GTGItems.DYNAMAX_BAND, "Dynamax Band", "Hold this in your hand to gain access to Dynamax");
        addItemWithTooltip(translationBuilder, GTGItems.TERA_ORB, "Tera Orb", "Hold this in your hand to gain access to Terastallization");
        translationBuilder.add(GTGBlocks.POWER_SPOT, "Power Spot");

        this.addEnchantment(translationBuilder, GTGEnchantments.KEY_STONE, "Key Stone", "Grants access to Mega Evolution.");
        this.addEnchantment(translationBuilder, GTGEnchantments.Z_RING, "Z-Power", "Grants access to Z-Moves.");
        this.addEnchantment(translationBuilder, GTGEnchantments.DYNAMAX_BAND, "Dynamax", "Grants access to Dynamax");
        this.addEnchantment(translationBuilder, GTGEnchantments.TERA_ORB, "Terastal", "Grants access to Terastallization.");
    }

    private void megaStones (TranslationBuilder translationBuilder) {
        Function<String, String[]> megaTooltip = pokemon -> new String[] {"Grants " + pokemon + " the power to Mega Evolve"};

        translationBuilder.add("message.overlay.gimme-that-gimmick.mega_exists", "You already have a Mega Pokémon.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.no_stone", "Your Pokémon is not holding its Mega Stone.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.mega_not_yours", "This is not your Pokémon.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.mega_in_battle", "You cannot perform this action in battle.");
        addItemWithTooltip(translationBuilder, GTGItems.ABOMASITE, "Abomasite", megaTooltip.apply("Abomasnow"));
        addItemWithTooltip(translationBuilder, GTGItems.ABSOLITE, "Absolite", megaTooltip.apply("Absol"));
        addItemWithTooltip(translationBuilder, GTGItems.AERODACTYLITE, "Aerodactylite", megaTooltip.apply("Aerodactyl"));
        addItemWithTooltip(translationBuilder, GTGItems.AGGRONITE, "Aggronite", megaTooltip.apply("Aggron"));
        addItemWithTooltip(translationBuilder, GTGItems.ALAKAZITE, "Alakazite", megaTooltip.apply("Alakazam"));
        addItemWithTooltip(translationBuilder, GTGItems.ALTARIANITE, "Altarianite", megaTooltip.apply("Altaria"));
        addItemWithTooltip(translationBuilder, GTGItems.AMPHAROSITE, "Ampharosite", megaTooltip.apply("Ampharos"));
        addItemWithTooltip(translationBuilder, GTGItems.AUDINITE, "Audinite", megaTooltip.apply("Audino"));
        addItemWithTooltip(translationBuilder, GTGItems.BANETTITE, "Banettite", megaTooltip.apply("Banette"));
        addItemWithTooltip(translationBuilder, GTGItems.BEEDRILLITE, "Beedrillite", megaTooltip.apply("Beedrill"));
        addItemWithTooltip(translationBuilder, GTGItems.BLASTOISINITE, "Blastoisinite", megaTooltip.apply("Blastoise"));
        addItemWithTooltip(translationBuilder, GTGItems.BLAZIKENITE, "Blazikenite", megaTooltip.apply("Blaziken"));
        addItemWithTooltip(translationBuilder, GTGItems.CAMERUPTITE, "Cameruptite", megaTooltip.apply("Camerupt"));
        addItemWithTooltip(translationBuilder, GTGItems.CHARIZARDITE_X, "Charizardite X", megaTooltip.apply("Charizard"));
        addItemWithTooltip(translationBuilder, GTGItems.CHARIZARDITE_Y, "Charizardite Y", megaTooltip.apply("Charizard"));
        addItemWithTooltip(translationBuilder, GTGItems.DIANCITE, "Diancite", megaTooltip.apply("Diancie"));
        addItemWithTooltip(translationBuilder, GTGItems.GALLADITE, "Galladite", megaTooltip.apply("Gallade"));
        addItemWithTooltip(translationBuilder, GTGItems.GARCHOMPITE, "Garchompite", megaTooltip.apply("Garchomp"));
        addItemWithTooltip(translationBuilder, GTGItems.GARDEVOIRITE, "Gardevoirite", megaTooltip.apply("Gardevoir"));
        addItemWithTooltip(translationBuilder, GTGItems.GENGARITE, "Gengarite", megaTooltip.apply("Gengar"));
        addItemWithTooltip(translationBuilder, GTGItems.GLALITITE, "Glalitite", megaTooltip.apply("Glalie"));
        addItemWithTooltip(translationBuilder, GTGItems.GYARADOSITE, "Gyaradosite", megaTooltip.apply("Gyarados"));
        addItemWithTooltip(translationBuilder, GTGItems.HERACRONITE, "Heracronite", megaTooltip.apply("Heracross"));
        addItemWithTooltip(translationBuilder, GTGItems.HOUNDOOMINITE, "Houndoominite", megaTooltip.apply("Houndoom"));
        addItemWithTooltip(translationBuilder, GTGItems.KANGASKHANITE, "Kangaskhanite", megaTooltip.apply("Kangaskhan"));
        addItemWithTooltip(translationBuilder, GTGItems.LATIASITE, "Latiasite", megaTooltip.apply("Latias"));
        addItemWithTooltip(translationBuilder, GTGItems.LATIOSITE, "Latiosite", megaTooltip.apply("Latios"));
        addItemWithTooltip(translationBuilder, GTGItems.LOPUNNITE, "Lopunnite", megaTooltip.apply("Lopunny"));
        addItemWithTooltip(translationBuilder, GTGItems.LUCARIONITE, "Lucarionite", megaTooltip.apply("Lucario"));
        addItemWithTooltip(translationBuilder, GTGItems.MANECTITE, "Manectite", megaTooltip.apply("Manectric"));
        addItemWithTooltip(translationBuilder, GTGItems.MAWILITE, "Mawilite", megaTooltip.apply("Mawile"));
        addItemWithTooltip(translationBuilder, GTGItems.MEDICHAMITE, "Medichamite", megaTooltip.apply("Medicham"));
        addItemWithTooltip(translationBuilder, GTGItems.METAGROSSITE, "Metagrossite", megaTooltip.apply("Metagross"));
        addItemWithTooltip(translationBuilder, GTGItems.MEWTWONITE_X, "Mewtwonite X", megaTooltip.apply("Mewtwo"));
        addItemWithTooltip(translationBuilder, GTGItems.MEWTWONITE_Y, "Mewtwonite Y", megaTooltip.apply("Mewtwo"));
        addItemWithTooltip(translationBuilder, GTGItems.PIDGEOTITE, "Pidgeotite", megaTooltip.apply("Pidgeot"));
        addItemWithTooltip(translationBuilder, GTGItems.PINSIRITE, "Pinsirite", megaTooltip.apply("Pinsir"));
        addItemWithTooltip(translationBuilder, GTGItems.SABLENITE, "Sablenite", megaTooltip.apply("Sableye"));
        addItemWithTooltip(translationBuilder, GTGItems.SALAMENCITE, "Salamencite", megaTooltip.apply("Salamence"));
        addItemWithTooltip(translationBuilder, GTGItems.SCEPTILITE, "Sceptilite", megaTooltip.apply("Sceptile"));
        addItemWithTooltip(translationBuilder, GTGItems.SCIZORITE, "Scizorite", megaTooltip.apply("Scizor"));
        addItemWithTooltip(translationBuilder, GTGItems.SHARPEDONITE, "Sharpedonite", megaTooltip.apply("Sharpedo"));
        addItemWithTooltip(translationBuilder, GTGItems.SLOWBRONITE, "Slowbronite", megaTooltip.apply("Slowbro"));
        addItemWithTooltip(translationBuilder, GTGItems.STEELIXITE, "Steelixite", megaTooltip.apply("Steelix"));
        addItemWithTooltip(translationBuilder, GTGItems.SWAMPERTITE, "Swampertite", megaTooltip.apply("Swampert"));
        addItemWithTooltip(translationBuilder, GTGItems.TYRANITARITE, "Tyranitarite", megaTooltip.apply("Tyranitar"));
        addItemWithTooltip(translationBuilder, GTGItems.VENUSAURITE, "Venusaurite", megaTooltip.apply("Venusaur"));
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
        addItemWithTooltip(translationBuilder, GTGItems.PIKASHUNIUM_Z, "Pikashunium-Z", "Upgrades Thunderbolt to a Z-Move when used by a Pikachu wearing a cap");
        addItemWithTooltip(translationBuilder, GTGItems.PRIMARIUM_Z, "Primarium-Z", zCrystalTooltip("Primarina", "Sparkling Aria"));
        addItemWithTooltip(translationBuilder, GTGItems.SNORLIUM_Z, "Snorlium-Z", zCrystalTooltip("Snorlax", "Giga Impact"));
        addItemWithTooltip(translationBuilder, GTGItems.SOLGANIUM_Z, "Solganium-Z", zCrystalTooltip("Solgaleo", "Sunsteel Strike"));
        addItemWithTooltip(translationBuilder, GTGItems.TAPUNIUM_Z, "Tapunium-Z", zCrystalTooltip("the tapu", "Nature's Madness"));
        addItemWithTooltip(translationBuilder, GTGItems.ULTRANECROZIUM_Z, "Ultranecrozium-Z", "Grants a new form to a fused Necrozma", "Upgrades Photon Geyser to a Z-Move when used by Ultra Necrozma");

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
        addItemWithTooltip(translationBuilder, GTGItems.DYNAMAX_CANDY, "Dynamax Candy", "Raises the Dynamax Level of a Pokémon by one,", "increasing the Pokémon's HP when Dynamaxed");
        addItemWithTooltip(translationBuilder, GTGItems.MAX_SOUP, "Max Soup", "Grants the Gigantamax factor to eligible Pokémon or removes it if already present");
        translationBuilder.add(GTGBlocks.MAX_MUSHROOM, "Max Mushroom");

        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax", "DMax Level: %1$s, GMax: %2$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.candy", "%1$s has reached Dynamax Level: %2$s");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.soup.yes", "%1$s can now Gigantamax");
        translationBuilder.add("message.overlay.gimmethatgimmick.dynamax.soup.no", "%1$s can no longer Gigantamax");

        translationBuilder.add("gimme-that-gimmick.ui.dynamax.level", "Dynamax Level");
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

    private void zMoves (TranslationBuilder translationBuilder) {
        zMove(translationBuilder, "zacidarmor", "Z-Acid Armor", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zacupressure", "Z-Acupressure", "Boosts the user's critical-hit ratio by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zafteryou", "Z-After You", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zagility", "Z-Agility", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zallyswitch", "Z-Ally Switch", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zamnesia", "Z-Amnesia", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zaquaring", "Z-Aqua Ring", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zaromatherapy", "Z-Aromatherapy", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zaromaticmist", "Z-Aromatic Mist", "Raises the user's Special Defense by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zassist", "Z-Assist", "Performs a random Z-Move attack from the party's movepool.");
        zMove(translationBuilder, "zattract", "Z-Attract", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zauroraveil", "Z-Aurora Veil", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zautotomize", "Z-Autotomize", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zbabydolleyes", "Z-Baby-Doll Eyes", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zbanefulbunker", "Z-Baneful Bunker", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zbarrier", "Z-Barrier", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zbatonpass", "Z-Baton Pass", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zbellydrum", "Z-Belly Drum", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zbestow", "Z-Bestow", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zblock", "Z-Block", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zbulkup", "Z-Bulk Up", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcalmmind", "Z-Calm Mind", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zcamouflage", "Z-Camouflage", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zcaptivate", "Z-Captivate", "Raises the user's Special Defense by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zcelebrate", "Z-Celebrate", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcharge", "Z-Charge", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcharm", "Z-Charm", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcoil", "Z-Coil", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zconfide", "Z-Confide", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zconfuseray", "Z-Confuse Ray", "Raises the user's the user's Special Attack by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zconversion", "Z-Conversion", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zconversion2", "Z-Conversion 2", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zcopycat", "Z-Copycat", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcosmicpower", "Z-Cosmic Power", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcottonguard", "Z-Cotton Guard", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zcottonspore", "Z-Cotton Spore", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zcraftyshield", "Z-Crafty Shield", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zcurse", "Z-Curse", "Fully restores user's HP if Ghost-type Raises the user's Attack by 1 stage if non Ghost-type in addition to its usual effect.");
        zMove(translationBuilder, "zdarkvoid", "Z-Dark Void", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zdefendorder", "Z-Defend Order", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zdefensecurl", "Z-Defense Curl", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zdefog", "Z-Defog", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zdestinybond", "Z-Destiny Bond", "User becomes center of attention in addition to its usual effect.");
        zMove(translationBuilder, "zdetect", "Z-Detect", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zdisable", "Z-Disable", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zdoubleteam", "Z-Double Team", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zdragondance", "Z-Dragon Dance", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zeerieimpulse", "Z-Eerie Impulse", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zelectricterrain", "Z-Electric Terrain", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zelectrify", "Z-Electrify", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zembargo", "Z-Embargo", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zencore", "Z-Encore", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zendure", "Z-Endure", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zentrainment", "Z-Entrainment", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfairylock", "Z-Fairy Lock", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfaketears", "Z-Fake Tears", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfeatherdance", "Z-Feather Dance", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zflash", "Z-Flash", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zflatter", "Z-Flatter", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfloralhealing", "Z-Floral Healing", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zflowershield", "Z-Flower Shield", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfocusenergy", "Z-Focus Energy", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zfollowme", "Z-Follow Me", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zforesight", "Z-Foresight", "Boosts the user's critical-hit ratio by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zforestscurse", "Z-Forest's Curse", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgastroacid", "Z-Gastro Acid", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgearup", "Z-Gear Up", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgeomancy", "Z-Geomancy", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zglare", "Z-Glare", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgrasswhistle", "Z-Grass Whistle", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgrassyterrain", "Z-Grassy Terrain", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgravity", "Z-Gravity", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgrowl", "Z-Growl", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgrowth", "Z-Growth", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zgrudge", "Z-Grudge", "User becomes center of attention in addition to its usual effect.");
        zMove(translationBuilder, "zguardsplit", "Z-Guard Split", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zguardswap", "Z-Guard Swap", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhail", "Z-Hail", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhappyhour", "Z-Happy Hour", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zharden", "Z-Harden", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhaze", "Z-Haze", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zhealbell", "Z-Heal Bell", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zhealblock", "Z-Heal Block", "Raises the user's Special Attack by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zhealingwish", "Z-Healing Wish", "Nothing in addition to its usual effect.");
        zMove(translationBuilder, "zhealorder", "Z-Heal Order", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zhealpulse", "Z-Heal Pulse", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zheartswap", "Z-Heart Swap", "Boosts the user's critical-hit ratio by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zhelpinghand", "Z-Helping Hand", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zholdhands", "Z-Hold Hands", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhoneclaws", "Z-Hone Claws", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhowl", "Z-Howl", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zhypnosis", "Z-Hypnosis", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zimprison", "Z-Imprison", "Raises the user's Special Defense by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zingrain", "Z-Ingrain", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zinstruct", "Z-Instruct", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ziondeluge", "Z-Ion Deluge", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zirondefense", "Z-Iron Defense", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zkinesis", "Z-Kinesis", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zkingsshield", "Z-King's Shield", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zlaserfocus", "Z-Laser Focus", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zleechseed", "Z-Leech Seed", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zleer", "Z-Leer", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zlightscreen", "Z-Light Screen", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zlockon", "Z-Lock-On", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zlovelykiss", "Z-Lovely Kiss", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zluckychant", "Z-Lucky Chant", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zlunardance", "Z-Lunar Dance", "Nothing in addition to its usual effect.");
        zMove(translationBuilder, "zmagiccoat", "Z-Magic Coat", "Raises the user's Special Defense by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zmagicroom", "Z-Magic Room", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmagneticflux", "Z-Magnetic Flux", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmagnetrise", "Z-Magnet Rise", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zmatblock", "Z-Mat Block", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmeanlook", "Z-Mean Look", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmeditate", "Z-Meditate", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmefirst", "Z-Me First", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zmemento", "Z-Memento", "Fully restores switched-in ally's HP in addition to its usual effect.");
        zMove(translationBuilder, "zmetalsound", "Z-Metal Sound", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmetronome", "Z-Metronome", "Performs a random Z-Move attack.");
        zMove(translationBuilder, "zmilkdrink", "Z-Milk Drink", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zmimic", "Z-Mimic", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmindreader", "Z-Mind Reader", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zminimize", "Z-Minimize", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zmiracleeye", "Z-Miracle Eye", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmirrormove", "Z-Mirror Move", "Raises the user's Attack by 2 stages calls a Z-move in addition to its usual effect.");
        zMove(translationBuilder, "zmist", "Z-Mist", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zmistyterrain", "Z-Misty Terrain", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zmoonlight", "Z-Moonlight", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zmorningsun", "Z-Morning Sun", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zmudsport", "Z-Mud Sport", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "znastyplot", "Z-Nasty Plot", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "znaturepower", "Z-Nature Power", "Performs a Z-Move corresponding to the terrain.");
        zMove(translationBuilder, "znightmare", "Z-Nightmare", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "znobleroar", "Z-Noble Roar", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zodorsleuth", "Z-Odor Sleuth", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpainsplit", "Z-Pain Split", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpartingshot", "Z-Parting Shot", "Fully restores switched-in ally's HP in addition to its usual effect.");
        zMove(translationBuilder, "zperishsong", "Z-Perish Song", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zplaynice", "Z-Play Nice", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpoisongas", "Z-Poison Gas", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpoisonpowder", "Z-Poison Powder", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpowder", "Z-Powder", "Raises the user's Special Defense by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zpowersplit", "Z-Power Split", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpowerswap", "Z-Power Swap", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpowertrick", "Z-Power Trick", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zprotect", "Z-Protect", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zpsychicterrain", "Z-Psychic Terrain", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zpsychoshift", "Z-Psycho Shift", "Raises the user's Special Attack by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zpsychup", "Z-Psych Up", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zpurify", "Z-Purify", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zquash", "Z-Quash", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zquickguard", "Z-Quick Guard", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zquiverdance", "Z-Quiver Dance", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zragepowder", "Z-Rage Powder", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zraindance", "Z-Rain Dance", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zrecover", "Z-Recover", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zrecycle", "Z-Recycle", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zreflect", "Z-Reflect", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zreflecttype", "Z-Reflect Type", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zrefresh", "Z-Refresh", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zrest", "Z-Rest", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zroar", "Z-Roar", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zrockpolish", "Z-Rock Polish", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zroleplay", "Z-Role Play", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zroost", "Z-Roost", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zrototiller", "Z-Rototiller", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsafeguard", "Z-Safeguard", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsandattack", "Z-Sand Attack", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zsandstorm", "Z-Sandstorm", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zscaryface", "Z-Scary Face", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zscreech", "Z-Screech", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsharpen", "Z-Sharpen", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zshellsmash", "Z-Shell Smash", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zshiftgear", "Z-Shift Gear", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zshoreup", "Z-Shore Up", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zsimplebeam", "Z-Simple Beam", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsing", "Z-Sing", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsketch", "Z-Sketch", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zskillswap", "Z-Skill Swap", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zslackoff", "Z-Slack Off", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zsleeppowder", "Z-Sleep Powder", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsleeptalk", "Z-Sleep Talk", "Boosts the user's critical-hit ratio by 2 stages calls a Z-move");
        zMove(translationBuilder, "zsmokescreen", "Z-Smokescreen", "Raises the user's the user's evasiveness by 1 in addition to its usual effect.");
        zMove(translationBuilder, "zsnatch", "Z-Snatch", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zsoak", "Z-Soak", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsoftboiled", "Z-Soft-Boiled", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zspeedswap", "Z-Speed Swap", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zspiderweb", "Z-Spider Web", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zspikes", "Z-Spikes", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zspikyshield", "Z-Spiky Shield", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zspite", "Z-Spite", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zsplash", "Z-Splash", "Raises the user's Attack by 3 stages in addition to its usual effect.");
        zMove(translationBuilder, "zspore", "Z-Spore", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zspotlight", "Z-Spotlight", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zstealthrock", "Z-Stealth Rock", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zstickyweb", "Z-Sticky Web", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zstockpile", "Z-Stockpile", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zstrengthsap", "Z-Strength Sap", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zstringshot", "Z-String Shot", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zstunspore", "Z-Stun Spore", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsubstitute", "Z-Substitute", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zsunnyday", "Z-Sunny Day", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsupersonic", "Z-Supersonic", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zswagger", "Z-Swagger", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zswallow", "Z-Swallow", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zsweetkiss", "Z-Sweet Kiss", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zsweetscent", "Z-Sweet Scent", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zswitcheroo", "Z-Switcheroo", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "zswordsdance", "Z-Swords Dance", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "zsynthesis", "Z-Synthesis", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "ztailglow", "Z-Tail Glow", "Resets the user's lowered stats in addition to its usual effect.");
        zMove(translationBuilder, "ztailwhip", "Z-Tail Whip", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztailwind", "Z-Tailwind", "Boosts the user's critical-hit ratio by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "ztaunt", "Z-Taunt", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztearfullook", "Z-Tearful Look", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zteeterdance", "Z-Teeter Dance", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztelekinesis", "Z-Telekinesis", "Raises the user's Special Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zteleport", "Z-Teleport", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "zthunderwave", "Z-Thunder Wave", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztickle", "Z-Tickle", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztopsyturvy", "Z-Topsy-Turvy", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztorment", "Z-Torment", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztoxic", "Z-Toxic", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztoxicspikes", "Z-Toxic Spikes", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztoxicthread", "Z-Toxic Thread", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztransform", "Z-Transform", "Fully restores user's HP in addition to its usual effect.");
        zMove(translationBuilder, "ztrick", "Z-Trick", "Raises the user's Speed by 2 stages in addition to its usual effect.");
        zMove(translationBuilder, "ztrickortreat", "Z-Trick-or-Treat", "Raises the user's Attack Defense Sp in addition to its usual effect. Atk Sp in addition to its usual effect. Def and Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "ztrickroom", "Z-Trick Room", "Raises the user's accuracy by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zvenomdrench", "Z-Venom Drench", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwatersport", "Z-Water Sport", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwhirlwind", "Z-Whirlwind", "Raises the user's Special Defense by 1");
        zMove(translationBuilder, "zwideguard", "Z-Wide Guard", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwillowisp", "Z-Will-O-Wisp", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwish", "Z-Wish", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwithdraw", "Z-Withdraw", "Raises the user's Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zwonderroom", "Z-Water Sport", "Raises the user's Special Defense by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zworkup", "Z-Work Up", "Raises the user's Attack by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zworryseed", "Z-Worry Seed", "Raises the user's Speed by 1 stage in addition to its usual effect.");
        zMove(translationBuilder, "zyawn", "Z-Yawn", "Raises the user's Speed by 1 stage in addition to its usual effect.");

        translationBuilder.add("cobblemon.battle.ultra", "%1$s has performed an ultra burst!");
    }

    private void gmaxEffects (TranslationBuilder translationBuilder) {
        gmax(translationBuilder, "gmaxvinelash", "Vines were placed on %1$s side of the battlefield!", "%1$s was damaged by vines!", "The vines disappeared.");
        gmax(translationBuilder, "gmaxvolcalith", "%1$s was burned!", "%1$s was damaged by it's burn!", "The burn went away.");
        gmax(translationBuilder, "gmaxwildfire", "%1$s flames are going wild!", "%1$s was damaged by the fire!", "The flames went away.");
        gmax(translationBuilder, "gmaxsteelsurge", "Spikes were scattered on the ground all around %1$s team!", null, "The spikes were cleared.");
        gmax(translationBuilder, "gmaxcannonade", "%1$s was trapped in a vortex!", "%1$s is hurt by the vortex!", "The vortex vanished");
        gmax(translationBuilder, "gmaxcentiferno", null, "%1$s is hurt by fiery vortex!", "The fiery vortex vanished.");

        translationBuilder.add("cobblemon.battle.activate.gmaxcentiferno", "%1$s became trapped in the fiery vortex!");
        translationBuilder.add("cobblemon.battle.start.gmaxchistrike", "%1$s concentrated intensely!");
    }

    private void formChanges (TranslationBuilder translationBuilder) {
        translationBuilder.add("gui.title.gimme-that-gimmick.form", "Select A Form");
        translationBuilder.add("message.overlay.gimme-that-gimmick.form", "Your %1$s has transformed into %2$s forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.fusion", "Your %1$s has fused with your %2$s.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.dna.no_dragon", "You need Kyurem in your party to use this item.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.no_sheet", "This player is not holding a team sheet.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.mirror.incarnate", "Your %1$s is now in Incarnate forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.mirror.therian", "Your %1$s is now in Therian forme.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.gracidea.sky", "Your %1$s is now in Sky forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.gracidea.land", "Your %1$s is now in Land forme.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.prison.unbound", "Your %1$s has been released into its Unbound forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.prison.confined", "Your %1$s has been restrained into its Confined forme.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.oricorio.pa'u", "Your %1$s has been transformed into its Pa'u forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.oricorio.sensu", "Your %1$s has been transformed into its Sensu forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.oricorio.baile", "Your %1$s has been transformed into its Baile forme.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.oricorio.pom_pom", "Your %1$s has been transformed into its Pom Pom forme.");

        translationBuilder.add("message.overlay.gimme-that-gimmick.zygarde.50", "Your %1$s has gained cells.");
        translationBuilder.add("message.overlay.gimme-that-gimmick.zygarde.10", "Your %1$s has lost cells.");

        addItemWithTooltip(translationBuilder, GTGItems.ADAMANT_CRYSTAL, "Adamant Crystal", "Boosts Steel- and Dragon-type attacks of Dialga");
        addItemWithTooltip(translationBuilder, GTGItems.LUSTROUS_GLOBE, "Lustrous Globe", "Boosts Water- and Dragon-type attacks of Palkia");
        addItemWithTooltip(translationBuilder, GTGItems.GRISEOUS_CORE, "Griseous Core", "Boosts Ghost- and Dragon-type attacks of Giratina");

        addItemWithTooltip(translationBuilder, GTGItems.BLUE_ORB, "Blue Orb", "Grants Primal Reversion when held by Kyogre");
        addItemWithTooltip(translationBuilder, GTGItems.RED_ORB, "Red Orb", "Grants Primal Reversion when held by Groudon");

        addItemWithTooltip(translationBuilder, GTGItems.RUSTED_SWORD, "Rusted Sword", "Turns Zacian's Iron Head into Behemoth Blade");
        addItemWithTooltip(translationBuilder, GTGItems.RUSTED_SHIELD, "Rusted Shield", "Turns Zamazenta's Iron Head into Behemoth Bash");

        addItemWithTooltip(translationBuilder, GTGItems.DRACO_PLATE, "Draco Plate", "Boosts the holder's Dragon-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.DREAD_PLATE, "Dread Plate", "Boosts the holder's Dark-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.EARTH_PLATE, "Earth Plate", "Boosts the holder's Ground-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.FIST_PLATE, "Fist Plate", "Boosts the holder's Fighting-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.FLAME_PLATE, "Flame Plate", "Boosts the holder's Fire-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.ICICLE_PLATE, "Icicle Plate", "Boosts the holder's Icicle-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.INSECT_PLATE, "Insect Plate", "Boosts the holder's Insect-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.IRON_PLATE, "Iron Plate", "Boosts the holder's Steel-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.MEADOW_PLATE, "Meadow Plate", "Boosts the holder's Grass-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.MIND_PLATE, "Mind Plate", "Boosts the holder's Psychic-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.PIXIE_PLATE, "Pixie Plate", "Boosts the holder's Fairy-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.SKY_PLATE, "Sky Plate", "Boosts the holder's Flying-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.SPLASH_PLATE, "Splash Plate", "Boosts the holder's Water-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.SPOOKY_PLATE, "Spooky Plate", "Boosts the holder's Ghost-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.STONE_PLATE, "Stone Plate", "Boosts the holder's Rock-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.TOXIC_PLATE, "Toxic Plate", "Boosts the holder's Poison-type attacks by 20%");
        addItemWithTooltip(translationBuilder, GTGItems.ZAP_PLATE, "Zap Plate", "Boosts the holder's Electric-type attacks by 20%");

        addItemWithTooltip(translationBuilder, GTGItems.BUG_MEMORY, "Bug Memory", "Grants Silvally the Bug-type");
        addItemWithTooltip(translationBuilder, GTGItems.DARK_MEMORY, "Dark Memory", "Grants Silvally the Dark-type");
        addItemWithTooltip(translationBuilder, GTGItems.DRAGON_MEMORY, "Dragon Memory", "Grants Silvally the Dragon-type");
        addItemWithTooltip(translationBuilder, GTGItems.ELECTRIC_MEMORY, "Electric Memory", "Grants Silvally the Electric-type");
        addItemWithTooltip(translationBuilder, GTGItems.FAIRY_MEMORY, "Fairy Memory", "Grants Silvally the Fairy-type");
        addItemWithTooltip(translationBuilder, GTGItems.FIGHTING_MEMORY, "Fighting Memory", "Grants Silvally the Fighting-type");
        addItemWithTooltip(translationBuilder, GTGItems.FIRE_MEMORY, "Fire Memory", "Grants Silvally the Fire-type");
        addItemWithTooltip(translationBuilder, GTGItems.FLYING_MEMORY, "Flying Memory", "Grants Silvally the Flying-type");
        addItemWithTooltip(translationBuilder, GTGItems.GHOST_MEMORY, "Ghost Memory", "Grants Silvally the Ghost-type");
        addItemWithTooltip(translationBuilder, GTGItems.GRASS_MEMORY, "Grass Memory", "Grants Silvally the Grass-type");
        addItemWithTooltip(translationBuilder, GTGItems.GROUND_MEMORY, "Ground Memory", "Grants Silvally the Ground-type");
        addItemWithTooltip(translationBuilder, GTGItems.ICE_MEMORY, "Ice Memory", "Grants Silvally the Ice-type");
        addItemWithTooltip(translationBuilder, GTGItems.POISON_MEMORY, "Poison Memory", "Grants Silvally the Poison-type");
        addItemWithTooltip(translationBuilder, GTGItems.PSYCHIC_MEMORY, "Psychic Memory", "Grants Silvally the Psychic-type");
        addItemWithTooltip(translationBuilder, GTGItems.ROCK_MEMORY, "Rock Memory", "Grants Silvally the Rock-type");
        addItemWithTooltip(translationBuilder, GTGItems.STEEL_MEMORY, "Steel Memory", "Grants Silvally the Steel-type");
        addItemWithTooltip(translationBuilder, GTGItems.WATER_MEMORY, "Water Memory", "Grants Silvally the Water-type");

        addItemWithTooltip(translationBuilder, GTGItems.BURN_DRIVE, "Burn Drive", "Changes Genesect's Techno Blast to Fire-type");
        addItemWithTooltip(translationBuilder, GTGItems.CHILL_DRIVE, "Chill Drive", "Changes Genesect's Techno Blast to Ice-type");
        addItemWithTooltip(translationBuilder, GTGItems.DOUSE_DRIVE, "Douse Drive", "Changes Genesect's Techno Blast to Water-type");
        addItemWithTooltip(translationBuilder, GTGItems.SHOCK_DRIVE, "Shock Drive", "Changes Genesect's Techno Blast to Electric-type");

        addItemWithTooltip(translationBuilder, GTGItems.WELLSPRING_MASK, "Wellspring Mask", "Grants Ogerpon its Wellspring form");
        addItemWithTooltip(translationBuilder, GTGItems.HEARTHFLAME_MASK, "Hearthflame Mask", "Grants Ogerpon its Hearthflame form");
        addItemWithTooltip(translationBuilder, GTGItems.CORNERSTONE_MASK, "Cornerstone Mask", "Grants Ogerpon its Cornerstone form");

        addItemWithTooltip(translationBuilder, GTGItems.REVEAL_GLASS, "Reveal Glass", "Toggles the Therian forms of the Forces of Nature");
        addItemWithTooltip(translationBuilder, GTGItems.DNA_SPLICERS, "DNA Splicers", "Fuses and unfuses Kyurem with Reshiram or Zekrom");
        addItemWithTooltip(translationBuilder, GTGItems.GRACIDEA_FLOWER, "Gracidea Flower", "Toggles the forme of Shaymin");
        addItemWithTooltip(translationBuilder, GTGItems.PRISON_BOTTLE, "Prison Bottle", "Toggles the forme of Hoopa");
        addItemWithTooltip(translationBuilder, GTGItems.N_LUNARIZER, "N Lunarizer", "Fuses Necrozma with Lunala");
        addItemWithTooltip(translationBuilder, GTGItems.N_SOLARIZER, "N Solarizer", "Fuses Necrozma with Solgaleo");
        addItemWithTooltip(translationBuilder, GTGItems.METEORITE, "Meteorite", "A meteorite that enables Deoxys to change its form");
        addItemWithTooltip(translationBuilder, GTGItems.REIGNS_OF_UNITY, "Reigns of Unity", "Used to unite Calyrex with its steed");
        addItemWithTooltip(translationBuilder, GTGItems.ROTOM_CATALOG, "Rotom Catalog", "A catalog of appliances for Rotom to possess");
        addItemWithTooltip(translationBuilder, GTGItems.ZYGARDE_CUBE, "Zygarde Cube", "Changes the amount of cells Zygarde has");

        addItemWithTooltip(translationBuilder, GTGItems.PINK_NECTAR, "Pink Nectar", "Changes Oricorio to its Pa'u Style");
        addItemWithTooltip(translationBuilder, GTGItems.YELLOW_NECTAR, "Yellow Nectar", "Changes Oricorio to its Pom-Pom Style");
        addItemWithTooltip(translationBuilder, GTGItems.RED_NECTAR, "Red Nectar", "Changes Oricorio to its Baile Style");
        addItemWithTooltip(translationBuilder, GTGItems.PURPLE_NECTAR, "Purple Nectar", "Changes Oricorio to its Sensu Style");
    }

    private void battleMessages (TranslationBuilder translationBuilder) {
        translationBuilder.add("cobblemon.battle.formechange.default.temporary.end", "%1$s changed to its base form.");
        translationBuilder.add("cobblemon.battle.activate.zerotohero", "%1$s underwent a heroic transformation!");
        translationBuilder.add("cobblemon.battle.activate.terashift", "A glow surrounds %1$s!");
        translationBuilder.add("cobblemon.battle.activate.terashell", "%1$s made its shell gleam! It's distorting type matchups!");
    }
    
    public static String[] zCrystalTooltip (String type) {
        return new String[]{"Upgrades a " + type + "-type move to a Z-Move"};
    }

    public static String[] zCrystalTooltip (String pokemon, String move) {
        return new String[]{"Upgrades " + pokemon + "'s " + move + " to a Z-Move"};
    }

    public static void zMove (TranslationBuilder translationBuilder, String moveId, String name, String description) {
        String moveTranslationKey = "cobblemon.move." + moveId;
        translationBuilder.add(moveTranslationKey, name);
        translationBuilder.add(moveTranslationKey + ".desc", description);
    }

    public static void gmax (TranslationBuilder translationBuilder, String moveId, String sideStart, String damage, String end) {
        if (sideStart != null) translationBuilder.add("cobblemon.battle.sidestart." + moveId, sideStart);
        if (damage != null) translationBuilder.add("cobblemon.battle.damage." + moveId, damage);
        if (end != null) translationBuilder.add("cobblemon.battle.end." + moveId, end);
    }
}
