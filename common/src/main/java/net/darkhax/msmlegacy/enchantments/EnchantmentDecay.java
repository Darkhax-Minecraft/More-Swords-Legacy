package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.DecayConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentDecay extends SwordEnchantment {

    public EnchantmentDecay(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.ignite);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final DecayConfig config = MSMContent.CONFIG.enchantments.decay;

        if (attacker.level() instanceof ServerLevel serverLevel && target instanceof LivingEntity) {

            config.wither.applyEffect(target, level);
            config.hunger.applyEffect(target, level);
        }
    }
}