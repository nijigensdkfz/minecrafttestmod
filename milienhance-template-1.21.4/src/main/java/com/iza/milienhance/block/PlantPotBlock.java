package com.iza.milienhance.block;

import com.iza.milienhance.block.entity.ModBlockEntityType;
import com.iza.milienhance.block.entity.PlantPotEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PlantPotBlock extends BlockWithEntity {
    public static final MapCodec<PlantPotBlock> CODEC = createCodec(PlantPotBlock::new);

    protected PlantPotBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public  BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new PlantPotEntity(pos,state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state){
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world,BlockPos pos,BlockState newstate,boolean moved){
        if(state.getBlock()!= newstate.getBlock()){
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof PlantPotEntity){
                ItemScatterer.spawn(world,pos,(PlantPotEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state,world,pos,newstate,moved);
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit){
        if(!world.isClient()){
            NamedScreenHandlerFactory screenHandlerFactory = (PlantPotEntity)world.getBlockEntity(pos);
            if(screenHandlerFactory!=null){
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }
    public  <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if(world instanceof ServerWorld serverWorld){
            return validateTicker(type, ModBlockEntityType.PLANT_POT,(world1, pos, state1, blockEntity)->{
                blockEntity.tick(serverWorld,pos,state1, blockEntity);
            });
        }else{
            return null;
        }
    }
}
