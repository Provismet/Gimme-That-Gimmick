package com.provismet.cobblemon.gimmick.datagen.debug;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.DataItem;
import com.provismet.cobblemon.gimmick.api.data.registry.HeldItem;
import com.provismet.cobblemon.gimmick.api.datagen.HeldItemProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DebugHeldItemGenerator extends HeldItemProvider {
    public DebugHeldItemGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, HeldItem> consumer) {
        consumer.accept(
            GimmeThatGimmickMain.identifier("fake_leftovers"),
            new HeldItem(
                new DataItem(
                    "Just An Apple",
                    List.of("Maybe it'll leave some leftovers..."),
                    Registries.ITEM.getId(Items.APPLE),
                    Optional.empty(),
                    Rarity.UNCOMMON
                ),
                "leftovers"
            )
        );
    }
}
