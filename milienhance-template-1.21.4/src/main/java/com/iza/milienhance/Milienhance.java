package com.iza.milienhance;

import com.iza.milienhance.block.ModBlocks;
import com.iza.milienhance.block.entity.ModBlockEntityType;
import com.iza.milienhance.entity.ModEntities;
import com.iza.milienhance.item.ModItemGroups;
import com.iza.milienhance.item.ModItems;
import com.iza.milienhance.recipe.ModRecipeSerializer;
import com.iza.milienhance.recipe.ModRecipeType;
import com.iza.milienhance.recipe.book.ModRecipeBookCategories;
import com.iza.milienhance.screen.*;
import com.iza.milienhance.sound.ModSoundEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Milienhance implements ModInitializer {
	public static final String MOD_ID = "milienhance";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		ModItems.initialize();
		ModItemGroups.initialize();
		ModBlocks.initialize();
		ModEntities.initialize();
		ModBlockEntityType.initialize();
		ModSoundEvents.initialize();
		ModScreenHandlers.initialize();
		ModRecipeType.initialize();
		ModRecipeSerializer.initialize();
		ModRecipeBookCategories.initialize();
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANTI_BOMB_GLASS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANTI_BOMB_GLASS_PANE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANTI_BOMB_DOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANTI_BOMB_TRAPDOOR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHIMERA, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PLANT_POT, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.MAINTAINER, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ANTI_BOMB_LADDER, RenderLayer.getCutout());
		EntityRendererRegistry.register(ModEntities.BULLET, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.THROWN_BRICK, FlyingItemEntityRenderer::new);
		HandledScreens.register(ModScreenHandlers.PLANT_POT_SCREEN_HANDLER, PlantPotScreen::new);
		HandledScreens.register(ModScreenHandlers.CHIMERA_SCREEN_HANDLER, ChimeraScreen::new);
		HandledScreens.register(ModScreenHandlers.MAINTAINER_SCREEN_HANDLER, MaintainerScreen::new);
	}
}