package com.provismet.cobblemon.gimmick.datagen.debug;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.api.data.component.MegaEvolution;
import com.provismet.cobblemon.gimmick.api.data.registry.MegaStone;
import com.provismet.cobblemon.gimmick.api.datagen.MegaStoneItemProvider;
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

public class DebugMegaStoneGenerator extends MegaStoneItemProvider {
    public DebugMegaStoneGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, MegaStone> consumer) {
        consumer.accept(
            GimmeThatGimmickMain.identifier("fake_mega_gardevoir"),
            new MegaStone(
                new DataItem(
                    "Gardevoirite 2.0",
                    List.of("The fan favourite."),
                    Registries.ITEM.getId(Items.EMERALD),
                    Optional.empty(),
                    Rarity.RARE
                ),
                MegaEvolution.create("gardevoir"),
                "gardevoirite"
            )
        );
    }
}
