package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.datapack.condition.EnchantmentToggleCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

public interface GTGResourceConditions {
    ResourceConditionType<EnchantmentToggleCondition> GIMMICK_ENCHANTMENTS_ENABLED = ResourceConditionType.create(GimmeThatGimmickMain.identifier("gimmick_enchantments_enabled"), EnchantmentToggleCondition.CODEC);

    static void register() {
        ResourceConditions.register(GIMMICK_ENCHANTMENTS_ENABLED);
    }
}
