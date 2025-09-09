package com.provismet.cobblemon.gimmick.features;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.pokemon.feature.GlobalSpeciesFeatures;
import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeature;
import com.cobblemon.mod.common.api.pokemon.feature.IntSpeciesFeatureProvider;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.config.Options;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class DynamaxLevelFeature {
    private static final IntSpeciesFeatureProvider DynamaxLevel;
    private static final String KEY = "dynamax_level";

    static {
        DynamaxLevel = new IntSpeciesFeatureProvider();
        DynamaxLevel.setDefault(0);
        DynamaxLevel.setKeys(List.of(KEY));
        DynamaxLevel.setMin(0);
        DynamaxLevel.setMax(Cobblemon.config.getMaxDynamaxLevel());
        DynamaxLevel.setVisible(Options.shouldShowDynamaxLevel());

        if (Options.shouldShowDynamaxLevel()) {
            IntSpeciesFeatureProvider.DisplayData display = new IntSpeciesFeatureProvider.DisplayData();
            display.setColour(new Vec3d(198, 0, 0));
            display.setName("gimme-that-gimmick.ui.dynamax.level");
            display.setUnderlay(MiscUtilsKt.cobblemonResource("textures/gui/summary/summary_stats_other_bar.png"));
            display.setOverlay(GimmeThatGimmickMain.identifier("textures/gui/summary/dynamax_level.png"));
            DynamaxLevel.setDisplay(display);
        }
    }

    public static void register () {
        GlobalSpeciesFeatures.INSTANCE.register(KEY, DynamaxLevel);
    }

    public static void updateForPokemon (Pokemon pokemon) {
        IntSpeciesFeature feature = DynamaxLevel.get(pokemon);
        if (feature == null) return;

        feature.setValue(pokemon.getDmaxLevel());
        feature.apply(pokemon);
        pokemon.markFeatureDirty(feature);
    }
}
