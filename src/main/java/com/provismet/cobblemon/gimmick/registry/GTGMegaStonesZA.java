package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.PokemonRequirements;
import com.provismet.cobblemon.gimmick.api.data.component.MegaEvolution;
import com.provismet.cobblemon.gimmick.item.mega.MegaStoneItem;

import static com.provismet.cobblemon.gimmick.registry.GTGItems.registerMegaStone;

public class GTGMegaStonesZA {
    public static final MegaStoneItem ABSOLITE_Z = registerMegaStone("absolitez", "absol", "mega_z");
    public static final MegaStoneItem BARBARACITE = registerMegaStone("barbaracite", "barbaracle");
    public static final MegaStoneItem BAXCALIBRITE = registerMegaStone("baxcalibrite", "baxcalibur");
    public static final MegaStoneItem CHANDELURITE = registerMegaStone("chandelurite", "chandelure");
    public static final MegaStoneItem CHESNAUGHTITE = registerMegaStone("chesnaughtite", "chesnaught");
    public static final MegaStoneItem CHIMECHITE = registerMegaStone("chimechite", "chimecho");
    public static final MegaStoneItem CLEFABLITE = registerMegaStone("clefablite", "clefable");
    public static final MegaStoneItem CRABOMINITE = registerMegaStone("crabominite", "crabominable");
    public static final MegaStoneItem DARKRANITE = registerMegaStone("darkranite", "darkrai");
    public static final MegaStoneItem DELPHOXITE = registerMegaStone("delphoxite", "delphox");
    public static final MegaStoneItem DRAGALGITE = registerMegaStone("dragalgite", "dragalge");
    public static final MegaStoneItem DRAGONINITE = registerMegaStone("dragoninite", "dragonite");
    public static final MegaStoneItem DRAMPANITE = registerMegaStone("drampanite", "drampa");
    public static final MegaStoneItem EELEKTROSSITE = registerMegaStone("eelektrossite", "eelektross");
    public static final MegaStoneItem EMBOARITE = registerMegaStone("emboarite", "emboar");
    public static final MegaStoneItem EXCADRITE = registerMegaStone("excadrite", "excadrill");
    public static final MegaStoneItem FALINKSITE = registerMegaStone("falinksite", "falinks");
    public static final MegaStoneItem FERALIGITE = registerMegaStone("feraligite", "feraligatr");
    public static final MegaStoneItem FLOETTITE = registerMegaStone("floettite", MegaEvolution.create("floette", "eternal"));
    public static final MegaStoneItem FROSLASSITE = registerMegaStone("froslassite", "froslass");
    public static final MegaStoneItem GARCHOMPITE_Z = registerMegaStone("garchompitez", "garchomp", "mega_z");
    public static final MegaStoneItem GLIMMORANITE = registerMegaStone("glimmoranite", "glimmora");
    public static final MegaStoneItem GOLISOPITE = registerMegaStone("golisopite", "golisopod");
    public static final MegaStoneItem GOLURKITE = registerMegaStone("golurkite", "golurk");
    public static final MegaStoneItem GRENINJITE = registerMegaStone("greninjite", "greninja");
    public static final MegaStoneItem HAWLUCHANITE = registerMegaStone("hawluchanite", "hawlucha");
    public static final MegaStoneItem HEATRANITE = registerMegaStone("heatranite", "heatran");
    public static final MegaStoneItem LUCARIONITE_Z = registerMegaStone("lucarionitez", "lucario", "mega_z");
    public static final MegaStoneItem MAGEARNITE = registerMegaStone("magearnite", "magearna");
    public static final MegaStoneItem MALAMARITE = registerMegaStone("malamarite", "malamar");
    public static final MegaStoneItem MEGANIUMITE = registerMegaStone("meganiumite", "meganium");
    public static final MegaStoneItem MEOWSTICITE = registerMegaStone("meowsticite", new MegaEvolution(PokemonRequirements.species("meowstic"), PokemonFeatures.single("mega_evolution", "mega"), PokemonFeatures.single("mega_evolution", "none")));
    public static final MegaStoneItem PYROARITE = registerMegaStone("pyroarite", "pyroar");
    public static final MegaStoneItem RAICHUNITE_X = registerMegaStone("raichunitex", "raichu", "mega_x");
    public static final MegaStoneItem RAICHUNITE_Y = registerMegaStone("raichunitey", "raichu", "mega_y");
    public static final MegaStoneItem SCOLIPITE = registerMegaStone("scolipite", "scolipede");
    public static final MegaStoneItem SCOVILLAINITE = registerMegaStone("scovillainite", "scovillain");
    public static final MegaStoneItem SCRAFTINITE = registerMegaStone("scraftinite", "scrafty");
    public static final MegaStoneItem SKARMORITE = registerMegaStone("skarmorite", "skarmory");
    public static final MegaStoneItem STARAPTITE = registerMegaStone("staraptite", "staraptor");
    public static final MegaStoneItem STARMINITE = registerMegaStone("starminite", "starmie");
    public static final MegaStoneItem TATSUGIRINITE = registerMegaStone("tatsugirinite", new MegaEvolution(PokemonRequirements.species("tatsugiri"), PokemonFeatures.single("mega_evolution", "mega"), PokemonFeatures.single("mega_evolution", "none")));
    public static final MegaStoneItem VICTREEBELITE = registerMegaStone("victreebelite", "victreebel");
    public static final MegaStoneItem ZERAORITE = registerMegaStone("zeraorite", "zeraora");
    public static final MegaStoneItem ZYGARDITE = registerMegaStone("zygardite", "zygarde");

    public static void init () {}
}
