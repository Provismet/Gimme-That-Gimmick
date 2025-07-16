package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.status.DynamaxStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class GTGStatusEffects {
    public static final RegistryEntry<StatusEffect> DYNAMAX = register("dynamax", new DynamaxStatusEffect());

    private static RegistryEntry<StatusEffect> register (String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, GimmeThatGimmickMain.identifier(name), statusEffect);
    }

    public static void init () {}
}
