package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.api.data.DataRegistry;
import com.cobblemon.mod.common.api.scripting.CobblemonScripts;
import com.cobblemon.mod.common.data.CobblemonDataProvider;
import com.provismet.cobblemon.gimmick.showdown.Conditions;
import com.provismet.cobblemon.gimmick.showdown.HeldItems;
import com.provismet.cobblemon.gimmick.showdown.Scripts;
import com.provismet.cobblemon.gimmick.showdown.TypeCharts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CobblemonDataProvider.class, remap = false)
public class CobblemonDataProviderMixin {
    @Redirect(
            method = "registerDefaults",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/cobblemon/mod/common/data/CobblemonDataProvider;register(Lcom/cobblemon/mod/common/api/data/DataRegistry;Z)Lcom/cobblemon/mod/common/api/data/DataRegistry;"
            )
    )
    private <T extends DataRegistry> T injectBeforeSpeciesRegister(CobblemonDataProvider instance, T registry, boolean reloadable) {
        if (registry instanceof CobblemonScripts && registry == CobblemonScripts.INSTANCE) {
            instance.register(Conditions.INSTANCE, true);
            instance.register(HeldItems.INSTANCE, true);
            instance.register(TypeCharts.INSTANCE, true);
            instance.register(Scripts.INSTANCE, true);
        }
        return instance.register(registry, reloadable);
    }
}