package net.vbinnie.foolishthulium.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.vbinnie.foolishthulium.block.ModBlocks;
import net.vbinnie.foolishthulium.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        List<ItemConvertible> THULIUM_SMELTABLES = List.of(ModItems.RAW_THULIUM, ModBlocks.DEEPSLATE_THULIUM_ORE);

        offerSmelting(recipeExporter, THULIUM_SMELTABLES, RecipeCategory.MISC, ModItems.THULIUM_INGOT, 0.25f, 200, "thulium");
        offerBlasting(recipeExporter, THULIUM_SMELTABLES, RecipeCategory.MISC, ModItems.THULIUM_INGOT, 0.25f, 100, "thulium");




        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.RAW_THULIUM_BLOCK)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R', ModItems.RAW_THULIUM)
                .criterion(hasItem(ModItems.RAW_THULIUM), conditionsFromItem(ModItems.RAW_THULIUM))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_THULIUM, 9)
                .input(ModBlocks.RAW_THULIUM_BLOCK)
                .criterion(hasItem(ModBlocks.RAW_THULIUM_BLOCK), conditionsFromItem(ModBlocks.RAW_THULIUM_BLOCK))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.THULIUM_BLOCK)
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .input('T', ModItems.THULIUM_INGOT)
                .criterion(hasItem(ModItems.THULIUM_INGOT), conditionsFromItem(ModItems.THULIUM_INGOT))
                .offerTo(recipeExporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.THULIUM_INGOT, 9)
                .input(ModBlocks.THULIUM_BLOCK)
                .criterion(hasItem(ModBlocks.THULIUM_BLOCK), conditionsFromItem(ModBlocks.THULIUM_BLOCK))
                .offerTo(recipeExporter);
    }
}
