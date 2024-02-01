package net.darkhax.msmlegacy.mixin;

import net.darkhax.msmlegacy.enchantments.SwordEnchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class MixinEnchantmentHelper {

    @Inject(method = "getAvailableEnchantmentResults(ILnet/minecraft/world/item/ItemStack;Z)Ljava/util/List;", at = @At("RETURN"))
    private static void getRandomEnchantments(int cost, ItemStack stack, boolean $$2, CallbackInfoReturnable<List<EnchantmentInstance>> cbi) {

        cbi.getReturnValue().removeIf(entry -> entry.enchantment instanceof SwordEnchantment enchant && !enchant.canEnchant(stack));
    }
}