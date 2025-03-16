package com.iza.milienhance.item;

import com.iza.milienhance.Milienhance;
import com.iza.milienhance.block.ModBlocks;
import com.iza.milienhance.component.type.ModConsumableComponents;
import com.iza.milienhance.component.type.ModFoodComponents;
import com.iza.milienhance.item.custom.*;
import com.iza.milienhance.item.equipment.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;


public class ModItems {

    private static Function<Item.Settings, Item> createBlockItemWithUniqueName(Block block) {
        return (settings) -> {
            return new BlockItem(block, settings.useItemPrefixedTranslationKey());
        };
    }

    private static RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Milienhance.MOD_ID,id));
    }

    private static RegistryKey<Item> keyOf(RegistryKey<Block> blockKey) {
        return RegistryKey.of(RegistryKeys.ITEM, blockKey.getValue());
    }

    public static Item register(Block block) {
        return register(block, BlockItem::new);
    }

    public static Item register(Block block, Item.Settings settings) {
        return register(block, BlockItem::new, settings);
    }

    public static Item register(Block block, UnaryOperator<Item.Settings> settingsOperator) {
        return register(block, (blockx, settings) -> {
            return new BlockItem(blockx, (Item.Settings)settingsOperator.apply(settings));
        });
    }

    public static Item register(Block block, BiFunction<Block, Item.Settings, Item> factory) {
        return register(block, factory, new Item.Settings());
    }

    public static Item register(Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        return register(keyOf(block.getRegistryEntry().registryKey()), (itemSettings) -> {
            return (Item)factory.apply(block, itemSettings);
        }, settings.useBlockPrefixedTranslationKey());
    }

    public static Item register(String id, Function<Item.Settings, Item> factory) {
        return register(keyOf(id), factory, new Item.Settings());
    }

    public static Item register(String id, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return register(keyOf(id), factory, settings);
    }

    public static Item register(String id, Item.Settings settings) {
        return register(keyOf(id), Item::new, settings);
    }

    public static Item register(String id) {
        return register(keyOf(id), Item::new, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory) {
        return register(key, factory, new Item.Settings());
    }

    public static Item register(RegistryKey<Item> key, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = (Item)factory.apply(settings.registryKey(key));
        if (item instanceof BlockItem blockItem) {
            blockItem.appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item)Registry.register(Registries.ITEM, key, item);
    }
    public static void initialize(){}

    public static final Item Mech_Arm = register("mech_arm");
    public static final Item Steel_Pipe = register("steel_pipe");
    public static final Item Genesis_Liquid = register("genesis_liquid", GenesisItem::new);
    public static final Item Enhance_Part = register("enhance_part");
    public static final Item Burger = register("burger",(new Item.Settings()).food(ModFoodComponents.BURGER));
    public static final Item Cheese = register("cheese",(new Item.Settings()).food(ModFoodComponents.CHEESE));
    public static final Item Cursed_Wine = register("cursed_wine",(new Item.Settings()).food(ModFoodComponents.DISASTER, ModConsumableComponents.DISASTER));
    public static final Item Enhanced_Pickaxe_Base = register("enhanced_pickaxe_base", (settings) -> {
        return new EPickaxeItem(settings,false);
    }, (new Item.Settings()).maxDamage(1536).fireproof().component(DataComponentTypes.TOOL, EPickaxeItem.createToolComponent()).repairable(ModItems.Enhance_Part).enchantable(25));
    public static final Item Enhanced_Pickaxe_Plus = register("enhanced_pickaxe_plus", (settings) -> {
        return new EPickaxeItem(settings,true);
    }, (new Item.Settings()).maxDamage(1536).fireproof().component(DataComponentTypes.TOOL, EPickaxeItem.createToolComponent()).repairable(ModItems.Enhance_Part).enchantable(25));
    public static final Item Flight_Engine = register("flight_engine", FlightEngineItem::new);
    public static final Item Holy_Sword = register("holy_sword", HolySwordItem::new, (new Item.Settings()).rarity(Rarity.EPIC).maxDamage(3072).component(DataComponentTypes.TOOL, HolySwordItem.createToolComponent()).repairable(ModItems.Enhance_Part).attributeModifiers(HolySwordItem.createAttributeModifiers(8,0)).enchantable(25));
    public static final Item Shadow_Dagger = register("shadow_dagger", ShadowDaggerItem::new, (new Item.Settings()).rarity(Rarity.EPIC).maxDamage(1024).component(DataComponentTypes.TOOL, ShadowDaggerItem.createToolComponent()).repairable(ModItems.Enhance_Part).attributeModifiers(ShadowDaggerItem.createAttributeModifiers(2,0)).enchantable(25));
    public static final Item Tharasa_Lightning_Rod = register("tharasa_lightning_rod",TLRodItem::new);
    public static final Item Genesis_rod = register("genesis_rod", GenesisRodItem::new);
    public static final Item ThrownBrick = register("thrown_brick",ThrownBrickItem::new, (new Item.Settings()).maxCount(32));
    public static final Item Bullet = register("bullet", (settings) -> {
        return new BulletItem(settings,1);
    },(new Item.Settings()));
    public static final Item Bullets = register("bullets", (settings) -> {
        return new BulletItem(settings,4);
    },(new Item.Settings()));
    public static final Item Magazine = register("magazine", (settings) -> {
        return new MagazineItem(settings,32);
    },(new Item.Settings()));
    public static final Item Autorifle = register("autorifle", (settings) -> {
        return new GunItem(settings,32);
    },(new Item.Settings()).component(DataComponentTypes.TOOL, GunItem.createToolComponent()));
    public static final Item Chainiron_Helmet = register("chainiron_helmet", ModArmorHelmet::new, (new Item.Settings()).fireproof());
    public static final Item Chainiron_Chestplate = register("chainiron_chestplate", ModArmorChestPlate::new, (new Item.Settings()).component(DataComponentTypes.GLIDER, Unit.INSTANCE).fireproof());
    public static final Item Chainiron_Leggings = register("chainiron_leggings", ModArmorLeggings::new, (new Item.Settings()).fireproof());
    public static final Item Chainiron_Boots = register("chainiron_boots", ModArmorBoots::new, (new Item.Settings()).fireproof());
    public static final Item Armor_Battery = register("armor_battery",ArmorControlItem::new,(new Item.Settings()));

    public static final Item Chainiron_Horse_Armor = register("chainiron_horse_armor", (settings) -> {
        return new AnimalArmorItem(ModArmorMaterials.CHAINPLATE, AnimalArmorItem.Type.EQUESTRIAN, SoundEvents.ENTITY_HORSE_ARMOR, false, settings);
    }, (new Item.Settings()).maxCount(1));

    public static final Item ANTI_BOMB_BRICKS = register(ModBlocks.ANTI_BOMB_BRICKS);
    public static final Item ANTI_BOMB_PILLAR = register(ModBlocks.ANTI_BOMB_PILLAR);
    public static final Item ANTI_BOMB_GLASS = register(ModBlocks.ANTI_BOMB_GLASS);
    public static final Item ANTI_BOMB_GLASS_PANE = register(ModBlocks.ANTI_BOMB_GLASS_PANE);
    public static final Item ANTI_BOMB_STAIRS = register(ModBlocks.ANTI_BOMB_STAIRS);
    public static final Item ANTI_BOMB_WALL = register(ModBlocks.ANTI_BOMB_WALL);
    public static final Item ANTI_BOMB_SLAB = register(ModBlocks.ANTI_BOMB_SLAB);
    public static final Item ANTI_BOMB_TRAPDOOR = register(ModBlocks.ANTI_BOMB_TRAPDOOR);
    public static final Item ANTI_BOMB_DOOR = register(ModBlocks.ANTI_BOMB_DOOR, TallBlockItem::new);
    public static final Item ANTI_BOMB_LAMP = register(ModBlocks.ANTI_BOMB_LAMP);
    public static final Item ANTI_BOMB_BUTTON = register(ModBlocks.ANTI_BOMB_BUTTON);
    public static final Item ANTI_BOMB_PRESSURE_PLATE = register(ModBlocks.ANTI_BOMB_PRESSURE_PLATE);
    public static final Item ANTI_BOMB_LEVER = register(ModBlocks.ANTI_BOMB_LEVER);
    public static final Item ANTI_BOMB_LADDER = register(ModBlocks.ANTI_BOMB_LADDER);
    public static final Item ANTI_BOMB_ROD = register(ModBlocks.ANTI_BOMB_ROD);
    public static final Item ANTI_BOMB_LANTERN = register(ModBlocks.ANTI_BOMB_LANTERN);
    public static final Item STEEL_COIL = register(ModBlocks.STEEL_COIL);
    public static final Item COPPER_COIL = register(ModBlocks.COPPER_COIL);
    public static final Item GOLD_BARS = register(ModBlocks.GOLD_BARS);
    public static final Item IRON_CHEST = register(ModBlocks.IRON_CHEST, (settings) -> {
        return settings.component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
    });
    public static final Item PLANT_POT = register(ModBlocks.PLANT_POT, (settings) -> {
        return settings.component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
    });
    public static final Item CHIMERA = register(ModBlocks.CHIMERA, (settings) -> {
        return settings.component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
    });
    public static final Item MAINTAINER = register(ModBlocks.MAINTAINER);
    public static final Item GRIND_OBSIDIAN = register(ModBlocks.GRIND_OBSIDIAN);
    public static final Item MINER = register(ModBlocks.MINER);
    public static final Item BROKEN_MINER = register(ModBlocks.BROKEN_MINER);
    public static final Item DOOMED_MINER = register(ModBlocks.DOOMED_MINER);

}
