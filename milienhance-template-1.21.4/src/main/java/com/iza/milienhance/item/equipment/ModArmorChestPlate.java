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

public class ModArmorChestPlate extends ArmorItem {
    public ModArmorChestPlate(Settings settings) {
        super(ModArmorMaterials.CHAINPLATE, EquipmentType.CHESTPLATE, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity user){
            if(user.hasStatusEffect(StatusEffects.FIRE_RESISTANCE)&&!user.getInventory().getArmorStack(2).isOf(this)){
                user.removeStatusEffect(StatusEffects.FIRE_RESISTANCE);
                ArmorControlItem.updateResistance(user);
            }
        }
    }
}
