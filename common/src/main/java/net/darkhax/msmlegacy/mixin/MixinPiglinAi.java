package net.darkhax.msmlegacy.mixin;

import net.darkhax.msmlegacy.RelicHooks;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.piglin.PiglinAi;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PiglinAi.class)
public class MixinPiglinAi {

    @Inject(method = "getBarterResponseItems(Lnet/minecraft/world/entity/monster/piglin/Piglin;)Ljava/util/List;", at = @At("RETURN"))
    private static void getBarterResponseItems(Piglin piglin, CallbackInfoReturnable<List<ItemStack>> cbi) {

        RelicHooks.injectPiglinBarteringTrades(piglin, cbi.getReturnValue());
    }
}