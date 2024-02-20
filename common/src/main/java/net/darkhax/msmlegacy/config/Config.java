package net.darkhax.msmlegacy.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.darkhax.bookshelf.api.Services;
import net.darkhax.msmlegacy.Constants;
import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.adapters.RegistryTypeAdapter;
import net.darkhax.msmlegacy.config.enchantment.AbsorbConfig;
import net.darkhax.msmlegacy.config.enchantment.AscensionConfig;
import net.darkhax.msmlegacy.config.enchantment.ConsumingShadowsConfig;
import net.darkhax.msmlegacy.config.enchantment.DecayConfig;
import net.darkhax.msmlegacy.config.enchantment.EnderAuraConfig;
import net.darkhax.msmlegacy.config.enchantment.EnderPulseConfig;
import net.darkhax.msmlegacy.config.enchantment.FeastConfig;
import net.darkhax.msmlegacy.config.enchantment.FrostWaveConfig;
import net.darkhax.msmlegacy.config.enchantment.FrozenAspectConfig;
import net.darkhax.msmlegacy.config.enchantment.GreedConfig;
import net.darkhax.msmlegacy.config.enchantment.IgniteConfig;
import net.darkhax.msmlegacy.config.enchantment.KeenEdgeConfig;
import net.darkhax.msmlegacy.config.enchantment.ScornConfig;
import net.darkhax.msmlegacy.config.enchantment.SkysGraceConfig;
import net.darkhax.msmlegacy.config.enchantment.SparksConfig;
import net.darkhax.msmlegacy.config.enchantment.VenomousAspectConfig;
import net.darkhax.msmlegacy.config.enchantment.VitalityConfig;
import net.darkhax.msmlegacy.config.enchantment.WisdomConfig;
import net.darkhax.msmlegacy.config.relics.RelicAqueousBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicBlazeSwordConfig;
import net.darkhax.msmlegacy.config.relics.RelicInfinityBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicKeyBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicMasterSword;
import net.darkhax.msmlegacy.config.relics.RelicMoltenBlade;
import net.darkhax.msmlegacy.config.relics.RelicPieCutter;
import net.darkhax.msmlegacy.config.types.LevelScaledDouble;
import net.darkhax.msmlegacy.config.types.LevelScaledFloat;
import net.darkhax.msmlegacy.config.types.LevelScaledInt;
import net.minecraft.world.effect.MobEffect;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(MobEffect.class, RegistryTypeAdapter.MOB_EFFECT).registerTypeAdapter(LevelScaledDouble.class, LevelScaledDouble.ADAPTER).registerTypeAdapter(LevelScaledFloat.class, LevelScaledFloat.ADAPTER).registerTypeAdapter(LevelScaledInt.class, LevelScaledInt.ADAPTER).excludeFieldsWithoutExposeAnnotation().create();

    @Expose
    @SerializedName("relic_events")
    public Relics relics = new Relics();

    @Expose
    @SerializedName("sword_stats")
    public WeaponStats swordStats = new WeaponStats();

    @Expose
    @SerializedName("enchantments")
    public Enchantments enchantments = new Enchantments();

    public static Config load() {

        File configFile = Services.PLATFORM.getConfigPath().resolve(MSMContent.MOD_ID + ".json").toFile();
        Config config = new Config();

        // Attempt to load existing config file
        if (configFile.exists()) {

            try (FileReader reader = new FileReader(configFile)) {

                config = GSON.fromJson(reader, Config.class);
                Constants.LOG.info("Loaded config file.");
            }

            catch (Exception e) {

                Constants.LOG.error("Could not read config file {}. Defaults will be used.", configFile.getAbsolutePath(), e);
            }
        }

        else {

            Constants.LOG.info("Creating a new config file at {}.", configFile.getAbsolutePath());
            configFile.getParentFile().mkdirs();
        }

        try (FileWriter writer = new FileWriter(configFile)) {

            GSON.toJson(config, writer);
            Constants.LOG.info("Saved config file.");
        }

        catch (Exception e) {

            Constants.LOG.error("Could not write config file '{}'!", configFile.getAbsolutePath(), e);
        }

        return config;
    }

    public static class Enchantments {

        @Expose
        @SerializedName("ignite")
        public IgniteConfig ignite = new IgniteConfig();

        @Expose
        @SerializedName("sparks")
        public SparksConfig sparks = new SparksConfig();

        @Expose
        @SerializedName("feast")
        public FeastConfig feast = new FeastConfig();

        @Expose
        @SerializedName("vitality")
        public VitalityConfig vitality = new VitalityConfig();

        @Expose
        @SerializedName("venomous_aspect")
        public VenomousAspectConfig venomousAspect = new VenomousAspectConfig();

        @Expose
        @SerializedName("absorb")
        public AbsorbConfig absorb = new AbsorbConfig();

        @Expose
        @SerializedName("keen_edge")
        public KeenEdgeConfig keenEdge = new KeenEdgeConfig();

        @Expose
        @SerializedName("scorn")
        public ScornConfig scorn = new ScornConfig();

        @Expose
        @SerializedName("ender_pulse")
        public EnderPulseConfig enderPulse = new EnderPulseConfig();

        @Expose
        @SerializedName("ender_aura")
        public EnderAuraConfig enderaura = new EnderAuraConfig();

        @Expose
        @SerializedName("greed")
        public GreedConfig greed = new GreedConfig();

        @Expose
        @SerializedName("wisdom")
        public WisdomConfig wisdom = new WisdomConfig();

        @Expose
        @SerializedName("frozen_aspect")
        public FrozenAspectConfig frozenAspect = new FrozenAspectConfig();

        @Expose
        @SerializedName("frost_wave")
        public FrostWaveConfig frostWave = new FrostWaveConfig();

        @Expose
        @SerializedName("ascension")
        public AscensionConfig ascension = new AscensionConfig();

        @Expose
        @SerializedName("skys_grace")
        public SkysGraceConfig skysGrace = new SkysGraceConfig();

        @Expose
        @SerializedName("decay")
        public DecayConfig decay = new DecayConfig();

        @Expose
        @SerializedName("consuming_shadows")
        public ConsumingShadowsConfig consumingshadows = new ConsumingShadowsConfig();
    }

    public static class WeaponStats {

        @Expose
        @SerializedName("dawn_star")
        public SwordStatsConfig dawnStar = new SwordStatsConfig(1280, 6, 22).swingSpeed(-2.3f);

        @Expose
        @SerializedName("vampiric_blade")
        public SwordStatsConfig vampiricBlade = new SwordStatsConfig(812, 6, 12).swingSpeed(-2.2f);

        @Expose
        @SerializedName("gladiolus")
        public SwordStatsConfig gladiolus = new SwordStatsConfig(650, 6, 14).swingSpeed(-2.2f);

        @Expose
        @SerializedName("draconic_blade")
        public SwordStatsConfig draconicBlade = new SwordStatsConfig(1280, 7, 16);

        @Expose
        @SerializedName("eye_end_blade")
        public SwordStatsConfig eyeEndBlade = new SwordStatsConfig(1580, 7, 22);

        @Expose
        @SerializedName("crystaline_blade")
        public SwordStatsConfig crystalineBlade = new SwordStatsConfig(512, 5, 28).swingSpeed(-2.2f);

        @Expose
        @SerializedName("glacial_blade")
        public SwordStatsConfig glacialBlade = new SwordStatsConfig(750, 6, 15).swingSpeed(-2.5f);

        @Expose
        @SerializedName("aethers_guard")
        public SwordStatsConfig aethersGuard = new SwordStatsConfig(1800, 9, 22);

        @Expose
        @SerializedName("wither_bane")
        public SwordStatsConfig withersBane = new SwordStatsConfig(1800, 9, 18);

        @Expose
        @SerializedName("relic_aqueous_blade")
        public SwordStatsConfig relic_aqueous_blade = new SwordStatsConfig(500, 3);

        @Expose
        @SerializedName("relic_molten_blade")
        public SwordStatsConfig relic_molten_blade = new SwordStatsConfig(500, 3);

        @Expose
        @SerializedName("relic_infinity_blade")
        public SwordStatsConfig relic_infinity_blade = new SwordStatsConfig(650, 3);

        @Expose
        @SerializedName("relic_key_blade")
        public SwordStatsConfig relic_key_blade = new SwordStatsConfig(400, 4);

        @Expose
        @SerializedName("relic_master_sword")
        public SwordStatsConfig relic_master_sword = new SwordStatsConfig(700, 5);

        @Expose
        @SerializedName("relic_pie_cutter")
        public SwordStatsConfig relic_pie_cutter = new SwordStatsConfig(150, 2);

        @Expose
        @SerializedName("relic_blaze_sword")
        public SwordStatsConfig relic_blaze_sword = new SwordStatsConfig(350, 3);

        @Expose
        @SerializedName("relic_adminium_ark")
        public SwordStatsConfig relic_adminium_ark = new SwordStatsConfig(1024, 8);
    }

    public static class Relics {

        @Expose
        @SerializedName("aqueous_blade")
        public RelicAqueousBladeConfig aqueousBlade = new RelicAqueousBladeConfig();

        @Expose
        @SerializedName("blaze_sword")
        public RelicBlazeSwordConfig blazeSword = new RelicBlazeSwordConfig();

        @Expose
        @SerializedName("infinity_blade")
        public RelicInfinityBladeConfig infinityBladeConfig = new RelicInfinityBladeConfig();

        @Expose
        @SerializedName("master_sword")
        public RelicMasterSword masterSwordConfig = new RelicMasterSword();

        @Expose
        @SerializedName("key_blade")
        public RelicKeyBladeConfig keyBladeConfig = new RelicKeyBladeConfig();

        @Expose
        @SerializedName("molten_blade")
        public RelicMoltenBlade moltenBladeConfig = new RelicMoltenBlade();

        @Expose
        @SerializedName("pie_cutter")
        public RelicPieCutter pieCutterConfig = new RelicPieCutter();
    }
}
