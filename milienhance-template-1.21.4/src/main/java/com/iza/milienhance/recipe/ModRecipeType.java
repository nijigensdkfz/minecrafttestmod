package com.iza.milienhance.recipe;

import com.iza.milienhance.Milienhance;
import net.minecraft.recipe.BlastingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface ModRecipeType<T extends Recipe<?>> {
    RecipeType<ChimeraRecipe> CHIMERA = register("chimera");
    RecipeType<PlantPotRecipe> PLANT_POT = register("plant_pot");

    static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return (RecipeType) Registry.register(Registries.RECIPE_TYPE, Identifier.of(Milienhance.MOD_ID,id), new RecipeType<T>() {
            public String toString() {
                return id;
            }
        });
    }
    static void initialize(){}
}
