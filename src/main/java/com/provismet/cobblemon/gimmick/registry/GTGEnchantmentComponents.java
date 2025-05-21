package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public abstract class GTGEnchantmentComponents {
    public static final ComponentType<Unit> KEY_STONE = register("key_stone", builder -> builder.codec(Unit.CODEC));
    public static final ComponentType<Unit> Z_RING = register("z_ring", builder -> builder.codec(Unit.CODEC));
    public static final ComponentType<Unit> DYNAMAX_BAND = register("dynamax_band", builder -> builder.codec(Unit.CODEC));
    public static final ComponentType<Unit> TERA_ORB = register("tera_orb", builder -> builder.codec(Unit.CODEC));

    private static <T> ComponentType<T> register (String path, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        ComponentType<T> type = Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, GimmeThatGimmickMain.identifier(path), builderOperator.apply(ComponentType.builder()).build());
        PolymerComponent.registerEnchantmentEffectComponent(type);
        return type;
    }

    public static void init () {}
}
