package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.net.messages.client.PokemonUpdatePacket;
import com.cobblemon.mod.common.net.messages.client.pokemon.update.DmaxLevelUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.features.DynamaxLevelHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Pokemon.class, remap = false)
public abstract class PokemonMixin {
    @Inject(method = "_dmaxLevel$lambda$128", at = @At("HEAD"))
    private static void applyFeature (Pokemon this$0, int it, CallbackInfoReturnable<PokemonUpdatePacket<DmaxLevelUpdatePacket>> cir) {
        DynamaxLevelHandler.update(this$0);
    }
}
