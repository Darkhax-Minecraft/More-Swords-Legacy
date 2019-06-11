package net.darkhax.msmlegacy;

import java.io.File;

import org.apache.commons.lang3.EnumUtils;

import net.darkhax.msmlegacy.enchantment.EnchantmentSwordLegacy;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;

public class ConfigurationHandler {

	private Configuration config;
	private boolean allowEnchOnAllSwords = false;

    public ConfigurationHandler (File configFile) {

        config = new Configuration(configFile);
        allowEnchOnAllSwords = config.getBoolean("allowEnchOnAllSwords", Configuration.CATEGORY_GENERAL, false, "When enabled, all new enchantments will be available for all swords, including vanilla and modded swords.");
    }


    public ToolMaterial getMaterial(String name, int harvestLevel, int maxUses, int efficiency, int damage, int enchantability) {
    	
    	
    	harvestLevel = this.config.getInt("harvestLevel", name, harvestLevel, 0, 128, "The block harvesting level of the sword's material.");
    	maxUses = this.config.getInt("durability", name, maxUses, 0, Integer.MAX_VALUE, "The durability of the sword.");
    	efficiency = this.config.getInt("efficiency", name, efficiency, 0, 128, "The block efficiency of the sword's material.");
    	damage = this.config.getInt("damage", name, damage, 0, Integer.MAX_VALUE, "The damage of the sword.") - 4;
    	enchantability = this.config.getInt("enchatability", name, enchantability, 0, 512, "The enchantability of the sword.");
    	
    	return EnumHelper.addToolMaterial("MSM_LEGACY_" + name.toUpperCase(), harvestLevel, maxUses, efficiency, damage, enchantability);
    }
    
    public Enchantment getSwordEnchantment(String id, Item sword, Rarity rarity, int min, int max) {
    	
    	String category = sword.getRegistryName().getPath();
    	EnumEnchantmentType type = allowEnchOnAllSwords ? EnumEnchantmentType.WEAPON : EnumHelper.addEnchantmentType("MSM_LEGACY_" + id.toUpperCase(), item -> item == sword);
        rarity = this.getRarity(id, rarity, category, "The rarity for the " + id + " enchantment. Accepts COMMON, UNCOMMON, RARE, VERY_RARE");     
        min = config.getInt("minLevel_" + id, category, min, 1, 128, "The min level for the " + id + " enchantment.");
        max = config.getInt("maxLevel", category, max, min, 128, "The max level for the " + id + " enchantment.");
        Enchantment enchant = new EnchantmentSwordLegacy(rarity, sword, type, min, max);
        enchant.setName(id);
        return enchant;
    }
    
    private Rarity getRarity(String id, Rarity defaultRarity, String category, String comment) {
    	
    	final String rarityName = this.config.getString("rarity_" + id, category, defaultRarity.name(), comment);   	
    	Rarity rarity = EnumUtils.getEnum(Rarity.class, rarityName);
    	
    	if (rarity == null) {
    		
    		rarity = Rarity.RARE;
    	}
    	
    	return rarity;
    }
    
    private void syncConfigData () {


    	if (config.hasChanged()) {

            config.save();
        }
    }
}