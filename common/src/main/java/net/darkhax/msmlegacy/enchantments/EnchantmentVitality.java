package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.VitalityConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import javax.annotation.Nullable;

public class EnchantmentVitality extends SwordEnchantment {

    public EnchantmentVitality(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.vitality);
    }

    @Nullable
    public InteractionResultHolder<ItemStack> onItemUsed(Level worldLevel, Player player, InteractionHand hand, int level) {

        final VitalityConfig config = MSMContent.CONFIG.enchantments.vitality;
        final ItemStack stack = player.getItemInHand(hand);
        final int remainingDurability = stack.getMaxDamage() - stack.getDamageValue();
        final int durabilityCost = (int) (stack.getMaxDamage() * config.durabilityCost.getValue(level));

        if (remainingDurability > durabilityCost) {

            stack.setDamageValue(stack.getDamageValue() + durabilityCost);

            config.absorption.applyEffect(player, level);
            config.resistance.applyEffect(player, level);
            config.regeneration.applyEffect(player, level);
            config.fireResistance.applyEffect(player, level);
            player.heal(config.healAmount.getValue(level));

            worldLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.WARDEN_HEARTBEAT, player.getSoundSource(), 1f, 0.5f);
        }

        else {

            player.displayClientMessage(Component.translatable("enchantment.msmlegacy.vitality.not_enough_durability"), true);
        }

        return super.onItemUsed(worldLevel, player, hand, level);
    }
}
