package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicKeyBladeConfig extends RelicConfig {

    @Expose
    @SerializedName("chance_normal")
    public float baseChance = 0.15f;

    @Expose
    @SerializedName("chance_first_time")
    public float newPlayerChance = 1f;
}
