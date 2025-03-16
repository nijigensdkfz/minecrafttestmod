package com.iza.milienhance.recipe.book;

import com.iza.milienhance.Milienhance;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipeBookCategories {
    public static final RecipeBookCategory CHIMERA = register("crafting_misc_things");
    public static final RecipeBookCategory PLANT_POT = register("crafting_plant");
    public static final RecipeBookCategory HUGE_BLAST = register("huge_blast");

    public ModRecipeBookCategories() {
    }

    private static RecipeBookCategory register(String id) {
        return (RecipeBookCategory) Registry.register(Registries.RECIPE_BOOK_CATEGORY, Identifier.of(Milienhance.MOD_ID,id), new RecipeBookCategory());
    }

    public static RecipeBookCategory registerAndGetDefault(Registry<RecipeBookCategory> registry) {
        return CHIMERA;
    }

    public static void initialize(){}
}
