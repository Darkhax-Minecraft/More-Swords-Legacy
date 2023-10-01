package net.darkhax.msmlegacy.mixin;

import net.darkhax.msmlegacy.enchantments.EnchantmentScorn;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Player.class)
public class MixinPlayer {

    @ModifyVariable(method = "attack(Lnet/minecraft/world/entity/Entity;)V", at = @At(value = "STORE", ordinal = 0), ordinal = 0)
    private float injected(float baseDamage) {

        final Player self = (Player)(Object)this;
        return self.level().isClientSide ? baseDamage : EnchantmentScorn.modifyBaseDamage((Player)(Object)this, baseDamage);
    }
}
