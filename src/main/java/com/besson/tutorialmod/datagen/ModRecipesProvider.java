package com.besson.tutorialmod.datagen;

import com.besson.tutorialmod.TutorialMod;
import com.besson.tutorialmod.block.ModBlocks;
import com.besson.tutorialmod.item.ModItems;
import com.besson.tutorialmod.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipesProvider extends FabricRecipeProvider {
    public static final List<ItemConvertible> ICE_ETHER = List.of(ModItems.RAW_ICE_ETHER,ModBlocks.ICE_ETHER_ORE, Items.ICE);

    public ModRecipesProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerReversibleCompactingRecipes(exporter,//9合1，1合9
                RecipeCategory.MISC, ModItems.ICE_ETHER,
                RecipeCategory.BUILDING_BLOCKS, ModBlocks.ICE_ETHER_BLOCK);

        offerSmelting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,0.7f,200,"ice_ether");//熔炉

        offerBlasting(exporter, ICE_ETHER, RecipeCategory.MISC, ModItems.ICE_ETHER,0.7f,100,"ice_ether");//高炉

        offerFoodCookingRecipe(exporter, "campfire_cooking",RecipeSerializer.CAMPFIRE_COOKING, CampfireCookingRecipe::new,
                200,ModItems.RAW_ICE_ETHER,ModItems.ICE_ETHER,0.35f);//营火

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,Items.SUGAR,3)
                .pattern("###")
                .input('#', ModItemTags.SUGER_TAG)
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.BEETROOT))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "sugar_from_beetroot"));//甘蔗糖

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,Items.ENCHANTED_GOLDEN_APPLE)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .input('#',Ingredient.ofItems(Items.GOLD_BLOCK))
                        .input('X',Ingredient.ofItems(Items.APPLE))
                                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.GOLD_BLOCK))
                                        .criterion("has_item", RecipeProvider.conditionsFromItem(Items.APPLE))
                                                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "god_dapple"));

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModBlocks.ICE_ETHER_ORE)
                .input(ModItems.RAW_ICE_ETHER)
                .input(Items.STONE)
                .criterion("has_item", RecipeProvider.conditionsFromItem(ModItems.RAW_ICE_ETHER))
                .criterion("has_item", RecipeProvider.conditionsFromItem(Items.STONE))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "ice_ether_ore"));
    }
}
