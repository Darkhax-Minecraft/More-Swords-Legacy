package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class VitalityConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("durability_cost_percent")
    public LevelScaledFloat durabilityCost = new LevelScaledFloat(0.20f);

    @Expose
    @SerializedName("absorption_effect")
    public MobEffectConfig absorption = new MobEffectConfig(MobEffects.ABSORPTION, 0, 600);

    @Expose
    @SerializedName("resistance_effect")
    public MobEffectConfig resistance = new MobEffectConfig(MobEffects.DAMAGE_RESISTANCE, 0, 600);

    @Expose
    @SerializedName("regeneration_effect")
    public MobEffectConfig regeneration = new MobEffectConfig(MobEffects.REGENERATION, 0, 600);

    @Expose
    @SerializedName("fire_resistance_effect")
    public MobEffectConfig fireResistance = new MobEffectConfig(MobEffects.FIRE_RESISTANCE, 0, 600);

    @Expose
    @SerializedName("heal_amount_per_level")
    public LevelScaledInt healAmount = new LevelScaledInt(10);
}