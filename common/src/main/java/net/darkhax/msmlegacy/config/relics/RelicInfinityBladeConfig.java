package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicInfinityBladeConfig extends RelicConfig.Chance {

    @Expose
    @SerializedName("bonus_armor")
    public double bonusArmor = 4;

    @Expose
    @SerializedName("bonus_armor_toughness")
    public double bonusArmorToughness = 2;

    @Expose
    @SerializedName("bonus_damage")
    public double bonusDamage = 2;

    @Expose
    @SerializedName("bonus_health")
    public double bonusHealth = 30;

    @Expose
    @SerializedName("custom_armor_enabled")
    public boolean useCustomArmor = true;

    @Expose
    @SerializedName("custom_armor_drop_chance")
    public float armorDropChance = 0.0f;

    @Expose
    @SerializedName("drop_chance")
    public float dropChance = 1f;

    public RelicInfinityBladeConfig() {

        super(0.001f);
    }
}