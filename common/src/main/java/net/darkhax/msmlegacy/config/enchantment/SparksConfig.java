package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class SparksConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("effect_range_per_level")
    public LevelScaledFloat range = new LevelScaledFloat(1.5f);

    @Expose
    @SerializedName("burn_amount_per_level")
    public LevelScaledInt burnAmount = new LevelScaledInt(2);

    @Expose
    @SerializedName("knockback_amount_per_level")
    public LevelScaledFloat knockback = new LevelScaledFloat(0.4f);
}
