package net.darkhax.msmlegacy.addons.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.vanilla.IJeiAnvilRecipe;
import mezz.jei.api.recipe.vanilla.IVanillaRecipeFactory;
import mezz.jei.api.registration.IRecipeRegistration;
import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.item.MSMSwordItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class MSMJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(MSMContent.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {

        final IVanillaRecipeFactory vanillaFactory = registry.getVanillaRecipeFactory();

        if (MSMContent.INSTANCE != null) {

            final List<IJeiAnvilRecipe> repairData = new ArrayList<>();

            for (Item item : MSMContent.INSTANCE.items) {

                if (item instanceof MSMSwordItem sword) {

                    final ItemStack quarterDamage = item.getDefaultInstance();
                    quarterDamage.setDamageValue((int) (quarterDamage.getMaxDamage() * 0.75f));

                    final ItemStack halfDamaged = item.getDefaultInstance();
                    halfDamaged.setDamageValue((int) (halfDamaged.getMaxDamage() * 0.5f));

                    final ItemStack fullyDamaged = item.getDefaultInstance();
                    fullyDamaged.setDamageValue(fullyDamaged.getMaxDamage());

                    // Repair with self
                    repairData.add(vanillaFactory.createAnvilRecipe(List.of(quarterDamage.copy()), List.of(quarterDamage.copy()), List.of(halfDamaged.copy())));

                    // Repair with material
                    repairData.add(vanillaFactory.createAnvilRecipe(List.of(fullyDamaged), List.of(sword.getRepairMaterials().getItems()), List.of(quarterDamage)));
                }
            }

            if (!repairData.isEmpty()) {

                registry.addRecipes(RecipeTypes.ANVIL, repairData);
            }
        }
    }
}
