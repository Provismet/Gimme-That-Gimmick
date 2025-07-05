package com.provismet.cobblemon.gimmick.api.data;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * General interface for objects that produce items from data.
 */
public interface DataItemStack {
    @Nullable
    ItemStack create ();

    String name ();
}
