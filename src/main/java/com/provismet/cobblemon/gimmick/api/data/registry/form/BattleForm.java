package com.provismet.cobblemon.gimmick.api.data.registry.form;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.dispatch.UntilDispatch;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.registry.GTGDynamicRegistryKeys;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public record BattleForm (PokemonTransformation defaultForm, Map<String, PokemonTransformation> forms) {
    public static final Codec<BattleForm> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PokemonTransformation.CODEC.fieldOf("default").forGetter(BattleForm::defaultForm),
        Codec.unboundedMap(Codec.STRING, PokemonTransformation.CODEC).fieldOf("forms").forGetter(BattleForm::forms)
    ).apply(instance, BattleForm::new));

    public static RegistryKey<BattleForm> key (Identifier speciesId) {
        return RegistryKey.of(GTGDynamicRegistryKeys.BATTLE_FORM, speciesId);
    }

    public static RegistryKey<BattleForm> key (Pokemon pokemon) {
        return key(pokemon.getSpecies().getResourceIdentifier());
    }

    public void applyForm (PokemonEntity pokemon, @Nullable PokemonEntity opponent, PokemonBattle battle, String formName) {
        PokemonTransformation toApply;

        if (this.forms.containsKey(formName)) {
            toApply = this.forms.get(formName);
        }
        else if (formName.equalsIgnoreCase(pokemon.getPokemon().getSpecies().showdownId())) {
            toApply = this.defaultForm;
        }
        else {
            return;
        }

        battle.dispatchToFront(dispatcher -> {
            toApply.features.apply(pokemon.getPokemon());
            return new UntilDispatch(() -> true);
        });
        toApply.effectsData.ifPresent(effectId -> EffectsData.run(pokemon, opponent, battle, effectId));
    }

    public record PokemonTransformation (PokemonFeatures features, Optional<Identifier> effectsData) {
        public static final Codec<PokemonTransformation> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            PokemonFeatures.CODEC.fieldOf("features").forGetter(PokemonTransformation::features),
            Identifier.CODEC.optionalFieldOf("effect").forGetter(PokemonTransformation::effectsData)
        ).apply(instance, PokemonTransformation::new));

        public static PokemonTransformation of (PokemonFeatures features) {
            return new PokemonTransformation(features, Optional.empty());
        }
    }
}
