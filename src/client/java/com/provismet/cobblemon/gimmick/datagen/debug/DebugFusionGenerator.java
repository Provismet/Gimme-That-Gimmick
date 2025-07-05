package com.provismet.cobblemon.gimmick.datagen.debug;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.api.data.component.Fusion;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.PokemonRequirements;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeFusionDataItem;
import com.provismet.cobblemon.gimmick.api.datagen.FormChangeFusionItemProvider;
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

public class DebugFusionGenerator extends FormChangeFusionItemProvider {
    public DebugFusionGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, FormChangeFusionDataItem> consumer) {
        consumer.accept(
            GimmeThatGimmickMain.identifier("fake_dna_splicers"),
            new FormChangeFusionDataItem(
                new DataItem(
                    "Fake DNA Splicers",
                    List.of(
                        "Tooltip line 1",
                        "Tooltip line 2"
                    ),
                    Registries.ITEM.getId(Items.PAPER),
                    Optional.of(20),
                    Rarity.EPIC
                ),
                new Fusion(
                    PokemonRequirements.species("kyurem"),
                    List.of(PokemonRequirements.species("reshiram"), PokemonRequirements.species("zekrom")),
                    List.of(PokemonFeatures.single("absofusion", "white"), PokemonFeatures.single("absofusion", "black")),
                    PokemonFeatures.single("absofusion", "none"),
                    Optional.empty(),
                    Optional.empty()
                )
            )
        );
    }
}
