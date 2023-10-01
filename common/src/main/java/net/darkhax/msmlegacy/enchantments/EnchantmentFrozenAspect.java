package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.FrozenAspectConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentFrozenAspect extends SwordEnchantment {

    public EnchantmentFrozenAspect(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.frozenAspect);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final FrozenAspectConfig config = MSMContent.CONFIG.enchantments.frozenAspect;

        if (attacker.level() instanceof ServerLevel && target instanceof LivingEntity living) {

            config.effect.applyEffect(target, level);

            if (attacker.getRandom().nextFloat() < config.damageChance.getValue(level)) {

                living.hurt(attacker.level().damageSources().freeze(), config.frostDamage.getValue(level));
            }
        }
    }
}