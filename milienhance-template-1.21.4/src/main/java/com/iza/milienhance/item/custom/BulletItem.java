package com.iza.milienhance.item.custom;

import com.iza.milienhance.sound.ModSoundEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BulletItem extends Item {
    private final int num;
    public BulletItem(Settings settings, int num) {
        super(settings);
        this.num = num;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        ItemStack leftStack = user.getOffHandStack();
        if(leftStack.getItem() instanceof MagazineItem){
            if(leftStack.getDamage()-num>=0){
                leftStack.setDamage(leftStack.getDamage()-num);
                stack.decrementUnlessCreative(1,user);
                user.playSound(ModSoundEvents.AMMO, 0.8F, 0.4F);
                return ActionResult.SUCCESS;
            }
            user.playSound(ModSoundEvents.AMMO, 0.8F, 0.8F);
            return ActionResult.FAIL;
        }
        return ActionResult.FAIL;
    }
}
