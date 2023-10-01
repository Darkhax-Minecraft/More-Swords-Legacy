package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.ConsumingShadowsConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.decoration.ArmorStand;

public class EnchantmentConsumingShadows extends SwordEnchantment {

    public EnchantmentConsumingShadows(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.consumingshadows);
    }

    @Override
    public void doPostAttack(LivingEntity attacker, Entity target, int level) {

        final ConsumingShadowsConfig config = MSMContent.CONFIG.enchantments.consumingshadows;

        if (attacker.level() instanceof ServerLevel serverLevel && target instanceof LivingEntity living) {

            for (LivingEntity nearby : serverLevel.getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(config.range.getValue(level), 0.25d, config.range.getValue(level)))) {

                if (nearby != attacker && nearby != target && !attacker.isAlliedTo(nearby) && !(nearby instanceof ArmorStand) && attacker.distanceToSqr(nearby) < 9) {

                    if (!(nearby instanceof TamableAnimal tamable && tamable.isOwnedBy(attacker)) && !(nearby instanceof OwnableEntity ownable && ownable.getOwner() == attacker)) {

                        config.blindness.applyEffect(nearby, level);
                        config.wither.applyEffect(nearby, level);
                    }
                }
            }
        }
    }
}
