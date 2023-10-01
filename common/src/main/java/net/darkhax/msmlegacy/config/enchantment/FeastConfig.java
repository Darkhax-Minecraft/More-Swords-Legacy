package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class FeastConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("repair_chance_per_level")
    public LevelScaledFloat repairChance = new LevelScaledFloat(0.04f);

    @Expose
    @SerializedName("repair_amount_per_level")
    public LevelScaledInt repairAmount = new LevelScaledInt(12);

    public FeastConfig() {
        super(3);
    }
}
