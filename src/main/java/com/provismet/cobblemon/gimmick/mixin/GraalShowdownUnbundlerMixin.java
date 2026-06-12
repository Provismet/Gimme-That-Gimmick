package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownUnbundler;
import com.provismet.cobblemon.gimmick.util.ShowdownPatcher;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(GraalShowdownUnbundler.class)
public abstract class GraalShowdownUnbundlerMixin {
    @Inject(method = "attemptUnbundle", at = @At("TAIL"), remap = false)
    private void replaceScripts (CallbackInfo info) {
        ShowdownPatcher.patch();
    }
}
