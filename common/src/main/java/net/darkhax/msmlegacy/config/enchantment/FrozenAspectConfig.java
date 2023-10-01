package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.MobEffectConfig;
import net.minecraft.world.effect.MobEffects;

public class FrozenAspectConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("slowness_effect")
    public MobEffectConfig effect = new MobEffectConfig(MobEffects.MOVEMENT_SLOWDOWN, 1, 40);

    @Expose
    @SerializedName("damage_chance")
    public LevelScaledFloat damageChance = new LevelScaledFloat(0.25f);

    @Expose
    @SerializedName("frost_damage")
    public LevelScaledFloat frostDamage = new LevelScaledFloat(1f);

    public FrozenAspectConfig() {

        super(3);
    }
}
