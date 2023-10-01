package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.WisdomConfig;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentWisdom extends SwordEnchantment {

    public EnchantmentWisdom(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.wisdom);
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {

        final WisdomConfig config = MSMContent.CONFIG.enchantments.wisdom;

        if (user instanceof ServerPlayer player && target.isAlive()) {

            float bonusDamage = Math.min(config.damageCap.getValue(level), Math.max(0, (player.experienceLevel / config.threshold.getValue(level)) * config.damage.getValue(level)));

            if (bonusDamage > 0.0) {

                target.hurt(user.level().damageSources().magic(), bonusDamage);
            }
        }
    }
}