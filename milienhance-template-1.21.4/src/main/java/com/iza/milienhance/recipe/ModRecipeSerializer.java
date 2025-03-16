package com.iza.milienhance.recipe;

import com.iza.milienhance.Milienhance;
import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public interface ModRecipeSerializer<T extends Recipe<?>> {
    RecipeSerializer<ChimeraRecipe> CHIMERA = register("chimera", new AbstractCookingRecipe.Serializer(ChimeraRecipe::new,100));
    RecipeSerializer<PlantPotRecipe> PLANT_POT = register("plant_pot", new AbstractCookingRecipe.Serializer(PlantPotRecipe::new,100));


    MapCodec<T> codec();

    /** @deprecated */
    @Deprecated
    PacketCodec<RegistryByteBuf, T> packetCodec();

    static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String id, S serializer) {
        return Registry.register(Registries.RECIPE_SERIALIZER,  Identifier.of(Milienhance.MOD_ID,id), serializer);
    }
    static void initialize(){}
}
