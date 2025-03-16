package com.iza.milienhance.block.entity;

import com.iza.milienhance.Milienhance;
import com.iza.milienhance.block.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntityType<T extends BlockEntity> implements FabricBlockEntityType {
    public static final BlockEntityType<IronChestEntity> IRON_CHEST;
    public static final BlockEntityType<PlantPotEntity> PLANT_POT;
    public static final BlockEntityType<ChimeraEntity> CHIMERA;
    public static final BlockEntityType<MinerEntity> MINER;
    public static <T extends BlockEntityType<?>> T create(String path, T blockEntityType) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Milienhance.MOD_ID, path), blockEntityType);
    }
    public static void initialize(){}
    static {
        IRON_CHEST = create("iron_chest", FabricBlockEntityTypeBuilder.create(IronChestEntity::new,ModBlocks.IRON_CHEST).build());
        PLANT_POT = create("plant_pot", FabricBlockEntityTypeBuilder.create(PlantPotEntity::new,ModBlocks.PLANT_POT).build());
        CHIMERA = create("chimera", FabricBlockEntityTypeBuilder.create(ChimeraEntity::new,ModBlocks.CHIMERA).build());
        MINER = create("miner", FabricBlockEntityTypeBuilder.create(MinerEntity::new,ModBlocks.MINER).build());
    }
}
