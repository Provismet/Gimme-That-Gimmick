package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.features.DynamaxLevelFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Pokemon.class, remap = false)
public abstract class PokemonMixin {
    @Inject(method = "setDmaxLevel", at = @At("TAIL"))
    private void applyFeature (int value, CallbackInfo ci) {
        DynamaxLevelFeature.updateForPokemon((Pokemon)(Object)this);
    }
}
