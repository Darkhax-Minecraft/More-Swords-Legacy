package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class DecayConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("wither")
    public MobEffectConfig wither = new MobEffectConfig(MobEffects.WITHER, 0, 40);

    @Expose
    @SerializedName("hunger")
    public MobEffectConfig hunger = new MobEffectConfig(MobEffects.HUNGER, 1, 40);

    public DecayConfig() {
        super(3);
    }
}
