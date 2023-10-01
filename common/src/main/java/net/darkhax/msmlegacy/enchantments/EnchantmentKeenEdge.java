package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.minecraft.world.entity.MobType;

public class EnchantmentKeenEdge extends SwordEnchantment {

    public EnchantmentKeenEdge(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.keenEdge);
    }

    @Override
    public float getDamageBonus(int level, MobType type) {

        return MSMContent.CONFIG.enchantments.keenEdge.damage.getValue(level);
    }
}
