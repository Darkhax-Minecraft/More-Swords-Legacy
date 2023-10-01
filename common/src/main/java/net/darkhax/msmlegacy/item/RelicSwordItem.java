package net.darkhax.msmlegacy.item;

import net.darkhax.bookshelf.api.function.CachedSupplier;
import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.SwordStatsConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class RelicSwordItem extends MSMSwordItem {

    private static final int BASE_DAMAGE = 0;
    private static final int relicCount = 7;

    private final int relicIndex;
    private final String relicType;
    private final Component description;
    private final Supplier<Component> countText;

    public RelicSwordItem(String type, int relicIndex, SwordStatsConfig config) {

        super(type, Rarity.EPIC, config);

        this.relicIndex = relicIndex;
        this.relicType = type;
        this.description = Component.translatable("item." + MSMContent.MOD_ID + "." + this.relicType + ".desc").withStyle(ChatFormatting.DARK_GRAY);
        this.countText = CachedSupplier.cache(() -> Component.translatable("msmlegacy.relic.count", this.relicIndex, relicCount).withStyle(ChatFormatting.GOLD));
    }

    @Override
    public void appendHoverText(ItemStack self, @Nullable Level worldLevel, List<Component> tooltips, TooltipFlag flags) {

        super.appendHoverText(self, worldLevel, tooltips, flags);
        tooltips.add(this.description);
        tooltips.add(this.countText.get());
    }
}