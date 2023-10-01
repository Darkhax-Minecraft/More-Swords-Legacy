package net.darkhax.msmlegacy.mixin;

import net.darkhax.msmlegacy.RelicHooks;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.monster.Drowned;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Drowned.class)
public class MixinDrowned {

    @Inject(method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V", at = @At("RETURN"))
    public void onPopulateDefaultEquipmentSlots(RandomSource rng, DifficultyInstance difficulty, CallbackInfo cbi) {
        RelicHooks.setupDrowned((Drowned) (Object) this, rng, difficulty);
    }
}