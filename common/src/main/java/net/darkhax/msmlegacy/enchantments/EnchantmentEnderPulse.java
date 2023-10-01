package net.darkhax.msmlegacy.enchantments;

import net.darkhax.bookshelf.api.util.PlayerHelper;
import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.EnderPulseConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class EnchantmentEnderPulse extends SwordEnchantment {

    public EnchantmentEnderPulse(String type) {

        super(Rarity.COMMON, type, MSMContent.CONFIG.enchantments.enderPulse);
    }

    @Override
    public InteractionResultHolder<ItemStack> onItemUsed(Level worldLevel, Player player, InteractionHand hand, int level) {

        final ItemStack heldStack = player.getItemInHand(hand);
        final int cooldown = PlayerHelper.getRemainingCooldownTicks(player, heldStack.getItem());

        if (cooldown <= 0) {

            final EnderPulseConfig config = MSMContent.CONFIG.enchantments.enderPulse;
            final HitResult hitResult = player.pick(config.distance.getValue(level), 0f, false);

            if (hitResult instanceof BlockHitResult blockInfo) {

                final BlockPos warpPos = blockInfo.getBlockPos().relative(blockInfo.getDirection());

                if (worldLevel.getBlockState(warpPos).isAir() && worldLevel.getBlockState(warpPos.above()).isAir()) {

                    player.teleportTo(warpPos.getX() + 0.5f, warpPos.getY() + 0.01f, warpPos.getZ() + 0.5f);
                    player.getCooldowns().addCooldown(heldStack.getItem(), config.cooldownTime.getValue(level));
                    return InteractionResultHolder.success(heldStack);
                }

                else {
                    player.sendSystemMessage(Component.translatable("msmlegacy.messages.enchantment.no_space"));
                }
            }
        }

        else {

            player.sendSystemMessage(Component.translatable("msmlegacy.messages.enchantment.cooldown", this.getName(), Math.ceil(cooldown / 20d)));
        }

        return null;
    }
}
