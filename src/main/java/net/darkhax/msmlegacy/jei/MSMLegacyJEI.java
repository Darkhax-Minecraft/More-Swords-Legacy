package net.darkhax.msmlegacy.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.VanillaTypes;
import net.darkhax.msmlegacy.MSMLegacy;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.Instance;

@JEIPlugin
public class MSMLegacyJEI implements IModPlugin {

    @Instance("msmlegacy")
    public static MSMLegacy msm;

    @Override
    public void register (IModRegistry registry) {

        for (final Item item : msm.registry.getItems()) {

            registry.addIngredientInfo(new ItemStack(item), VanillaTypes.ITEM, "jei." + item.getTranslationKey());
        }
    }
}