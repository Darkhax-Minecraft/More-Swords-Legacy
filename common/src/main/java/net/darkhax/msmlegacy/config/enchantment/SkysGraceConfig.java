package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class SkysGraceConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("fall_effect")
    public MobEffectConfig effect = new MobEffectConfig(MobEffects.SLOW_FALLING, 0, 35);

    @Expose
    @SerializedName("reset_fall_distance")
    public boolean resetFallDistance = true;
}