package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class GreedConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("chance_per_level")
    public LevelScaledFloat chance = new LevelScaledFloat(0.2f);

    @Expose
    @SerializedName("min_exp_per_level")
    public LevelScaledInt minExp = new LevelScaledInt(1);

    @Expose
    @SerializedName("max_exp_per_level")
    public LevelScaledInt maxExp = new LevelScaledInt(1);

    public GreedConfig() {
        super(3);
    }
}
