package com.iza.milienhance.block.entity;

import com.iza.milienhance.block.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MinerEntity extends BlockEntity {
    private int chunkBaseX;
    private int chunkBaseZ;
    private int currentY;
    private int minY;
    private int chunkOffsetX;
    private int chunkOffsetZ;
    private boolean broken;

    public MinerEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.MINER, pos, state);
        getChunkBase();
    }

    public void setWorld(World world) {
        super.setWorld(world);
        minY = world.getBottomY();
        System.out.println(minY);
    }

    public void getChunkBase() {
        BlockPos pos = this.getPos();
        this.chunkBaseX = (pos.getX() > 0 ? pos.getX() / 16 : pos.getX() / 16 - 1) * 16;
        this.chunkBaseZ = (pos.getZ() > 0 ? pos.getZ() / 16 : pos.getZ() / 16 - 1) * 16;
        this.chunkOffsetX = 0;
        this.chunkOffsetZ = 0;
        currentY = pos.getY()-1;
        this.broken = false;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        nbt.putInt("basex",chunkBaseX);
        nbt.putInt("basez",chunkBaseZ);
        nbt.putInt("cy",currentY);
        nbt.putInt("my",minY);
        nbt.putInt("offsetx",chunkOffsetX);
        nbt.putInt("offsetz",chunkOffsetZ);
        nbt.putBoolean("broken",broken);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        chunkBaseX = nbt.getInt("basex");
        chunkBaseZ = nbt.getInt("basez");
        currentY = nbt.getInt("cy");
        minY = nbt.getInt("my");
        chunkOffsetX = nbt.getInt("offsetx");
        chunkOffsetZ = nbt.getInt("offsetz");
        broken = nbt.getBoolean("broken");
    }

    public void tick(ServerWorld world, BlockPos pos, BlockState state, MinerEntity miner) {
        if(currentY>=this.minY){
            this.mine(new BlockPos(chunkBaseX+chunkOffsetX,currentY,chunkBaseZ+chunkOffsetZ));
            chunkOffsetX++;
            if(chunkOffsetX>=16){
                chunkOffsetZ++;
                chunkOffsetX=0;
                if(chunkOffsetZ>=16){
                    chunkOffsetZ=0;
                    currentY--;
                }
            }
        }else{
            if(this.broken){
                world.setBlockState(this.getPos(), ModBlocks.DOOMED_MINER.getDefaultState());
            }else{
                world.setBlockState(this.getPos(),ModBlocks.BROKEN_MINER.getDefaultState());
            }
        }
    }

    private void mine(BlockPos pos){
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_REDSTONE_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.REDSTONE,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_LAPIS_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.LAPIS_LAZULI,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_GOLD_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_GOLD,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_EMERALD_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.EMERALD,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_DIAMOND_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.DIAMOND,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_IRON_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_IRON,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_COPPER_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_COPPER,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_COAL_ORE)){
            world.setBlockState(pos,Blocks.DEEPSLATE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.COAL,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.REDSTONE_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.REDSTONE,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.LAPIS_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.LAPIS_LAZULI,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.GOLD_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_GOLD,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.EMERALD_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.EMERALD,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.DIAMOND_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.DIAMOND,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.IRON_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_IRON,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.COPPER_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.RAW_COPPER,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.COAL_ORE)){
            world.setBlockState(pos,Blocks.STONE.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.COAL,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.NETHER_QUARTZ_ORE)){
            world.setBlockState(pos,Blocks.NETHERRACK.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.QUARTZ,1)));
        }
        if(world.getBlockState(pos).isOf(Blocks.NETHER_GOLD_ORE)){
            world.setBlockState(pos,Blocks.NETHERRACK.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.GOLD_NUGGET,4)));
        }
        if(world.getBlockState(pos).isOf(Blocks.ANCIENT_DEBRIS)){
            world.setBlockState(pos,Blocks.NETHERRACK.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.ANCIENT_DEBRIS,1)));
            this.broken = true;
        }
        if(world.getBlockState(pos).isOf(Blocks.BUDDING_AMETHYST)){
            world.setBlockState(pos,Blocks.AMETHYST_BLOCK.getDefaultState());
            world.spawnEntity(new ItemEntity(this.world,this.pos.getX(),this.pos.getY()+1,this.pos.getZ(),new ItemStack(Items.BUDDING_AMETHYST,1)));
            this.broken = true;
        }
    }
}