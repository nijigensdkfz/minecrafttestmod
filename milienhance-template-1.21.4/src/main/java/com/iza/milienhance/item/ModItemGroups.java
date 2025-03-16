package com.iza.milienhance.item;

import com.iza.milienhance.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroups {
    public static final ItemGroup mili_industry = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.Mech_Arm))
            .displayName(Text.translatable("itemGroup.mili_industry"))
            .entries((context, entries) -> {
                entries.add(ModItems.Mech_Arm);
                entries.add(ModItems.Steel_Pipe);
                entries.add(ModItems.Enhance_Part);
                entries.add(ModItems.Genesis_Liquid);
                entries.add(ModItems.Enhanced_Pickaxe_Base);
                entries.add(ModItems.Enhanced_Pickaxe_Plus);
                entries.add(ModItems.Genesis_rod);
                entries.add(ModItems.Burger);
                entries.add(ModItems.Cheese);
                entries.add(ModItems.Cursed_Wine);
                entries.add(ModItems.CHIMERA);
                entries.add(ModItems.PLANT_POT);
                entries.add(ModItems.MAINTAINER);
                entries.add(ModItems.GRIND_OBSIDIAN);
                entries.add(ModItems.MINER);
                entries.add(ModItems.BROKEN_MINER);
                entries.add(ModItems.DOOMED_MINER);
            })
            .build();

    public static final ItemGroup mili_weapon = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.Autorifle))
            .displayName(Text.translatable("itemGroup.mili_weapon"))
            .entries((context, entries) -> {
                entries.add(ModItems.Autorifle);
                entries.add(ModItems.Magazine);
                entries.add(ModItems.Bullets);
                entries.add(ModItems.Bullet);
                entries.add(ModItems.Holy_Sword);
                entries.add(ModItems.Shadow_Dagger);
                entries.add(ModItems.Tharasa_Lightning_Rod);
                entries.add(ModItems.ThrownBrick);
                entries.add(ModItems.Armor_Battery);
                entries.add(ModItems.Chainiron_Helmet);
                entries.add(ModItems.Chainiron_Chestplate);
                entries.add(ModItems.Chainiron_Leggings);
                entries.add(ModItems.Chainiron_Boots);
                entries.add(ModItems.Chainiron_Horse_Armor);
                entries.add(ModItems.Flight_Engine);
            })
            .build();
    public static final ItemGroup anti_bomb = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.ANTI_BOMB_BRICKS))
            .displayName(Text.translatable("itemGroup.anti_bomb"))
            .entries((context, entries) -> {
                entries.add(ModItems.ANTI_BOMB_BRICKS);
                entries.add(ModItems.ANTI_BOMB_PILLAR);
                entries.add(ModItems.ANTI_BOMB_GLASS);
                entries.add(ModItems.ANTI_BOMB_GLASS_PANE);
                entries.add(ModItems.ANTI_BOMB_DOOR);
                entries.add(ModItems.ANTI_BOMB_TRAPDOOR);
                entries.add(ModItems.ANTI_BOMB_WALL);
                entries.add(ModItems.ANTI_BOMB_SLAB);
                entries.add(ModItems.ANTI_BOMB_STAIRS);
                entries.add(ModItems.ANTI_BOMB_LAMP);
                entries.add(ModItems.ANTI_BOMB_BUTTON);
                entries.add(ModItems.ANTI_BOMB_LEVER);
                entries.add(ModItems.ANTI_BOMB_PRESSURE_PLATE);
                entries.add(ModItems.ANTI_BOMB_LADDER);
                entries.add(ModItems.ANTI_BOMB_LANTERN);
                entries.add(ModItems.ANTI_BOMB_ROD);
                entries.add(ModItems.STEEL_COIL);
                entries.add(ModItems.GOLD_BARS);
                entries.add(ModItems.COPPER_COIL);
                entries.add(ModItems.IRON_CHEST);
            })
            .build();
    public static void initialize(){
        Registry.register(Registries.ITEM_GROUP, Identifier.of("milienhance","mili_industry"),mili_industry);
        Registry.register(Registries.ITEM_GROUP, Identifier.of("milienhance","mili_weapon"),mili_weapon);
        Registry.register(Registries.ITEM_GROUP, Identifier.of("milienhance","anti_bomb"),anti_bomb);
    }
}
