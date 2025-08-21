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
import java.util.stream.Collectors;

public class BattleFormGenerator extends BattleFormProvider {
    public BattleFormGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, BattleForm> consumer) {
        this.createBasic(consumer, "aegislash", "stance_forme", "shield", "blade");
        this.createComplex(consumer,
            "castform",
            PokemonFeatures.single("forecast_form", "normal"),
            Map.of(
                "sunny", PokemonFeatures.single("forecast_form", "sunny"),
                "rainy", PokemonFeatures.single("forecast_form", "rainy"),
                "snowy", PokemonFeatures.single("forecast_form", "snowy")
            )
        );
        this.createComplex(consumer,
            "cramorant",
            PokemonFeatures.single("missile_form", "none"),
            Map.of(
                "gulping", PokemonFeatures.single("missile_form", "gulping"),
                "gorging", PokemonFeatures.single("missile_form", "gorging")
            )
        );
        this.createBasic(consumer, "cherrim", "blossom_form", "overcast", "sunshine");
        this.createComplex(consumer,
            "darmanitan",
            PokemonFeatures.single("blazing_mode", "standard"),
            Map.of(
                "zen", PokemonFeatures.single("blazing_mode", "zen"),
                "", PokemonFeatures.single("blazing_mode", "standard") // Happens if HP goes back above 50%.
            )
        );
        this.createComplex(consumer,
            "eiscue",
            PokemonFeatures.single("penguin_head", "ice_face"),
            Map.of("noice", PokemonFeatures.single("penguin_head", "noice_face"))
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
        Identifier pokemonId = MiscUtilsKt.cobblemonResource(speciesId);
        consumer.accept(
            pokemonId,
            new BattleForm(
                PokemonTransformation.of(PokemonFeatures.single(featureName, defaultValue), pokemonId.withSuffixedPath("_base")),
                Map.of(formValue, PokemonTransformation.of(PokemonFeatures.single(featureName, formValue), pokemonId.withSuffixedPath("_" + formValue)))
            )
        );
    }

    private void createComplex (BiConsumer<Identifier, BattleForm> consumer, String speciesId, PokemonFeatures defaultForm, Map<String, PokemonFeatures> alternateForms) {
        Identifier pokemonId = MiscUtilsKt.cobblemonResource(speciesId);
        consumer.accept(
            pokemonId,
            new BattleForm(
                PokemonTransformation.of(defaultForm, pokemonId.withSuffixedPath("_base")),
                alternateForms.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, val -> PokemonTransformation.of(val.getValue(), pokemonId.withSuffixedPath("_" + val.getKey()))))
            )
        );
    }
}
