package net.darkhax.msmlegacy.config.enchantment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;

public class WisdomConfig extends EnchantmentConfig {

    @Expose
    @SerializedName("damage")
    public LevelScaledFloat damage = new LevelScaledFloat(1f);

    @Expose
    @SerializedName("damage_cap")
    public LevelScaledFloat damageCap = new LevelScaledFloat(10f);

    @Expose
    @SerializedName("threshold")
    public LevelScaledInt threshold = new LevelScaledInt(10);
}
