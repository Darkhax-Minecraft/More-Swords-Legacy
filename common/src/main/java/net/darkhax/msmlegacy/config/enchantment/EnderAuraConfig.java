package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class EnderAuraConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("chance")
    public LevelScaledFloat chance = new LevelScaledFloat(0.40f);

    @Expose
    @SerializedName("warp_range_per_level")
    public LevelScaledInt warpRange = new LevelScaledInt(32);
}
