package com.iza.milienhance.component.type;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent BURGER = (new FoodComponent.Builder()).nutrition(12).saturationModifier(2.0F).build();
    public static final FoodComponent CHEESE = (new FoodComponent.Builder()).nutrition(6).saturationModifier(0.6F).build();
    public static final FoodComponent DISASTER = (new FoodComponent.Builder()).nutrition(0).saturationModifier(0.0F).alwaysEdible().build();
}
