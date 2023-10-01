package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;

public class KeenEdgeConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("damage_per_level")
    public LevelScaledFloat damage = new LevelScaledFloat(1.5f);

    public KeenEdgeConfig() {

        super(3);
    }
}