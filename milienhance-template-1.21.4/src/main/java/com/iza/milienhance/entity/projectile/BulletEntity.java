package com.iza.milienhance.entity.projectile;

import com.google.common.collect.Lists;
import com.iza.milienhance.entity.ModEntities;
import com.iza.milienhance.item.ModItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class BulletEntity extends ThrownItemEntity {
    private int life_ticks;

    @Override
    protected Item getDefaultItem() {
        return ModItems.Bullet;
    }

    public BulletEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.life_ticks = 0;
    }

    public BulletEntity(World world, LivingEntity owner, ItemStack stack) {
        super(ModEntities.BULLET, owner, world, stack); // null will be changed later
        this.life_ticks = 0;
    }

    public BulletEntity(World world, double x, double y, double z, ItemStack stack) {
        super(ModEntities.BULLET, x, y, z, world, stack); // null will be changed later
        this.life_ticks = 0;
    }
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        float i = 50.0F;
        entity.setOnFireFor(5.0F);
        entity.serverDamage(this.getDamageSources().playerAttack((PlayerEntity) this.getOwner()), (float)i); // deals damage
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.discard();
        }
    }
    @Override
    public void tick() {
        super.tick();
        this.life_ticks++;
        if(this.life_ticks>=40){
            this.discard();
        }
    }
    @Override
    protected double getGravity() {
        return 0.0;
    }
    @Override
    protected ProjectileDeflection hitOrDeflect(HitResult hitResult) {
        this.onCollision(hitResult);
        return ProjectileDeflection.NONE;
    }
}
