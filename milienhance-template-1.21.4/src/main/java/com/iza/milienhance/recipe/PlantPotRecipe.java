package com.iza.milienhance.recipe;

import com.iza.milienhance.item.ModItems;
import com.iza.milienhance.recipe.book.ModRecipeBookCategories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;


public class PlantPotRecipe extends AbstractCookingRecipe {
    public PlantPotRecipe(String string, CookingRecipeCategory cookingRecipeCategory, Ingredient ingredient, ItemStack itemStack, float f, int i) {
        super(string, cookingRecipeCategory, ingredient, itemStack, f, i);
    }

    protected Item getCookerItem() {
        return ModItems.PLANT_POT;
    }

    public RecipeSerializer<PlantPotRecipe> getSerializer() {
        return ModRecipeSerializer.PLANT_POT;
    }

    public RecipeType<PlantPotRecipe> getType() {
        return ModRecipeType.PLANT_POT;
    }

    public RecipeBookCategory getRecipeBookCategory() {
        RecipeBookCategory var10000;
        switch (this.getCategory()) {
            case BLOCKS, FOOD, MISC -> var10000 = ModRecipeBookCategories.PLANT_POT;
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return var10000;
    }

    public ItemStack result() {
        return super.result();
    }
}
