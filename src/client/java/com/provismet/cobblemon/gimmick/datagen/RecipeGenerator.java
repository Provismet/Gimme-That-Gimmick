package com.provismet.cobblemon.gimmick.datagen;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.registry.GTGItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;

import java.util.concurrent.CompletableFuture;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate (RecipeExporter recipeExporter) {
        recipeExporter.accept(
            GimmeThatGimmickMain.identifier("max_soup"),
            new ShapelessRecipe(
                "cobblemon",
                CraftingRecipeCategory.MISC,
                GTGItems.MAX_SOUP.getDefaultStack(),
                DefaultedList.copyOf(Ingredient.ofItems(GTGItems.MAX_MUSHROOM, Items.BOWL),
                    Ingredient.ofItems(GTGItems.MAX_MUSHROOM),
                    Ingredient.ofItems(GTGItems.MAX_MUSHROOM),
                    Ingredient.ofItems(GTGItems.MAX_MUSHROOM),
                    Ingredient.ofItems(Items.BOWL)
                )
            ),
            recipeExporter.getAdvancementBuilder()
                .criterion(FabricRecipeProvider.hasItem(GTGItems.MAX_MUSHROOM), FabricRecipeProvider.conditionsFromItem(GTGItems.MAX_MUSHROOM))
                .criterion(FabricRecipeProvider.hasItem(Items.BOWL), FabricRecipeProvider.conditionsFromItem(Items.BOWL))
                .build(GimmeThatGimmickMain.identifier("max_soup"))
        );
    }
}
