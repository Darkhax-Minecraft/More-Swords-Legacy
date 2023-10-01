package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicAqueousBladeConfig extends RelicConfig.Chance {

    @Expose
    @SerializedName("drop_chance")
    public float dropChance = 1f;

    @Expose
    @SerializedName("bonus_armor")
    public double bonusArmor = 2d;

    @Expose
    @SerializedName("bonus_damage")
    public double bonusDamage = 5d;

    @Expose
    @SerializedName("bonus_health")
    public double bonusHealth = 20d;

    public RelicAqueousBladeConfig() {

        super(0.0025f);
    }
}
