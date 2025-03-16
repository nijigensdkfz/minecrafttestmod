package com.iza.milienhance.screen;

import com.iza.milienhance.Milienhance;
import com.iza.milienhance.recipe.ChimeraRecipe;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ChimeraScreen extends HandledScreen<ChimeraScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Milienhance.MOD_ID,"textures/gui/container/chimera.png");
    public ChimeraScreen(ChimeraScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width-this.backgroundWidth)/2;
        int y = (this.height-this.backgroundHeight)/2;
        context.drawTexture(RenderLayer::getGuiTextured,TEXTURE,x,y,0,0,backgroundWidth,backgroundHeight,256,256);
        renderProgressArrow(context,x,y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()){
            context.drawTexture(RenderLayer::getGuiTextured,TEXTURE,x+83,y+36,177,0,12,handler.getScale(),256,256);
        }
    }
}
