package com.provismet.cobblemon.gimmick.util.tag;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public abstract class GTGItemTags {
    public static final TagKey<Item> MEGA_STONES = GTGItemTags.of("mega_stones");
    public static final TagKey<Item> MEGA_STONES_X = GTGItemTags.of("mega_stones_x");
    public static final TagKey<Item> MEGA_STONES_Y = GTGItemTags.of("mega_stones_y");
    public static final TagKey<Item> MEGA_STONES_Z = GTGItemTags.of("mega_stones_z");

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

    public static final TagKey<Item> FORM_CHANGE = GTGItemTags.of("form_change");
    public static final TagKey<Item> ARCEUS_PLATES = GTGItemTags.of("arceus_plates");
    public static final TagKey<Item> SINNOH_ORBS = GTGItemTags.of("sinnoh_orbs");
    public static final TagKey<Item> HOENN_ORBS = GTGItemTags.of("hoenn_orbs");
    public static final TagKey<Item> GALAR_RUSTED = GTGItemTags.of("galar_rusted");
    public static final TagKey<Item> SILVALLY_MEMORIES = GTGItemTags.of("silvally_memories");
    public static final TagKey<Item> GENESECT_DRIVES = GTGItemTags.of("genesect_drives");
    public static final TagKey<Item> ORICORIO_NECTAR = GTGItemTags.of("oricorio_nectar");
    public static final TagKey<Item> OGERPON_MASKS = GTGItemTags.of("ogerpon_masks");

    private static TagKey<Item> of (String name) {
        return TagKey.of(RegistryKeys.ITEM, GimmeThatGimmickMain.identifier(name));
    }
}
