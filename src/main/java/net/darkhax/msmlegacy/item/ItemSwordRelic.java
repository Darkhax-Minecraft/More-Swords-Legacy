package net.darkhax.msmlegacy.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSwordRelic extends ItemSword {

    private static int maxRelics = 0;
    private final int relicIndex;

    public ItemSwordRelic () {

        super(ToolMaterial.IRON);
        maxRelics++;
        this.relicIndex = maxRelics;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.format("jei." + this.getTranslationKey()));
        tooltip.add(TextFormatting.GOLD + I18n.format("msmlegacy.relic.count", this.relicIndex, maxRelics));
    }
}