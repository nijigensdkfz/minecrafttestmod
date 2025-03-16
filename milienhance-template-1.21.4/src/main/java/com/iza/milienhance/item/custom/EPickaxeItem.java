package com.iza.milienhance.item.custom;

import com.iza.milienhance.sound.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.TrailParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EPickaxeItem extends Item {
    private static final int distance = 6;
    private final boolean canTrans;
    public EPickaxeItem(Item.Settings settings, boolean canTrans) {
        super(settings);
        this.canTrans = canTrans;
    }
    public static ToolComponent createToolComponent() {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(
                List.of(
                        ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(BlockTags.PICKAXE_MINEABLE), 15.0F),
                        ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(BlockTags.SHOVEL_MINEABLE), 15.0F),
                        ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(BlockTags.AXE_MINEABLE), 15.0F),
                        ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(BlockTags.HOE_MINEABLE), 15.0F),
                        ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(new RegistryEntry[]{Blocks.COBWEB.getRegistryEntry()}),15.0F)
                ),
                1.0F, 1);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!this.canTrans){
            return ActionResult.FAIL;
        }
        Box box = user.getBoundingBox();
        Vec3d peVec = box.getMinPos();
        BlockPos pos = user.getBlockPos();
        BlockPos targetPos = pos;
        boolean founded = false;
        int n,x,z;
        for(n=0;n<=distance;n++){
            for(x=-n;x<=n;x++) {
                for (z=-n+Math.abs(x);z<=n-Math.abs(x);z++) {
                    targetPos = pos.up(n-Math.abs(x)-Math.abs(z)).east(x).south(z);
                    if (is_ore(world.getBlockState(targetPos))) {
                        founded = true;
                        break;
                    }
                    targetPos = pos.up(Math.abs(x)+Math.abs(z)-n).east(x).south(z);
                    if (is_ore(world.getBlockState(targetPos))) {
                        founded = true;
                        break;
                    }
                }
                if (founded) {
                    break;
                }
            }
            if (founded) {
                break;
            }
        }
        if(founded){
            Vec3d targetVec = new Vec3d(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            TrailParticleEffect trailParticleEffect = new TrailParticleEffect(targetVec.add(0.5,0.5,0.5), 11184878, 40);
            user.playSound(ModSoundEvents.OREALARM, 1.0F, 1.0F);
            if(world instanceof ServerWorld serverWorld){
                serverWorld.spawnParticles(trailParticleEffect,true,true,peVec.x,peVec.y+1.5,peVec.z,1,0,0,0,5);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,600,2));
            }
        }
        return ActionResult.SUCCESS;
    }

    private boolean is_ore(BlockState bs){
        if(bs.isOf(Blocks.ANCIENT_DEBRIS)
                ||bs.isOf(Blocks.NETHER_QUARTZ_ORE)
                ||bs.isOf(Blocks.NETHER_GOLD_ORE)
                ||bs.isOf(Blocks.COAL_ORE)
                ||bs.isOf(Blocks.COPPER_ORE)
                ||bs.isOf(Blocks.IRON_ORE)
                ||bs.isOf(Blocks.GOLD_ORE)
                ||bs.isOf(Blocks.DIAMOND_ORE)
                ||bs.isOf(Blocks.EMERALD_ORE)
                ||bs.isOf(Blocks.LAPIS_ORE)
                ||bs.isOf(Blocks.REDSTONE_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_COAL_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_COPPER_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_IRON_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_GOLD_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_EMERALD_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_LAPIS_ORE)
                ||bs.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)){
            return true;
        }else{
            return false;
        }
    }

    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {}

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {return true;}
}
