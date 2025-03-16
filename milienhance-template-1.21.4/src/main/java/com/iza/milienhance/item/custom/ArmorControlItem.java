package com.iza.milienhance.item.custom;

import com.iza.milienhance.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class ArmorControlItem extends Item {

    public ArmorControlItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient){
            if(user.isSneaking()){
                if(user.hasStatusEffect(StatusEffects.NIGHT_VISION)){
                    user.removeStatusEffect(StatusEffects.NIGHT_VISION);
                }else{
                    if(user.getInventory().getArmorStack(3).isOf(ModItems.Chainiron_Helmet)){
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION,-1,0,true,false,true));
                    }
                }
            }else{
                if(this.match(user,0)&&this.match(user,1)&&this.match(user,2)&&this.match(user,3)){
                    user.removeStatusEffect(StatusEffects.RESISTANCE);
                    user.removeStatusEffect(StatusEffects.SPEED);
                    user.removeStatusEffect(StatusEffects.JUMP_BOOST);
                    user.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
                    user.removeStatusEffect(StatusEffects.WATER_BREATHING);
                }else{
                    if(!match(user,3)){
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING,-1,0,true,false,true));
                    }
                    if(!match(user,2)){
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,-1,0,true,false,true));
                    }
                    if(!match(user,1)){
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,-1,1,true,false,true));
                    }
                    if(!match(user,0)){
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST,-1,1,true,false,true));
                    }
                    updateResistance(user);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    public boolean match(PlayerEntity user,int slot){
        if(slot==3&&user.getInventory().getArmorStack(slot).isOf(ModItems.Chainiron_Helmet)){
            return user.hasStatusEffect(StatusEffects.WATER_BREATHING);
        }
        if(slot==2&&user.getInventory().getArmorStack(slot).isOf(ModItems.Chainiron_Chestplate)){
            return user.hasStatusEffect(StatusEffects.FIRE_RESISTANCE);
        }
        if(slot==1&&user.getInventory().getArmorStack(slot).isOf(ModItems.Chainiron_Leggings)){
            return user.hasStatusEffect(StatusEffects.SPEED);
        }
        if(slot==0&&user.getInventory().getArmorStack(slot).isOf(ModItems.Chainiron_Boots)){
            return user.hasStatusEffect(StatusEffects.JUMP_BOOST);
        }
        return true;
    }

    public static int getCount(PlayerEntity user){
        int i = 0;
        if(user.getInventory().getArmorStack(0).isOf(ModItems.Chainiron_Boots)){
            i++;
        }
        if(user.getInventory().getArmorStack(1).isOf(ModItems.Chainiron_Leggings)){
            i++;
        }
        if(user.getInventory().getArmorStack(2).isOf(ModItems.Chainiron_Chestplate)){
            i++;
        }
        if(user.getInventory().getArmorStack(3).isOf(ModItems.Chainiron_Helmet)){
            i++;
        }
        return i;
    }

    public static void updateResistance(PlayerEntity user){
        user.removeStatusEffect(StatusEffects.RESISTANCE);
        if(getCount(user)!=0){
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,-1,getCount(user)-1,true,false,true));
        }
    }
}
