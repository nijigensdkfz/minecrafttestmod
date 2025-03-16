package com.iza.milienhance.screen;

import com.iza.milienhance.Milienhance;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlantPotScreen extends HandledScreen<PlantPotScreenHandler> {
    private static final Identifier TEXTURE = Identifier.of(Milienhance.MOD_ID,"textures/gui/container/plant_pot.png");
    public PlantPotScreen(PlantPotScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        int x = (this.width-this.backgroundWidth)/2;
        int y = (this.height-this.backgroundHeight)/2;
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE,x,y,0,0,backgroundWidth,backgroundHeight,256,256);
        renderProgressArrow(context,x,y);
    }

    private void renderProgressArrow(DrawContext context, int x, int y) {
        if(handler.isCrafting()){
            context.drawTexture(RenderLayer::getGuiTextured, TEXTURE,x+70,y+49-handler.getScale(),177,15-handler.getScale(),38,handler.getScale(),256,256);
        }
    }
}
