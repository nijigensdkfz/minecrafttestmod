package com.iza.milienhance.item.custom;

import com.iza.milienhance.item.ModItems;
import com.iza.milienhance.sound.ModSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class MagazineItem extends Item {

    public MagazineItem(Settings settings, int content) {
        super(settings.maxDamage(content));
    }

    public int getMaxCount(){return 1;}

    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.of("Left Ammo:"+(stack.getMaxDamage()- stack.getDamage())+"/"+stack.getMaxDamage()));
    }
}
