package net.darkhax.msmlegacy.config.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class MobEffectConfig {

    @Expose
    @SerializedName("enabled")
    public boolean enabled = true;

    @Expose
    @SerializedName("chance_per_level")
    public LevelScaledFloat chance;

    @Expose
    @SerializedName("amplifier_per_level")
    public LevelScaledInt amplifier;

    @Expose
    @SerializedName("duration_per_level")
    public LevelScaledInt duration;

    @Expose
    @SerializedName("effect_id")
    private final MobEffect effect;

    public MobEffectConfig(MobEffect effect, int amplifier, int duration) {

        this(effect, amplifier, duration, 1f);
    }

    public MobEffectConfig(MobEffect effect, int amplifier, int duration, float chance) {

        this(effect, new LevelScaledInt(amplifier), new LevelScaledInt(duration), new LevelScaledFloat(chance));
    }

    public MobEffectConfig(MobEffect effect, LevelScaledInt amplifier, LevelScaledInt duration, LevelScaledFloat chance) {

        this.amplifier = amplifier;
        this.duration = duration;
        this.effect = effect;
        this.chance = chance;
    }


    public void applyEffect(Entity target, int level) {

        if (this.enabled && target instanceof LivingEntity living && living.isAlive() && living.getRandom().nextFloat() < this.chance.getValue(level)) {

            living.addEffect(new MobEffectInstance(this.effect, this.duration.getValue(level), Math.min(0, this.amplifier.getValue(level))));
        }
    }
}
