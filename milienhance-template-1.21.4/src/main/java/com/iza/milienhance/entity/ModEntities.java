package com.iza.milienhance.entity;

import com.iza.milienhance.Milienhance;
import com.iza.milienhance.entity.projectile.BulletEntity;
import com.iza.milienhance.entity.projectile.ThrownBrickEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BulletEntity> BULLET = register("bullet", EntityType.Builder.<BulletEntity>create(BulletEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    public static final EntityType<ThrownBrickEntity> THROWN_BRICK = register("thrown_brick", EntityType.Builder.<ThrownBrickEntity>create(ThrownBrickEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F).maxTrackingRange(4).trackingTickInterval(10));
    private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> type) {
        return register(keyOf(id), type);
    }
    private static <T extends Entity> EntityType<T> register(RegistryKey<EntityType<?>> key, EntityType.Builder<T> type) {
        return (EntityType)Registry.register(Registries.ENTITY_TYPE, key, type.build(key));
    }
    private static RegistryKey<EntityType<?>> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Milienhance.MOD_ID,id));
    }
    public static void initialize(){}
}
