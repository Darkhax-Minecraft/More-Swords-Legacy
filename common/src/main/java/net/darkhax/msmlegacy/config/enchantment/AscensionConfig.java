package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;

public class AscensionConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("force_per_level")
    public LevelScaledFloat force = new LevelScaledFloat(0.35f);

    @Expose
    @SerializedName("chance_per_level")
    public LevelScaledFloat chance = new LevelScaledFloat(1f);

    public AscensionConfig() {

        super(3);
    }
}
