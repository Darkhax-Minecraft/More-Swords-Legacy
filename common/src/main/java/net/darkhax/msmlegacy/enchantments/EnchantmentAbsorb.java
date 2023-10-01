package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.AbsorbConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentAbsorb extends SwordEnchantment {

    public EnchantmentAbsorb(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.absorb);
    }

    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final AbsorbConfig config = MSMContent.CONFIG.enchantments.absorb;

        if (attacker.level() instanceof ServerLevel) {

            config.effect.applyEffect(attacker, level);
        }
    }
}