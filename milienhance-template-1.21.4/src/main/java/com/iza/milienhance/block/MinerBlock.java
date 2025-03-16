package com.iza.milienhance.block;

import com.iza.milienhance.block.entity.MinerEntity;
import com.iza.milienhance.block.entity.ModBlockEntityType;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MinerBlock extends BlockWithEntity {
    public static final MapCodec<MinerBlock> CODEC = createCodec(MinerBlock::new);

    protected MinerBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MinerEntity(pos,state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public  <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if(world instanceof ServerWorld serverWorld){
            return validateTicker(type, ModBlockEntityType.MINER,(world1, pos, state1, blockEntity)->{
                blockEntity.tick(serverWorld,pos,state1, blockEntity);
            });
        }else{
            return null;
        }
    }
}
