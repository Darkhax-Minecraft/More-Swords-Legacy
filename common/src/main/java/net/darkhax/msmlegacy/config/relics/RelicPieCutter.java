package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicPieCutter extends RelicConfig.Chance {

    @Expose
    @SerializedName("emerald_cost_min")
    public int minEmeraldCost = 13;

    @Expose
    @SerializedName("emerald_cost_max")
    public int maxEmeraldCost = 22;

    @Expose
    @SerializedName("max_uses")
    public int maxUses = 1;

    public RelicPieCutter() {

        super(0.15f);
    }
}
