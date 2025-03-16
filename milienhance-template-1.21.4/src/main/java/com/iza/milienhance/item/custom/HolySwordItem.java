package com.iza.milienhance.item.custom;

import com.iza.milienhance.sound.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.ToolComponent.Rule;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class HolySwordItem extends Item {
    public HolySwordItem(Item.Settings settings) {
        super(settings);
    }

    public static ToolComponent createToolComponent() {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(List.of(
                Rule.ofAlwaysDropping(RegistryEntryList.of(new RegistryEntry[]{Blocks.COBWEB.getRegistryEntry()}), 15.0F),
                Rule.of(registryEntryLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
        ), 1.0F, 2);
    }

    public static AttributeModifiersComponent createAttributeModifiers(int baseAttackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder().add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, baseAttackDamage, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }

    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.removeStatusEffect(StatusEffects.WEAKNESS);
            user.removeStatusEffect(StatusEffects.MINING_FATIGUE);
            user.removeStatusEffect(StatusEffects.NAUSEA);
            user.removeStatusEffect(StatusEffects.BLINDNESS);
            user.removeStatusEffect(StatusEffects.HUNGER);
            user.removeStatusEffect(StatusEffects.SLOWNESS);
            user.removeStatusEffect(StatusEffects.POISON);
            user.removeStatusEffect(StatusEffects.LEVITATION);
            user.removeStatusEffect(StatusEffects.WITHER);
            user.removeStatusEffect(StatusEffects.UNLUCK);
            user.removeStatusEffect(StatusEffects.DARKNESS);
            user.removeStatusEffect(StatusEffects.OOZING);
            user.removeStatusEffect(StatusEffects.WEAVING);
            user.removeStatusEffect(StatusEffects.WIND_CHARGED);
            user.removeStatusEffect(StatusEffects.INFESTED);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,1200,4));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION,1200,2));
        }
        user.getStackInHand(hand).damage(16,user,EquipmentSlot.MAINHAND);
        user.playSound(ModSoundEvents.POWERUP, 1.0F, 0.8F);
        user.getItemCooldownManager().set(user.getStackInHand(Hand.MAIN_HAND),100);
        return ActionResult.SUCCESS;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,200,4,true,false,true));
        target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS,200,2,true,false,true));
        if(!attacker.isOnGround()) {
            attacker.getWorld().createExplosion(
                    attacker,
                    Explosion.createDamageSource(attacker.getWorld(), attacker),
                    new AdvancedExplosionBehavior(false, true, Optional.of(1.22F), Registries.BLOCK.getOptional(BlockTags.BLOCKS_WIND_CHARGE_EXPLOSIONS).map(Function.identity())),
                    target.getX(), target.getY(), target.getZ(),
                    4.5F, false, World.ExplosionSourceType.MOB
            );
            attacker.onLanding();
        }
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }
}
