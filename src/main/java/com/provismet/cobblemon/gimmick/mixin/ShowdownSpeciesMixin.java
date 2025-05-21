package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.abilities.PotentialAbility;
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Species;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Map;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(PokemonSpecies.ShowdownSpecies.class)
public class ShowdownSpeciesMixin {
    @Shadow(remap = false) @Final @Mutable
    private Map<String, String> abilities;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    private void populateAbilities (Species species, FormData formData, CallbackInfo info) {
        if(formData == null) return;

        Iterator<PotentialAbility> abilityIterator = formData.getAbilities().iterator();
        abilities = Map.of(
            "0", abilityIterator.hasNext() ? abilityIterator.next().getTemplate().getName() : "No Ability"
        );
    }
}
