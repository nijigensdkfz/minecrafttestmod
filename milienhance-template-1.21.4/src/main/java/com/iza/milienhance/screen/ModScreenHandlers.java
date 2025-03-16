package com.iza.milienhance.screen;

import com.iza.milienhance.Milienhance;
import com.iza.milienhance.data.ChimeraData;
import com.iza.milienhance.data.PlantPotData;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.BlastFurnaceScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<PlantPotScreenHandler> PLANT_POT_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Milienhance.MOD_ID, "plant_pot"),
                    new ExtendedScreenHandlerType<>(PlantPotScreenHandler::new, PlantPotData.CODEC));
    public static final ScreenHandlerType<ChimeraScreenHandler> CHIMERA_SCREEN_HANDLER =
        Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Milienhance.MOD_ID, "chimera"),
                new ExtendedScreenHandlerType<>(ChimeraScreenHandler::new, ChimeraData.CODEC));

    public static final ScreenHandlerType<MaintainerScreenHandler> MAINTAINER_SCREEN_HANDLER = (ScreenHandlerType)Registry.register(Registries.SCREEN_HANDLER, Identifier.of("maintainer"), new ScreenHandlerType(MaintainerScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static void initialize(){}

}
