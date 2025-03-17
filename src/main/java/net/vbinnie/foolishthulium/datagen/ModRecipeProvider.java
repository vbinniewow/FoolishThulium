package net.vbinnie.foolishthulium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.vbinnie.foolishthulium.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> THULIUM_SMELTABLES = List.of(ModItems.RAW_THULIUM);

        offerSmelting(recipeExporter, THULIUM_SMELTABLES, RecipeCategory.MISC, ModItems.THULIUM_INGOT, 0.25f, 200, "thulium");
        offerBlasting(recipeExporter, THULIUM_SMELTABLES, RecipeCategory.MISC, ModItems.THULIUM_INGOT, 0.25f, 100, "thulium");

    }
}
