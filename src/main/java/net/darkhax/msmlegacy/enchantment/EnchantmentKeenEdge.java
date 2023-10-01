package net.darkhax.msmlegacy.enchantment;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.item.Item;

public class EnchantmentKeenEdge extends EnchantmentSwordLegacy {

	public EnchantmentKeenEdge(Rarity rarityIn, Item item, EnumEnchantmentType typeIn, int min, int max, boolean isVanillaAllowed) {
		
		super(rarityIn, item, typeIn, min, max, isVanillaAllowed);
	}
	
	@Override
    public float calcDamageByCreature(int level, EnumCreatureAttribute creatureType) {
    	
        return level * 1.5f;
    }
}