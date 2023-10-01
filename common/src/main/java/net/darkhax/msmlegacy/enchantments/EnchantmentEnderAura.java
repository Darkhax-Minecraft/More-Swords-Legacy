package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.EnderAuraConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EnchantmentEnderAura extends SwordEnchantment {

    public EnchantmentEnderAura(String type) {
        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.enderaura);
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level) {

        super.doPostHurt(user, attacker, level);

        final EnderAuraConfig config = MSMContent.CONFIG.enchantments.enderaura;

        if (user.level() instanceof ServerLevel serverLevel && user.getRandom().nextFloat() < config.chance.getValue(level)) {

            final double posX = user.getX();
            final double posY = user.getY();
            final double posZ = user.getZ();

            for (int attempts = 0; attempts < 16; attempts++) {

                double randomX = posX + (user.getRandom().nextDouble() - 0.5) * config.warpRange.getValue(level);
                double randomY = Mth.clamp(posY + (double) (user.getRandom().nextInt(config.warpRange.getValue(level)) - 8), serverLevel.getMinBuildHeight(), (serverLevel.getMinBuildHeight() + serverLevel.getLogicalHeight() - 1));
                double randomZ = posZ + (user.getRandom().nextDouble() - 0.5) * config.warpRange.getValue(level);

                if (user.isPassenger()) {

                    user.stopRiding();
                }

                final Vec3 oldPosition = user.position();

                if (user.randomTeleport(randomX, randomY, randomZ, true)) {

                    serverLevel.gameEvent(GameEvent.TELEPORT, oldPosition, GameEvent.Context.of(user));
                    serverLevel.playSound(null, posX, posY, posZ, SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    user.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
                    break;
                }
            }
        }
    }
}
