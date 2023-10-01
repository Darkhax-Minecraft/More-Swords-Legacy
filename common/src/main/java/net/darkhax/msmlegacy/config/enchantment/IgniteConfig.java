package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class IgniteConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("fire_damage_per_level")
    public LevelScaledFloat fireDamage = new LevelScaledFloat(0.5f);

    @Expose
    @SerializedName("damage_chance")
    public LevelScaledFloat chance = new LevelScaledFloat(1f);

    @Expose
    @SerializedName("burn_time_per_level")
    public LevelScaledInt burnTime = new LevelScaledInt(2);

    public IgniteConfig() {
        super(3);
    }
}
