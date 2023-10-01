package net.darkhax.msmlegacy;

import net.darkhax.bookshelf.api.Services;
import net.darkhax.bookshelf.api.registry.RegistryDataProvider;
import net.darkhax.msmlegacy.config.Config;
import net.darkhax.msmlegacy.config.SwordStatsConfig;
import net.darkhax.msmlegacy.enchantments.EnchantmentAbsorb;
import net.darkhax.msmlegacy.enchantments.EnchantmentAscension;
import net.darkhax.msmlegacy.enchantments.EnchantmentConsumingShadows;
import net.darkhax.msmlegacy.enchantments.EnchantmentDecay;
import net.darkhax.msmlegacy.enchantments.EnchantmentEnderAura;
import net.darkhax.msmlegacy.enchantments.EnchantmentEnderPulse;
import net.darkhax.msmlegacy.enchantments.EnchantmentFeast;
import net.darkhax.msmlegacy.enchantments.EnchantmentFrostWave;
import net.darkhax.msmlegacy.enchantments.EnchantmentFrozenAspect;
import net.darkhax.msmlegacy.enchantments.EnchantmentGreed;
import net.darkhax.msmlegacy.enchantments.EnchantmentIgnite;
import net.darkhax.msmlegacy.enchantments.EnchantmentKeenEdge;
import net.darkhax.msmlegacy.enchantments.EnchantmentScorn;
import net.darkhax.msmlegacy.enchantments.EnchantmentSkysGrace;
import net.darkhax.msmlegacy.enchantments.EnchantmentSparks;
import net.darkhax.msmlegacy.enchantments.EnchantmentVenomousAspect;
import net.darkhax.msmlegacy.enchantments.EnchantmentVitality;
import net.darkhax.msmlegacy.enchantments.EnchantmentWisdom;
import net.darkhax.msmlegacy.item.MSMSwordItem;
import net.darkhax.msmlegacy.item.RelicSwordItem;
import net.darkhax.msmlegacy.trades.MerchantOfferPieCutter;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.function.Function;

public final class MSMContent extends RegistryDataProvider {

    public static final String MOD_ID = "msmlegacy";
    public static final Config CONFIG = Config.load();
    public static MSMContent INSTANCE ;

    public static void init() {

        INSTANCE = new MSMContent();
        Services.REGISTRIES.loadContent(INSTANCE);
    }

    private MSMContent() {

        super(MOD_ID);

        this.withItemTab(Items.NETHERITE_SWORD::getDefaultInstance);

        sword("dawn_star", Rarity.RARE, CONFIG.swordStats.dawnStar);
        sword("vampiric_blade", Rarity.RARE, CONFIG.swordStats.vampiricBlade);
        sword("gladiolus", Rarity.UNCOMMON, CONFIG.swordStats.gladiolus);
        sword("draconic_blade", Rarity.UNCOMMON, CONFIG.swordStats.draconicBlade);
        sword("eye_end_blade", Rarity.RARE, CONFIG.swordStats.eyeEndBlade);
        sword("crystaline_blade", Rarity.UNCOMMON, CONFIG.swordStats.crystalineBlade);
        sword("glacial_blade", Rarity.RARE, CONFIG.swordStats.glacialBlade);
        sword("aethers_guard", Rarity.EPIC, CONFIG.swordStats.aethersGuard);
        sword("wither_bane", Rarity.EPIC, CONFIG.swordStats.withersBane);

        enchantment("ignite", EnchantmentIgnite::new);
        enchantment("sparks", EnchantmentSparks::new);
        enchantment("feast", EnchantmentFeast::new);
        enchantment("vitality", EnchantmentVitality::new);
        enchantment("venomous_aspect", EnchantmentVenomousAspect::new);
        enchantment("absorb", EnchantmentAbsorb::new);
        enchantment("keen_edge", EnchantmentKeenEdge::new);
        enchantment("scorn", EnchantmentScorn::new);
        enchantment("ender_pulse", EnchantmentEnderPulse::new);
        enchantment("ender_aura", EnchantmentEnderAura::new);
        enchantment("greed", EnchantmentGreed::new);
        enchantment("wisdom", EnchantmentWisdom::new);
        enchantment("frozen_aspect", EnchantmentFrozenAspect::new);
        enchantment("frost_wave", EnchantmentFrostWave::new);
        enchantment("ascension", EnchantmentAscension::new);
        enchantment("skys_grace", EnchantmentSkysGrace::new);
        enchantment("decay", EnchantmentDecay::new);
        enchantment("consuming_shadows", EnchantmentConsumingShadows::new);

        relic("relic_aqueous_blade", 1, CONFIG.swordStats.relic_aqueous_blade);
        relic("relic_molten_blade", 2, CONFIG.swordStats.relic_molten_blade);
        relic("relic_infinity_blade", 3, CONFIG.swordStats.relic_infinity_blade);
        relic("relic_key_blade", 4, CONFIG.swordStats.relic_key_blade);
        relic("relic_master_sword", 5, CONFIG.swordStats.relic_master_sword);
        relic("relic_pie_cutter", 6, CONFIG.swordStats.relic_pie_cutter);
        relic("relic_blaze_sword", 7, CONFIG.swordStats.relic_blaze_sword);
        relic("relic_adminium_ark", 8, CONFIG.swordStats.relic_adminium_ark);

        if (CONFIG.relics.pieCutterConfig.isEnabled()) {

            this.trades.addRareWanderingTrade(new MerchantOfferPieCutter());
        }
    }

    private void sword(String name, Rarity rarity, SwordStatsConfig config) {

        this.items.add(() -> new MSMSwordItem(name, rarity, config), name);
    }

    private void relic(String name, int index, SwordStatsConfig config) {

        this.items.add(() -> new RelicSwordItem(name, index, config), name);
    }

    private void enchantment(String name, Function<String, Enchantment> constructor) {

        this.enchantments.add(() -> constructor.apply(name), name);
    }
}