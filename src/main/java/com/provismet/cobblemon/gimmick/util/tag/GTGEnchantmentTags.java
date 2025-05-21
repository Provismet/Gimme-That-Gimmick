package com.provismet.cobblemon.gimmick.util.tag;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public abstract class GTGEnchantmentTags {
    public static final TagKey<Enchantment> KEY_ITEM = GTGEnchantmentTags.of("key_item");

    private static TagKey<Enchantment> of (String name) {
        return TagKey.of(RegistryKeys.ENCHANTMENT, GimmeThatGimmickMain.identifier(name));
    }
}
