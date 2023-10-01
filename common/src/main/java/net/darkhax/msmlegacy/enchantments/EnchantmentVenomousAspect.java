package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.AbsorbConfig;
import net.darkhax.msmlegacy.config.enchantment.VenomousAspectConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentVenomousAspect extends SwordEnchantment {

    public EnchantmentVenomousAspect(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.venomousAspect);
    }

    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final VenomousAspectConfig config = MSMContent.CONFIG.enchantments.venomousAspect;

        if (attacker.level() instanceof ServerLevel && target instanceof LivingEntity living) {

            config.effect.applyEffect(living, level);
        }
    }
}