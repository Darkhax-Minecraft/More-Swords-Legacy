package net.darkhax.msmlegacy.trades;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.RelicHooks;
import net.darkhax.msmlegacy.config.relics.RelicPieCutter;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import org.jetbrains.annotations.Nullable;

public class MerchantOfferPieCutter implements VillagerTrades.ItemListing {

    @Nullable
    @Override
    public MerchantOffer getOffer(Entity entity, RandomSource randomSource) {

        final RelicPieCutter config = MSMContent.CONFIG.relics.pieCutterConfig;

        if (config.isEnabled() && randomSource.nextFloat() < config.getChance()) {

            return new MerchantOffer(new ItemStack(Items.EMERALD, randomSource.nextIntBetweenInclusive(config.minEmeraldCost, config.maxEmeraldCost)), Items.PUMPKIN_PIE.getDefaultInstance(), RelicHooks.PIE_CUTTER.get().getDefaultInstance(), 0, config.maxUses, 1, 0.05f);
        }

        else {
            return new MerchantOffer(new ItemStack(Items.EMERALD, randomSource.nextIntBetweenInclusive(config.minEmeraldCost, config.maxEmeraldCost)), Items.PUMPKIN_PIE.getDefaultInstance(), config.maxUses, 1, 0.05f);
        }
    }
}
