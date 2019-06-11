package net.darkhax.msmlegacy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry.ItemStackHolder;

public class CreativeTabMSMLegacy extends CreativeTabs {

	private ItemStack iconStack;
	
	public CreativeTabMSMLegacy() {
		
		super("msmlegacy");
	}

	@Override
	public ItemStack createIcon() {
		
		if (iconStack == null) {
			
			Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("msmlegacy", "adminium_ark"));
			iconStack = new ItemStack(item);
		}
		
		return this.iconStack;
	}
}