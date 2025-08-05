package com.provismet.cobblemon.gimmick.datagen.debug;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.component.DataItem;
import com.provismet.cobblemon.gimmick.api.data.component.FormToggle;
import com.provismet.cobblemon.gimmick.api.data.PokemonFeatures;
import com.provismet.cobblemon.gimmick.api.data.PokemonRequirements;
import com.provismet.cobblemon.gimmick.api.data.registry.form.FormChangeToggleDataItem;
import com.provismet.cobblemon.gimmick.api.datagen.FormChangeToggleItemProvider;
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

public class DebugFormToggleGenerator extends FormChangeToggleItemProvider {
    public DebugFormToggleGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, FormChangeToggleDataItem> consumer) {
        consumer.accept(
            GimmeThatGimmickMain.identifier("become_alolan"),
            new FormChangeToggleDataItem(
                new DataItem(
                    "The Alolanator",
                    List.of("Sends your pokemon on vacation."),
                    Registries.ITEM.getId(Items.FLINT),
                    Optional.empty(),
                    Rarity.UNCOMMON
                ),
                new FormToggle(
                    new PokemonRequirements(
                        List.of(
                            "raichu",
                            "muk",
                            "grimer",
                            "vulpix",
                            "ninetales",
                            "rattata",
                            "raticate",
                            "sandshrew",
                            "sandslash",
                            "diglet",
                            "dugtrio",
                            "meowth",
                            "persian",
                            "geodude",
                            "graveler",
                            "golem",
                            "exeggutor",
                            "marowak"
                        ),
                        List.of("normal", "alola"),
                        List.of(),
                        List.of(),
                        List.of(),
                        List.of()
                    ),
                    new PokemonRequirements(
                        List.of(),
                        List.of("normal"),
                        List.of(),
                        List.of(),
                        List.of(),
                        List.of()
                    ),
                    PokemonFeatures.single("alolan", true),
                    PokemonFeatures.single("alolan", false),
                    Optional.of(GimmeThatGimmickMain.identifier("layered_vanilla"))
                )
            )
        );
    }
}
