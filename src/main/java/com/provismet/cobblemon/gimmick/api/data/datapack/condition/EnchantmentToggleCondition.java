package com.provismet.cobblemon.gimmick.api.data.datapack.condition;

import com.mojang.serialization.MapCodec;
import com.provismet.cobblemon.gimmick.config.Options;
import com.provismet.cobblemon.gimmick.registry.GTGResourceConditions;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

public record EnchantmentToggleCondition () implements ResourceCondition {
    public static final MapCodec<EnchantmentToggleCondition> CODEC = MapCodec.unit(EnchantmentToggleCondition::new);

    @Override
    public ResourceConditionType<?> getType () {
        return GTGResourceConditions.GIMMICK_ENCHANTMENTS_ENABLED;
    }

    @Override
    public boolean test (RegistryWrapper.@Nullable WrapperLookup wrapperLookup) {
        return Options.includeGimmickEnchantments();
    }
}
