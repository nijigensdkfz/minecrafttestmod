package com.iza.milienhance.screen;

import com.iza.milienhance.Milienhance;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.RenameItemC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MaintainerScreen extends ForgingScreen<MaintainerScreenHandler> {
    private static final Identifier ERROR_TEXTURE = Identifier.ofVanilla("container/anvil/error");
    private static final Identifier TEXTURE = Identifier.of(Milienhance.MOD_ID,"textures/gui/container/maintainer.png");
    private final PlayerEntity player;

    public MaintainerScreen(MaintainerScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title, TEXTURE);
        this.player = inventory.player;
        this.titleX = 60;
    }

    protected void setup() {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
    }


    public void resize(MinecraftClient client, int width, int height) {
        this.init(client, width, height);
    }

    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        super.drawForeground(context, mouseX, mouseY);
    }

    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        super.drawBackground(context, delta, mouseX, mouseY);
    }

    protected void drawInvalidRecipeArrow(DrawContext context, int x, int y) {
        if ((((MaintainerScreenHandler)this.handler).getSlot(0).hasStack() || ((MaintainerScreenHandler)this.handler).getSlot(1).hasStack()) && !((MaintainerScreenHandler)this.handler).getSlot(((MaintainerScreenHandler)this.handler).getResultSlotIndex()).hasStack()) {
            context.drawGuiTexture(RenderLayer::getGuiTextured, ERROR_TEXTURE, x + 99, y + 45, 28, 21);
        }

    }
}
