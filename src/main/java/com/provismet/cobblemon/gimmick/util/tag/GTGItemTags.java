package com.provismet.cobblemon.gimmick.util.tag;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public abstract class GTGItemTags {
    public static final TagKey<Item> MEGA_STONES = GTGItemTags.of("mega_stones");
    public static final TagKey<Item> MEGA_STONES_X = GTGItemTags.of("mega_stones_x");
    public static final TagKey<Item> MEGA_STONES_Y = GTGItemTags.of("mega_stones_y");

    public static final TagKey<Item> Z_CRYSTALS = GTGItemTags.of("z-crystal");
    public static final TagKey<Item> Z_CRYSTAL_TYPE = GTGItemTags.of("typed_z-crystal");
    public static final TagKey<Item> Z_CRYSTAL_SPECIES = GTGItemTags.of("species_z-crystal");
    public static final TagKey<Item> TERA_SHARDS = GTGItemTags.of("tera_shards");

    public static final TagKey<Item> GIMMICK_KEY_ITEMS = GTGItemTags.of("key_items");
    public static final TagKey<Item> KEY_STONES = GTGItemTags.of("key_stones");
    public static final TagKey<Item> Z_RINGS = GTGItemTags.of("z-rings");
    public static final TagKey<Item> DYNAMAX_BANDS = GTGItemTags.of("dynamax_bands");
    public static final TagKey<Item> TERA_ORBS = GTGItemTags.of("tera_orbs");
    public static final TagKey<Item> BREAKABLE_TERA_ORBS = GTGItemTags.of("breakable_tera_orbs");

    public static final TagKey<Item> GIMMICK_ENCHANTABLE = GTGItemTags.of("enchantable/gimmick");

    private static TagKey<Item> of(String name) {
        return TagKey.of(RegistryKeys.ITEM, GimmeThatGimmickMain.identifier(name));
    }
}
