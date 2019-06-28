package net.darkhax.msmlegacy.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;

public class EnchantmentSwordLegacy extends Enchantment {

    private static final EntityEquipmentSlot[] slots = new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND };

    private final Item intendedItem;
    private final int minLevel;
    private final int maxLevel;
    private final boolean isVanillaAllowed;

    public EnchantmentSwordLegacy (Rarity rarityIn, Item item, EnumEnchantmentType typeIn, int min, int max, boolean isVanillaAllowed) {

        super(rarityIn, typeIn, slots);
        this.intendedItem = item;
        this.minLevel = min;
        this.maxLevel = max;
        this.isVanillaAllowed = isVanillaAllowed;
    }

    @Override
    public int getMinLevel () {

        return this.minLevel;
    }

    @Override
    public int getMaxLevel () {

        return this.maxLevel;
    }
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {

        return (!(stack.getItem() instanceof ItemBook) || isVanillaAllowed) && super.canApplyAtEnchantingTable(stack);
    }
}