package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class EnderPulseConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("distance")
    public LevelScaledInt distance = new LevelScaledInt(32, 64, 96);

    @Expose
    @SerializedName("cooldown_time")
    public LevelScaledInt cooldownTime = new LevelScaledInt(600);

    public EnderPulseConfig() {

        super(3);
    }
}
