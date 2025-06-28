package com.provismet.cobblemon.gimmick.api.data;

import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface DataItemStack {
    @Nullable
    ItemStack create();
}
