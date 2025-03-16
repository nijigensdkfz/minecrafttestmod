package com.iza.milienhance.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class GenesisItem extends Item {
    public GenesisItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState bs = context.getWorld().getBlockState(pos);
        PlayerEntity pe = context.getPlayer();
        if(pe.getOffHandStack().isOf(Items.SKELETON_SPAWN_EGG)){
            if(bs.isOf(Blocks.COAL_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.WITHER_SKELETON_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.POWDER_SNOW)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.STRAY_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.MUD)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.BOGGED_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.GRASS_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.SKELETON_HORSE_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }else if(pe.getOffHandStack().isOf(Items.PIG_SPAWN_EGG)){
            if(bs.isOf(Blocks.NETHERRACK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.HOGLIN_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.SOUL_SAND)||bs.isOf(Blocks.SOUL_SOIL)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.PIGLIN_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }else if(pe.getOffHandStack().isOf(Items.ZOMBIE_SPAWN_EGG)){
            if(bs.isOf(Blocks.SEAGRASS)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.DROWNED_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.SAND)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.HUSK_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.EMERALD_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.ZOMBIE_VILLAGER_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.GRASS_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.ZOMBIE_HORSE_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }else if(pe.getOffHandStack().isOf(Items.COW_SPAWN_EGG)){
            if(bs.isOf(Blocks.RED_MUSHROOM)||bs.isOf(Blocks.BROWN_MUSHROOM)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.MOOSHROOM_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }else if(pe.getOffHandStack().isOf(Items.SQUID_SPAWN_EGG)){
            if(bs.isOf(Blocks.GLOWSTONE)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.GLOW_SQUID_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }else{
            if(bs.isOf(Blocks.AMETHYST_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.ALLAY_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.BEEHIVE)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.BEE_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.SAND)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.CAMEL_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.SEAGRASS)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.TURTLE_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.SEA_LANTERN)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.ELDER_GUARDIAN_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isIn(BlockTags.WOOL)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.SHEEP_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.BONE_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.SKELETON_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.MAGMA_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.MAGMA_CUBE_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.BAMBOO)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.PANDA_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.EMERALD_BLOCK)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.VILLAGER_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.END_STONE)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.ENDERMAN_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
            if(bs.isOf(Blocks.ICE)){
                context.getWorld().spawnEntity(new ItemEntity(context.getWorld(),pe.getX(),pe.getY(),pe.getZ(),new ItemStack(Items.POLAR_BEAR_SPAWN_EGG)));
                context.getStack().decrementUnlessCreative(1,pe);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity.getType()==EntityType.ARMADILLO){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ARMADILLO_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.AXOLOTL){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.AXOLOTL_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.BAT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.BAT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.BLAZE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.BLAZE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.BREEZE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.BREEZE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.CAT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.CAT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.CAVE_SPIDER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.CAVE_SPIDER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.CHICKEN){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.CHICKEN_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.COD){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.COD_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.COW){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.COW_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.CREEPER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.CREEPER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.DOLPHIN){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.DOLPHIN_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.DONKEY){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.DONKEY_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.EVOKER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.EVOKER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.ENDERMITE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ENDERMITE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.FOX){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.FOX_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.FROG){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.FROG_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.GHAST){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.GHAST_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.GUARDIAN){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.GUARDIAN_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.GOAT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.GOAT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.HORSE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.HORSE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.LLAMA){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.LLAMA_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.MULE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.MULE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.OCELOT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.OCELOT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PARROT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PARROT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PIG){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PIG_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PIGLIN_BRUTE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PIGLIN_BRUTE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PUFFERFISH){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PUFFERFISH_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PHANTOM){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PHANTOM_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.PILLAGER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.PILLAGER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.RABBIT){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.RABBIT_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.RAVAGER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.RAVAGER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SALMON){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SALMON_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SHULKER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SHULKER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SILVERFISH){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SILVERFISH_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SKELETON_HORSE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SKELETON_HORSE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SLIME){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SLIME_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SNIFFER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SNIFFER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SPIDER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SPIDER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.SQUID){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.SQUID_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.STRIDER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.STRIDER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.TADPOLE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.TADPOLE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.TROPICAL_FISH){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.TROPICAL_FISH_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.WANDERING_TRADER){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.WANDERING_TRADER_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.WITCH){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.WITCH_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.WOLF){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.WOLF_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.ZOGLIN){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ZOGLIN_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.ZOMBIE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ZOMBIE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.ZOMBIE_HORSE){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ZOMBIE_HORSE_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.ZOMBIFIED_PIGLIN){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.ZOMBIFIED_PIGLIN_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.VEX){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.VEX_SPAWN_EGG)));
        }
        if(entity.getType()==EntityType.VINDICATOR){
            user.getWorld().spawnEntity(new ItemEntity(user.getWorld(),entity.getX(),entity.getY(),entity.getZ(),new ItemStack(Items.VINDICATOR_SPAWN_EGG)));
        }
        if(entity instanceof LivingEntity){
            stack.decrementUnlessCreative(1,user);
        }
        return ActionResult.SUCCESS;
    }
}
