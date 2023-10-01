package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class VenomousAspectConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("mob_effect")
    public MobEffectConfig effect = new MobEffectConfig(MobEffects.POISON, 1, 80, 0.3f);

    public VenomousAspectConfig() {
        super(3);
    }
}
