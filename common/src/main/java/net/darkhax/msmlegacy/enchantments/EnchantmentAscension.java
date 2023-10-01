package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.AscensionConfig;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class EnchantmentAscension extends SwordEnchantment {

    public EnchantmentAscension(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.ascension);
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity target, int level) {

        final AscensionConfig config = MSMContent.CONFIG.enchantments.ascension;

        if (target instanceof LivingEntity living && !living.level().isClientSide && user.getRandom().nextFloat() < config.chance.getValue(level)) {

            final Vec3 oldMovement = living.getDeltaMovement();
            living.setDeltaMovement(oldMovement.x, config.force.getValue(level), oldMovement.z);
        }
    }
}