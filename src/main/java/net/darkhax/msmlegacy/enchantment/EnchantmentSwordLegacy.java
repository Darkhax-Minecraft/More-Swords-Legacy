package net.darkhax.msmlegacy.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;

public class EnchantmentSwordLegacy extends Enchantment {

    private static final EntityEquipmentSlot[] slots = new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND };

    private final Item intendedItem;
    private final int minLevel;
    private final int maxLevel;

    public EnchantmentSwordLegacy (Rarity rarityIn, Item item, EnumEnchantmentType typeIn, int min, int max) {

        super(rarityIn, typeIn, slots);
        this.intendedItem = item;
        this.minLevel = min;
        this.maxLevel = max;
    }

    @Override
    public int getMinLevel () {

        return this.minLevel;
    }

    @Override
    public int getMaxLevel () {

        return this.maxLevel;
    }
}