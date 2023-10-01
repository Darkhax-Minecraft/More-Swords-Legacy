package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.SparksConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.decoration.ArmorStand;

public class EnchantmentSparks extends SwordEnchantment {

    public EnchantmentSparks(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.sparks);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final SparksConfig config = MSMContent.CONFIG.enchantments.sparks;

        if (attacker.level() instanceof ServerLevel serverLevel && target instanceof LivingEntity living) {

            for (LivingEntity nearby : serverLevel.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(config.range.getValue(level), 0.25d, config.range.getValue(level)))) {

                if (nearby != attacker && nearby != target && !attacker.isAlliedTo(nearby) && !(nearby instanceof ArmorStand) && attacker.distanceToSqr(nearby) < 9) {

                    if (!(nearby instanceof TamableAnimal tamable && tamable.isOwnedBy(attacker)) && !(nearby instanceof OwnableEntity ownable && ownable.getOwner() == attacker)) {

                        nearby.knockback(config.knockback.getValue(level), Mth.sin(attacker.getYRot() * ((float) Math.PI / 180F)), (-Mth.cos(attacker.getYRot() * ((float) Math.PI / 180F))));
                        nearby.setSecondsOnFire(config.burnAmount.getValue(level));
                    }
                }
            }
        }
    }
}