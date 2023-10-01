package net.darkhax.msmlegacy.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class SwordStatsConfig {

    @Expose
    @SerializedName("harvest_level")
    public int harvestLevel = 3;

    @Expose
    @SerializedName("durability")
    public int durability;

    @Expose
    @SerializedName("dig_speed")
    public float digSpeed = 1f;

    @Expose
    @SerializedName("attack_damage")
    public float damage;

    @Expose
    @SerializedName("enchantability")
    public int enchantability;

    @Expose
    @SerializedName("swing_speed_penalty")
    public float swingSpeedPenalty = -2.4f;

    public SwordStatsConfig(int durability, float attackDamage, int enchantability) {

        this.durability = durability;
        this.damage = attackDamage;
        this.enchantability = enchantability;
    }

    public SwordStatsConfig(int durability, float attackDamage) {

        this(durability, attackDamage, 14);
    }

    public SwordStatsConfig swingSpeed(float speed) {

        this.swingSpeedPenalty = speed;
        return this;
    }

    public Tier getAstier() {

        return new SwordTier(this);
    }

    public static class SwordTier implements Tier {

        private SwordStatsConfig config;

        public SwordTier(SwordStatsConfig config) {

            this.config = config;
        }

        @Override
        public int getUses() {
            return this.config.durability;
        }

        @Override
        public float getSpeed() {
            return this.config.digSpeed;
        }

        @Override
        public float getAttackDamageBonus() {
            return Math.max(0, config.damage - 1);
        }

        @Override
        public int getLevel() {
            return config.harvestLevel;
        }

        @Override
        public int getEnchantmentValue() {
            return config.enchantability;
        }

        @Override
        public Ingredient getRepairIngredient() {
            // We don't use this.
            return Ingredient.EMPTY;
        }
    }
}
