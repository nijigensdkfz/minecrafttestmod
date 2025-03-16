package com.iza.milienhance.item.custom;

import com.iza.milienhance.entity.projectile.BulletEntity;
import com.iza.milienhance.item.ModItems;
import com.iza.milienhance.sound.ModSoundEvents;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

import java.util.List;

public class GunItem extends Item {

    public GunItem(Item.Settings settings, int content) {
        super(settings.maxDamage(content));
    }
    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 1);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if(itemStack.getDamage()<itemStack.getMaxDamage()){
            if (world instanceof ServerWorld serverWorld) {
                ItemStack bullet = new ItemStack(ModItems.Bullet);
                ProjectileEntity.spawnWithVelocity(BulletEntity::new, serverWorld, bullet, user, 0.0F, 2.5F, 0.1F);
            }
            user.playSound(ModSoundEvents.FIRESHOT, 1.0F, 1.0F);
            itemStack.setDamage(itemStack.getDamage()+1);
            return ActionResult.PASS;
        }else{
            ItemStack leftStack = user.getOffHandStack();
            if(leftStack.getItem() instanceof MagazineItem){
                if(leftStack.getMaxDamage() == itemStack.getMaxDamage()&&leftStack.getDamage()!=leftStack.getMaxDamage()){
                    int i = itemStack.getDamage();
                    itemStack.setDamage(leftStack.getDamage());
                    leftStack.setDamage(i);
                    user.playSound(ModSoundEvents.AMMO, 0.8F, 0.4F);
                    return ActionResult.SUCCESS;
                }
            }
            user.playSound(ModSoundEvents.AMMOEMPTY, 0.8F, 1.0F);
        }
        return ActionResult.FAIL;
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (world instanceof ServerWorld serverWorld) {
            ItemStack bullet = new ItemStack(ModItems.Bullet);
            ProjectileEntity.spawnWithVelocity(BulletEntity::new, serverWorld, bullet, user, 0.0F, 2.5F, 0.1F);
        }
        user.playSound(ModSoundEvents.FIRESHOT, 1.0F, 1.0F);
        stack.setDamage(stack.getDamage()+1);
        return true;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world instanceof ServerWorld serverWorld) {
            ItemStack bullet = new ItemStack(ModItems.Bullet);
            ProjectileEntity.spawnWithVelocity(BulletEntity::new, serverWorld, bullet, user, 0.0F, 2.5F, 0.1F);
        }
        user.playSound(ModSoundEvents.FIRESHOT, 1.0F, 1.0F);
        stack.setDamage(stack.getDamage()+1);
        return stack;
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 5*stack.getMaxDamage()-5*stack.getDamage();
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return new BulletEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.NONE;
    }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        tooltip.add(Text.of("Left Ammo:"+(stack.getMaxDamage()- stack.getDamage())+"/"+stack.getMaxDamage()));
    }
}
