package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.sabio.ahoy.registry.AhoyItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AhoyRecipeGenerator extends FabricRecipeProvider {
    public AhoyRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public @NotNull RecipeGenerator getRecipeGenerator(RegistryWrapper.@NotNull WrapperLookup registryLookup, @NotNull RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryEntryLookup<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TRANSPORTATION, AhoyItems.SHIP_ITEM)
                        .pattern("PPP")
                        .pattern("PWP")
                        .pattern("PPP")
                        .input('P', Items.OAK_PLANKS)
                        .input('W', Items.OAK_LOG)
                        .criterion(hasItem(Items.OAK_PLANKS), conditionsFromItem(Items.OAK_PLANKS))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, AhoyItems.CANNON_BALL, 4)
                        .pattern(" I ")
                        .pattern("I I")
                        .pattern(" I ")
                        .input('I', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.COMBAT, AhoyItems.BALLISTA_BOLT, 4)
                        .pattern("  S")
                        .pattern(" P ")
                        .pattern("I  ")
                        .input('S', Items.STRING)
                        .input('P', Items.STICK)
                        .input('I', Items.IRON_INGOT)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);

                ShapedRecipeJsonBuilder.create(itemLookup, RecipeCategory.TOOLS, AhoyItems.ANCHOR)
                        .pattern(" I ")
                        .pattern("ICI")
                        .pattern("I I")
                        .input('I', Items.IRON_INGOT)
                        .input('C', Items.IRON_CHAIN)
                        .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Ahoy Recipes";
    }
}