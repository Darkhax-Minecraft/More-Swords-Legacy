package net.darkhax.msmlegacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CreativeTabMSMLegacy extends CreativeTabs {

    private ItemStack iconStack;

    public CreativeTabMSMLegacy () {

        super("msmlegacy");
    }

    @Override
    public ItemStack createIcon () {

        if (this.iconStack == null) {

            final Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("msmlegacy", "adminium_ark"));
            this.iconStack = new ItemStack(item);
        }

        return this.iconStack;
    }
}