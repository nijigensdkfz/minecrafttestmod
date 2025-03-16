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

public class ModArmorBoots extends ArmorItem {
    public ModArmorBoots(Settings settings) {
        super(ModArmorMaterials.CHAINPLATE, EquipmentType.BOOTS, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(entity instanceof PlayerEntity user){
            if(user.hasStatusEffect(StatusEffects.JUMP_BOOST)&&!user.getInventory().getArmorStack(0).isOf(this)){
                user.removeStatusEffect(StatusEffects.JUMP_BOOST);
                ArmorControlItem.updateResistance(user);
            }
        }
    }
}
