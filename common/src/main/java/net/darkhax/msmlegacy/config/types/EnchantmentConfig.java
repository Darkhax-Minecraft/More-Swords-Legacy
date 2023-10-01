package net.darkhax.msmlegacy.config.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EnchantmentConfig {

    @Expose
    @SerializedName("can_villagers_trade")
    public boolean isTradeable = false;

    @Expose
    @SerializedName("is_generally_available")
    public boolean isDiscoverable = true;

    @Expose
    @SerializedName("level_min")
    public int minLevel;

    @Expose
    @SerializedName("level_max")
    public int maxLevel;

    public EnchantmentConfig() {

        this(1);
    }

    public EnchantmentConfig(int maxLevel) {

        this.minLevel = 1;
        this.maxLevel = maxLevel;
    }
}