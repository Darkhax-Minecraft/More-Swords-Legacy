package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.GreedConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;

public class EnchantmentGreed extends SwordEnchantment {

    public EnchantmentGreed(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.greed);
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {

        final GreedConfig config = MSMContent.CONFIG.enchantments.greed;

        if (user instanceof ServerPlayer player && player.level() instanceof ServerLevel serverLevel && player.getRandom().nextFloat() < config.chance.getValue(level)) {

            ExperienceOrb.award(serverLevel, target.position(), player.getRandom().nextIntBetweenInclusive(config.minExp.getValue(level), config.maxExp.getValue(level)));
        }
    }
}