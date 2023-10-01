package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicMoltenBlade extends RelicConfig.Chance {

    @Expose
    @SerializedName("drop_chance")
    public float dropChance = 1f;

    public RelicMoltenBlade() {

        super(0.005f);
    }
}