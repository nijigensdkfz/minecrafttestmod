package com.iza.milienhance.item.equipment;

import com.iza.milienhance.item.custom.ArmorControlItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.world.World;

public class ModArmorLeggings extends ArmorItem {
    public ModArmorLeggings(Settings settings) {
        super(ModArmorMaterials.CHAINPLATE, EquipmentType.LEGGINGS, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity user){
            if(user.hasStatusEffect(StatusEffects.SPEED)&&!user.getInventory().getArmorStack(1).isOf(this)){
                user.removeStatusEffect(StatusEffects.SPEED);
                ArmorControlItem.updateResistance(user);
            }
        }
    }
}
