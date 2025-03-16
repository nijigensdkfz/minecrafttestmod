package com.iza.milienhance.item.equipment;

import com.iza.milienhance.item.custom.ArmorControlItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.world.World;

public class ModArmorHelmet extends ArmorItem {
    public ModArmorHelmet(Settings settings) {
        super(ModArmorMaterials.CHAINPLATE, EquipmentType.HELMET, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity user){
            if(user.hasStatusEffect(StatusEffects.WATER_BREATHING)&&!user.getInventory().getArmorStack(3).isOf(this)){
                user.removeStatusEffect(StatusEffects.WATER_BREATHING);
                ArmorControlItem.updateResistance(user);
            }
            if(user.hasStatusEffect(StatusEffects.NIGHT_VISION)&&!user.getInventory().getArmorStack(3).isOf(this)){
                user.removeStatusEffect(StatusEffects.NIGHT_VISION);
                ArmorControlItem.updateResistance(user);
            }
        }
    }
}
