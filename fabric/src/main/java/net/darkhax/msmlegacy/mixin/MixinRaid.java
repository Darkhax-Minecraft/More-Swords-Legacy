package net.darkhax.msmlegacy.mixin;

import net.darkhax.msmlegacy.RelicHooks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.raid.Raid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.UUID;

@Mixin(Raid.class)
public class MixinRaid {

    @Inject(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/critereon/PlayerTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;)V"), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onRaidVictory(CallbackInfo cbi, int idc1, Iterator idc2, UUID heroId, Entity idc4, LivingEntity idc5, ServerPlayer hero) {

        RelicHooks.onPlayerWinsRaid(hero);
    }
}
