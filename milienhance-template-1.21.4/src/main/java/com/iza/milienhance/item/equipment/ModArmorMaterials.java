package com.iza.milienhance.item.equipment;

import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.Map;

public interface ModArmorMaterials {
    ArmorMaterial CHAINPLATE = new ArmorMaterial(45, (Map) Util.make(new EnumMap(EquipmentType.class), (map) -> {
        map.put(EquipmentType.BOOTS, 4);
        map.put(EquipmentType.LEGGINGS, 9);
        map.put(EquipmentType.CHESTPLATE, 12);
        map.put(EquipmentType.HELMET, 5);
        map.put(EquipmentType.BODY, 20);
    }), 25, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 5.0F, 0.2F, ItemTags.REPAIRS_CHAIN_ARMOR, ModEquipmentAssetKeys.CHAINIRON);
}
