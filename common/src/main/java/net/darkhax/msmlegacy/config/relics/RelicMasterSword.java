package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicMasterSword extends RelicConfig.Chance {

    @Expose
    @SerializedName("custom_shield_enabled")
    public boolean useCustomShield = true;

    @Expose
    @SerializedName("custom_shield_drop_chance")
    public float shieldDropChance = 0.25f;

    @Expose
    @SerializedName("drop_chance")
    public float dropChance = 1f;

    @Expose
    @SerializedName("replace_with_wither_skeleton")
    public boolean replaceSkeleton = true;

    public RelicMasterSword() {

        super(0.001f);
    }
}