package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class ConsumingShadowsConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("wither")
    public MobEffectConfig wither = new MobEffectConfig(MobEffects.WITHER, 0, 80);

    @Expose
    @SerializedName("blindness")
    public MobEffectConfig blindness = new MobEffectConfig(MobEffects.BLINDNESS, 1, 80);

    @Expose
    @SerializedName("range")
    public LevelScaledFloat range = new LevelScaledFloat(1.5f);
}
