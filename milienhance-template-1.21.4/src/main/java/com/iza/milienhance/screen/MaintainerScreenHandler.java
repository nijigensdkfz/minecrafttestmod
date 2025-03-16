package com.iza.milienhance.screen;

import com.iza.milienhance.block.ModBlocks;
import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.text.Text;
import net.minecraft.util.StringHelper;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Iterator;

public class MaintainerScreenHandler extends ForgingScreenHandler {
    private int repairItemUsage;
    private final Property levelCost;
    private boolean keepSecondSlot;

    public MaintainerScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, ScreenHandlerContext.EMPTY);
    }

    public MaintainerScreenHandler(int syncId, PlayerInventory inventory, ScreenHandlerContext context) {
        super(ModScreenHandlers.MAINTAINER_SCREEN_HANDLER, syncId, inventory, context, ForgingSlotsManager.builder()
                .input(0, 27, 47, (stack) -> {return true;})
                .input(1, 76, 47, (stack) -> {return true;})
                .output(2, 134, 47)
                .build()
        );
        this.levelCost = Property.create();
        this.keepSecondSlot = false;
        this.addProperty(this.levelCost);
    }

    protected boolean canUse(BlockState state) {
        return state.isOf(ModBlocks.MAINTAINER);
    }

    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return (player.isInCreativeMode() || player.experienceLevel >= this.levelCost.get()) && this.levelCost.get() > 0;
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        if (!player.getAbilities().creativeMode) {
            player.addExperienceLevels(-this.levelCost.get());
        }

        if (this.repairItemUsage > 0) {
            ItemStack itemStack = this.input.getStack(1);
            if (!itemStack.isEmpty() && itemStack.getCount() > this.repairItemUsage) {
                itemStack.decrement(this.repairItemUsage);
                this.input.setStack(1, itemStack);
            } else {
                this.input.setStack(1, ItemStack.EMPTY);
            }
        } else if (!this.keepSecondSlot) {
            this.input.setStack(1, ItemStack.EMPTY);
        }

        this.levelCost.set(0);
        this.input.setStack(0, ItemStack.EMPTY);
    }

    public void updateResult() {
        ItemStack itemStack = this.input.getStack(0);
        this.keepSecondSlot = false;
        this.levelCost.set(1);
        int i = 0;
        long l = 0L;
        int j = 0;
        if (!itemStack.isEmpty() && EnchantmentHelper.canHaveEnchantments(itemStack)) {
            ItemStack itemStack2 = itemStack.copy();
            ItemStack itemStack3 = this.input.getStack(1);
            ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(EnchantmentHelper.getEnchantments(itemStack2));
            l += (long)(Integer)itemStack.getOrDefault(DataComponentTypes.REPAIR_COST, 0) + (long)(Integer)itemStack3.getOrDefault(DataComponentTypes.REPAIR_COST, 0);
            this.repairItemUsage = 0;
            int k;
            if (!itemStack3.isEmpty()) {
                boolean bl = itemStack3.contains(DataComponentTypes.STORED_ENCHANTMENTS);
                int m;
                int n;
                if (itemStack2.isDamageable() && itemStack.canRepairWith(itemStack3)) {
                    k = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                    if (k <= 0) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.levelCost.set(0);
                        return;
                    }

                    for(m = 0; k > 0 && m < itemStack3.getCount(); ++m) {
                        n = itemStack2.getDamage() - k;
                        itemStack2.setDamage(n);
                        ++i;
                        k = Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
                    }

                    this.repairItemUsage = m;
                } else {
                    if (!bl && (!itemStack2.isOf(itemStack3.getItem()) || !itemStack2.isDamageable())) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.levelCost.set(0);
                        return;
                    }

                    if (itemStack2.isDamageable() && !bl) {
                        k = itemStack.getMaxDamage() - itemStack.getDamage();
                        m = itemStack3.getMaxDamage() - itemStack3.getDamage();
                        n = m + itemStack2.getMaxDamage() * 12 / 100;
                        int o = k + n;
                        int p = itemStack2.getMaxDamage() - o;
                        if (p < 0) {
                            p = 0;
                        }

                        if (p < itemStack2.getDamage()) {
                            itemStack2.setDamage(p);
                            i += 2;
                        }
                    }

                    ItemEnchantmentsComponent itemEnchantmentsComponent = EnchantmentHelper.getEnchantments(itemStack3);
                    boolean bl2 = false;
                    boolean bl3 = false;
                    Iterator var26 = itemEnchantmentsComponent.getEnchantmentEntries().iterator();

                    while(var26.hasNext()) {
                        Object2IntMap.Entry<RegistryEntry<Enchantment>> entry = (Object2IntMap.Entry)var26.next();
                        RegistryEntry<Enchantment> registryEntry = (RegistryEntry)entry.getKey();
                        int q = builder.getLevel(registryEntry);
                        int r = entry.getIntValue();
                        r = q == r ? r + 1 : Math.max(r, q);
                        Enchantment enchantment = (Enchantment)registryEntry.value();
                        boolean bl4 = enchantment.isAcceptableItem(itemStack);
                        if (this.player.getAbilities().creativeMode || itemStack.isOf(Items.ENCHANTED_BOOK)) {
                            bl4 = true;
                        }

                        Iterator var20 = builder.getEnchantments().iterator();

                        while(var20.hasNext()) {
                            RegistryEntry<Enchantment> registryEntry2 = (RegistryEntry)var20.next();
                            if (!registryEntry2.equals(registryEntry) && !Enchantment.canBeCombined(registryEntry, registryEntry2)) {
                                bl4 = false;
                                ++i;
                            }
                        }

                        if (!bl4) {
                            bl3 = true;
                        } else {
                            bl2 = true;
                            if (r > enchantment.getMaxLevel()) {
                                r = enchantment.getMaxLevel();
                            }

                            builder.set(registryEntry, r);
                            int s = enchantment.getAnvilCost();
                            if (bl) {
                                s = Math.max(1, s / 2);
                            }

                            i += s * r;
                            if (itemStack.getCount() > 1) {
                                i = 40;
                            }
                        }
                    }

                    if (bl3 && !bl2) {
                        this.output.setStack(0, ItemStack.EMPTY);
                        this.levelCost.set(0);
                        return;
                    }
                }
            }

            int t = i <= 0 ? 0 : (int)MathHelper.clamp(l + (long)i, 0L, 2147483647L);
            this.levelCost.set(t);
            if (i <= 0) {
                itemStack2 = ItemStack.EMPTY;
            }

            if (j == i && j > 0) {
                if (this.levelCost.get() >= 40) {
                    this.levelCost.set(39);
                }

                this.keepSecondSlot = true;
            }

            if (this.levelCost.get() >= 40 && !this.player.getAbilities().creativeMode) {
                itemStack2 = ItemStack.EMPTY;
            }

            if (!itemStack2.isEmpty()) {
                EnchantmentHelper.set(itemStack2, builder.build());
            }

            this.output.setStack(0, itemStack2);
            this.sendContentUpdates();
        } else {
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
        }
    }
}
