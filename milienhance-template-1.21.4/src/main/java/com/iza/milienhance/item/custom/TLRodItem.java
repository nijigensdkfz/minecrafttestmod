package com.iza.milienhance.item.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TLRodItem extends Item {
    public TLRodItem(Settings settings) {
        super(settings.maxCount(1));
    }
    public ActionResult use(World world, PlayerEntity user, Hand hand){
        if(world instanceof ServerWorld serverWorld){
            if(user.isSneaking()){
                if(!user.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)||!user.hasStatusEffect(StatusEffects.CONDUIT_POWER)){
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE,-1,2,true,false,true));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER,-1,1,true,false,true));
                }else {
                    user.removeStatusEffect(StatusEffects.DOLPHINS_GRACE);
                    user.removeStatusEffect(StatusEffects.CONDUIT_POWER);
                }
            }else{
                Vec3d vec3d = user.raycast(30.0,1.0F,false).getPos();
                LightningEntity lightning = new LightningEntity(EntityType.LIGHTNING_BOLT,serverWorld);
                lightning.setPosition(vec3d);
                world.spawnEntity(lightning);
                user.getStackInHand(hand).damage(1,user);
            }
        }
        return ActionResult.SUCCESS;
    }
}
