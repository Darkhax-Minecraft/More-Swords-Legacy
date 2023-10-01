package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.FeastConfig;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class EnchantmentFeast extends SwordEnchantment {

    public EnchantmentFeast(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.feast);
    }

    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final FeastConfig config = MSMContent.CONFIG.enchantments.feast;
        final ItemStack weaponStack = attacker.getMainHandItem();

        if (EnchantmentHelper.getItemEnchantmentLevel(this, weaponStack) > 0 && attacker.getRandom().nextFloat() < config.repairChance.getValue(level)) {

            weaponStack.setDamageValue(Math.max(0, weaponStack.getDamageValue() - config.repairAmount.getValue(level)));
            target.playSound(SoundEvents.WITCH_DRINK, 1f, 0.5f);
        }
    }
}
