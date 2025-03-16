package com.iza.milienhance.block.entity;

import com.iza.milienhance.data.PlantPotData;
import com.iza.milienhance.recipe.ModRecipeType;
import com.iza.milienhance.recipe.PlantPotRecipe;
import com.iza.milienhance.screen.PlantPotScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.input.SingleStackRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PlantPotEntity extends BlockEntity implements ExtendedScreenHandlerFactory<PlantPotData>,ImplementedInventory {
    private final DefaultedList<ItemStack> inventory;
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int max_progress = 150;

    public PlantPotEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityType.PLANT_POT, pos, state);
        this.inventory = DefaultedList.ofSize(2,ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> PlantPotEntity.this.progress;
                    case 1 -> PlantPotEntity.this.max_progress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index){
                    case 0 -> PlantPotEntity.this.progress = value;
                    case 1 -> PlantPotEntity.this.max_progress = value;
                };
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public PlantPotData getScreenOpeningData(ServerPlayerEntity player) {
        return new PlantPotData(pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.plant_pot");
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PlantPotScreenHandler(syncId,playerInventory,this.propertyDelegate,this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt,this.inventory,false,registryLookup);
        nbt.putInt("pp_progress",progress);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt,this.inventory,registryLookup);
        progress = nbt.getInt("pp_progress");
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    public void tick(World world,BlockPos pos,BlockState state, PlantPotEntity plantPotEntity){
        if(!plantPotEntity.inventory.get(0).isEmpty()){
            if(isOutputSlotAvailable()){
                if(hasRecipe()){
                    if(this.progress>=this.max_progress){
                        craftItem();
                        progress=0;
                    }else{
                        progress++;
                    }
                    markDirty(world,pos,state);
                }
            }
        }else{
            progress=0;
            markDirty(world,pos,state);
        }
    }

    private void craftItem() {
        Optional<RecipeEntry<PlantPotRecipe>> recipe = getCurrentRecipe();
        int count = Math.min(getStack(OUTPUT_SLOT).getCount() + getStack(INPUT_SLOT).getCount(), 64);
        this.setStack(OUTPUT_SLOT, new ItemStack(recipe.get().value().result().getItem(), count));
    }

    private Optional<RecipeEntry<PlantPotRecipe>> getCurrentRecipe() {
        SimpleInventory inventory = new SimpleInventory(this.size());
        for (int i = 0; i < this.size(); i++) {
            inventory.setStack(i, this.getStack(i));
        }
        return getWorld().getServer().getRecipeManager().getFirstMatch(ModRecipeType.PLANT_POT,
                new SingleStackRecipeInput(inventory.getStack(INPUT_SLOT)), getWorld());
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<PlantPotRecipe>> recipe = getCurrentRecipe();
        return recipe.isPresent() && canInsertAmountIntoOutputSlot(recipe.get().value().result()) &&
                canInsertIntoOutputSlot(recipe.get().value().result().getItem());
    }

    private boolean canInsertIntoOutputSlot(Item food) {
        return this.getStack(OUTPUT_SLOT).isEmpty() ||
                this.getStack(OUTPUT_SLOT).getItem() == food;
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= this.getMaxCountPerStack();
    }

    private boolean isOutputSlotAvailable() {
        boolean isEmpty = this.getStack(OUTPUT_SLOT).isEmpty();
        boolean hasSpace = this.getStack(OUTPUT_SLOT).getCount()<this.getMaxCountPerStack();
        return isEmpty||hasSpace;
    }
}
