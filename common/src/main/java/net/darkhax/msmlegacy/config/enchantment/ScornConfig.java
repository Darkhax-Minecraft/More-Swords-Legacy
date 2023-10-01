package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;

public class ScornConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("effect_range_per_level")
    public LevelScaledFloat damageModifier = new LevelScaledFloat(2f);
}