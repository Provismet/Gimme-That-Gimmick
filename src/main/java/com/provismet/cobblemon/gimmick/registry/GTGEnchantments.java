package com.provismet.cobblemon.gimmick.registry;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.util.tag.GTGEnchantmentTags;
import com.provismet.cobblemon.gimmick.util.tag.GTGItemTags;
import com.provismet.lilylib.container.EnchantmentContainer;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registerable;
import net.minecraft.util.Unit;

import java.util.function.Consumer;

public abstract class GTGEnchantments {
    public static final EnchantmentContainer KEY_STONE = createGimmick("key_stone", GTGEnchantmentComponents.KEY_STONE);
    public static final EnchantmentContainer Z_RING = createGimmick("z-power", GTGEnchantmentComponents.Z_RING);
    public static final EnchantmentContainer DYNAMAX_BAND = createGimmick("dynamax", GTGEnchantmentComponents.DYNAMAX_BAND);
    public static final EnchantmentContainer TERA_ORB = createGimmick("terastal", GTGEnchantmentComponents.TERA_ORB);

    private static EnchantmentContainer createGimmick(String name, ComponentType<Unit> gimmickComponent) {
        return new EnchantmentContainer(
                GimmeThatGimmickMain.identifier(name),
                (itemLookup, enchantmentLookup, damageLookup, blockLookup) -> Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(GTGItemTags.GIMMICK_ENCHANTABLE),
                                1,
                                1,
                                Enchantment.constantCost(30),
                                Enchantment.constantCost(30),
                                7,
                                AttributeModifierSlot.ANY
                        )
                ).addEffect(
                        gimmickComponent
                ).exclusiveSet(
                        enchantmentLookup.getOrThrow(GTGEnchantmentTags.KEY_ITEM)
                )
        );
    }

    public static void bootstrap(Registerable<Enchantment> registerable) {
        Consumer<EnchantmentContainer> register = container -> registerable.register(container.getKey(), container.getBuilder(registerable).build(container.getKey().getValue()));

        register.accept(KEY_STONE);
        register.accept(Z_RING);
        register.accept(DYNAMAX_BAND);
        register.accept(TERA_ORB);
    }
}
