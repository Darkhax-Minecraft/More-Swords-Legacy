package net.darkhax.msmlegacy.config.relics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelicConfig {

    @Expose
    private boolean enabled = true;

    public boolean isEnabled() {

        return this.enabled;
    }

    public static class Chance extends RelicConfig {

        @Expose
        @SerializedName("event_chance")
        private float chance;

        public Chance(float chance) {

            this.chance = chance;
        }

        public float getChance() {

            return this.chance;
        }
    }
}