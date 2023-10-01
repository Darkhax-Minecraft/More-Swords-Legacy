package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.enchantment.SkysGraceConfig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnchantmentSkysGrace extends SwordEnchantment {

    public EnchantmentSkysGrace(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.skysGrace);
    }

    @Override
    public void onHeldTick(ItemStack stack, Level level, Player user, int slotId, int enchLevel) {

        if (user.fallDistance > 4f && !user.isCrouching()) {

            final SkysGraceConfig config = MSMContent.CONFIG.enchantments.skysGrace;

            config.effect.applyEffect(user, enchLevel);

            if (config.resetFallDistance) {

                user.fallDistance = 3f;
            }
        }
    }
}
