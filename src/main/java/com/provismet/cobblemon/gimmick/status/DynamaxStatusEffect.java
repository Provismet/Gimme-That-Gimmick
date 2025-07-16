package com.provismet.cobblemon.gimmick.status;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import eu.pb4.polymer.core.api.other.PolymerStatusEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class DynamaxStatusEffect extends StatusEffect implements PolymerStatusEffect {
    public DynamaxStatusEffect () {
        super(StatusEffectCategory.BENEFICIAL, 0x7C2123);
        this.addAttributeModifier(
            EntityAttributes.GENERIC_SCALE,
            GimmeThatGimmickMain.identifier("dynamax_size"),
            0.1,
            EntityAttributeModifier.Operation.ADD_VALUE
        );
    }
}
