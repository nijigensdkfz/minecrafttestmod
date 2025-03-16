package com.iza.milienhance.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.event.GameEvent;

import java.util.Objects;

public class GenesisRodItem extends Item {
    public GenesisRodItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().isClient){
            return ActionResult.SUCCESS;
        } else {
            PlayerEntity pe = context.getPlayer();
            ItemStack itemStack = pe.getOffHandStack();
            BlockPos blockPos = context.getBlockPos();
            BlockState blockState = context.getWorld().getBlockState(blockPos);
            if(blockState.isOf(Blocks.BEDROCK)){
                context.getWorld().setBlockState(blockPos,Blocks.AIR.getDefaultState());
            }
            if(itemStack.getItem() instanceof SpawnEggItem spawnEggItem){
                Direction direction = context.getSide();
                BlockPos blockPos2;
                if (blockState.getCollisionShape(context.getWorld(), blockPos).isEmpty()) {
                    blockPos2 = blockPos;
                } else {
                    blockPos2 = blockPos.offset(direction);
                }
                EntityType entityType = spawnEggItem.getEntityType(context.getWorld().getRegistryManager(), itemStack);
                if (entityType.spawnFromItemStack((ServerWorld)context.getWorld(), itemStack, context.getPlayer(), blockPos2, SpawnReason.SPAWN_ITEM_USE, true, !Objects.equals(blockPos, blockPos2) && direction == Direction.UP) != null) {
                    context.getWorld().emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.FAIL;
        }
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof HorseEntity horse){
            EntityAttributeInstance var10000 = horse.getAttributeInstance(EntityAttributes.MAX_HEALTH);
            var10000.setBaseValue(30.0);
            var10000 = horse.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);
            var10000.setBaseValue(0.3375);
            var10000 = horse.getAttributeInstance(EntityAttributes.JUMP_STRENGTH);
            var10000.setBaseValue(1.0);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
