package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.IgniteConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentIgnite extends SwordEnchantment {

    public EnchantmentIgnite(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.ignite);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final IgniteConfig config = MSMContent.CONFIG.enchantments.ignite;

        if (attacker.level() instanceof ServerLevel serverLevel && target instanceof LivingEntity living && !living.fireImmune() && attacker.getRandom().nextFloat() < config.chance.getValue(level)) {

            serverLevel.levelEvent(2004, target.getOnPos(), 0);
            target.hurt(serverLevel.damageSources().inFire(), config.fireDamage.getValue(level));

            if (config.burnTime.getValue(level) > 0) {

                target.setSecondsOnFire(config.burnTime.getValue(level));
            }
        }
    }
}