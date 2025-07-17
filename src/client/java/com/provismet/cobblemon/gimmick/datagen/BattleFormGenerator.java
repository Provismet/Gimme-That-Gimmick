package com.provismet.cobblemon.gimmick.datagen;

import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm;
import com.provismet.cobblemon.gimmick.api.data.registry.form.BattleForm.PokemonTransformation;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.datagen.BattleFormProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class BattleFormGenerator extends BattleFormProvider {
    public BattleFormGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, BattleForm> consumer) {
        this.createBasic(consumer, "aegislash", "stance_forme", "shield", "blade");
        consumer.accept(
            MiscUtilsKt.cobblemonResource("castform"),
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single("forecast_form", "normal")),
                Map.of(
                    "sunny", PokemonTransformation.of(PokemonFeatures.single("forecast_form", "sunny")),
                    "rainy", PokemonTransformation.of(PokemonFeatures.single("forecast_form", "rainy")),
                    "snowy", PokemonTransformation.of(PokemonFeatures.single("forecast_form", "snowy"))
                )
            )
        );
        consumer.accept(
            MiscUtilsKt.cobblemonResource("cramorant"),
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single("missile_form", "none")),
                Map.of(
                    "gulping", PokemonTransformation.of(PokemonFeatures.single("missile_form", "gulping")),
                    "gorging", PokemonTransformation.of(PokemonFeatures.single("missile_form", "gorging"))
                )
            )
        );
        this.createBasic(consumer, "cherrim", "blossom_form", "overcast", "sunshine");
        consumer.accept(
            MiscUtilsKt.cobblemonResource("darmanitan"),
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single("blazing_mode", "standard")),
                Map.of(
                    "zen", PokemonTransformation.of(PokemonFeatures.single("blazing_mode", "zen")),
                    "", PokemonTransformation.of(PokemonFeatures.single("blazing_mode", "standard")) // Happens if HP goes back above 50%.
                )
            )
        );
        consumer.accept(
            MiscUtilsKt.cobblemonResource("eiscue"),
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single("penguin_head", "ice_face")),
                Map.of("noice", PokemonTransformation.of(PokemonFeatures.single("penguin_head", "noice_face")))
            )
        );
        this.createBasic(consumer, "meloetta", "song_forme", "aria", "pirouette");
        this.createBasic(consumer, "mimikyu", "disguise_form", "disguised", "busted");
        this.createBasic(consumer, "minior", "meteor_shield", "shield", "meteor");
        this.createBasic(consumer, "morpeko", "hunger_mode", "full_belly", "hangry");
        this.createBasic(consumer, "palafin", "dolphin_form", "zero", "hero");
        this.createBasic(consumer, "wishiwashi", "schooling_form", "solo", "school");
        this.createBasic(consumer, "xerneas", "life_mode", "neutral", "active");
    }

    private void createBasic (BiConsumer<Identifier, BattleForm> consumer, String speciesId, String featureName, String defaultValue, String formValue) {
        consumer.accept(
            MiscUtilsKt.cobblemonResource(speciesId),
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single(featureName, defaultValue)),
                Map.of(formValue, PokemonTransformation.of(PokemonFeatures.single(featureName, formValue)))
            )
        );
    }
}
