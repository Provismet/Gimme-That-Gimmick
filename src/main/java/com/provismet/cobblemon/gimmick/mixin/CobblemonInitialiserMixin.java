package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.Cobblemon;
import com.provismet.cobblemon.gimmick.handlers.CobblemonEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Cobblemon.class, remap = false)
public abstract class CobblemonInitialiserMixin {
    @Inject(method = "initialize", at = @At("HEAD"))
    private void registerEarlyEvents (CallbackInfo ci) {
        // Mod loading order is a thing that exists.
        CobblemonEventHandler.registerEarly();
    }
}
