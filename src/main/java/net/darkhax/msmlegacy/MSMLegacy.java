package net.darkhax.msmlegacy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.OreDictUtils;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "msmlegacy", name = "More Swords: Legacy", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.3.577,)", certificateFingerprint = "@FINGERPRINT@")
public class MSMLegacy {

    private final CreativeTabs creativeTab = new CreativeTabMSMLegacy();
    public final RegistryHelper registry = new RegistryHelper("msmlegacy").setTab(this.creativeTab).enableAutoRegistration();
    public final ConfigurationHandler config = new ConfigurationHandler(new File("config/msmlegacy.cfg"));
    private final String[] relicNames = { "aqueous", "candy", "infinity", "keyblade", "master", "molten", "pie" };
    public final List<Item> relics = new ArrayList<>();

    public Item dawnStar;
    public Enchantment ignite;
    public Enchantment sparks;

    public Item vampiricBlade;
    public Enchantment feast;
    public Enchantment vitality;

    public Item gladiolus;
    public Enchantment venomousAspect;
    public Enchantment absorb;

    public Item draconicBlade;
    public Enchantment keenEdge;
    public Enchantment scorn;

    public Item eyeEndBlade;
    public Enchantment enderPulse;
    public Enchantment enderAura;

    public Item crystalineBlade;
    public Enchantment greed;
    public Enchantment wisdom;

    public Item glacialBlade;
    public Enchantment frozenAspect;
    public Enchantment frostWave;

    public Item aeithersGuard;
    public Enchantment ascension;
    public Enchantment descension;

    public Item withersBane;
    public Enchantment consumingShadows;
    public Enchantment decay;

    public Item adminiumArk;
    public Enchantment stealth;
    public Enchantment extinction;

    @EventHandler
    public void onPreInit (FMLPreInitializationEvent event) {

        this.dawnStar = this.registry.registerItem(new ItemSword(this.config.getMaterial("dawn_star", 3, 1286, 8, 6, 22)), "dawn_star");
        this.ignite = this.registry.registerEnchantment(this.config.getSwordEnchantment("ignite", this.dawnStar, Rarity.COMMON, 1, 3), "ignite");
        this.sparks = this.registry.registerEnchantment(this.config.getSwordEnchantment("sparks", this.dawnStar, Rarity.RARE, 1, 1), "sparks");
        this.registry.addShapedRecipe("dawn_star", new ItemStack(this.dawnStar), "bcm", "cmc", "rcb", 'b', Items.BLAZE_POWDER, 'c', Items.FIRE_CHARGE, 'm', Items.MAGMA_CREAM, 'r', Items.BLAZE_ROD);

        this.vampiricBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("vampiric_blade", 3, 812, 8, 7, 12)), "vampiric_blade");
        this.feast = this.registry.registerEnchantment(this.config.getSwordEnchantment("feast", this.vampiricBlade, Rarity.COMMON, 1, 3), "feast");
        this.vitality = this.registry.registerEnchantment(this.config.getSwordEnchantment("vitality", this.vampiricBlade, Rarity.RARE, 1, 1), "vitality");
        this.registry.addShapedRecipe("vampiric_blade", new ItemStack(this.vampiricBlade), " ir", "ori", "so ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'o', OreDictUtils.OBSIDIAN, 's', OreDictUtils.STICK_WOOD);

        this.gladiolus = this.registry.registerItem(new ItemSword(this.config.getMaterial("gladiolus", 3, 645, 8, 6, 10)), "gladiolus");
        this.venomousAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("venomous_aspect", this.gladiolus, Rarity.COMMON, 1, 3), "venomous_aspect");
        this.absorb = this.registry.registerEnchantment(this.config.getSwordEnchantment("absorb", this.gladiolus, Rarity.RARE, 1, 1), "absorb");
        this.registry.addShapedRecipe("gladiolus", new ItemStack(this.gladiolus), " lv", "sfl", "ts ", 'l', OreDictUtils.TREE_LEAVES, 'v', OreDictUtils.VINE, 's', OreDictUtils.TREE_SAPLING, 'f', new ItemStack(Blocks.RED_FLOWER, 1, 1), 't', OreDictUtils.STICK_WOOD);

        this.draconicBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("draconic_blade", 3, 1089, 8, 7, 16)), "draconic_blade");
        this.keenEdge = this.registry.registerEnchantment(this.config.getSwordEnchantment("keen_edge", this.draconicBlade, Rarity.COMMON, 1, 3), "keen_edge");
        this.scorn = this.registry.registerEnchantment(this.config.getSwordEnchantment("scorn", this.draconicBlade, Rarity.RARE, 1, 1), "scorn");
        this.registry.addShapedRecipe("draconic_blade", new ItemStack(this.draconicBlade), " ir", "ldi", "sl ", 'i', OreDictUtils.INGOT_IRON, 'r', OreDictUtils.DUST_REDSTONE, 'l', OreDictUtils.GEM_LAPIS, 'd', OreDictUtils.GEM_DIAMOND, 's', OreDictUtils.STICK_WOOD);

        this.eyeEndBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("eye_end_blade", 3, 1580, 8, 8, 22)), "eye_end_blade");
        this.enderPulse = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_pulse", this.eyeEndBlade, Rarity.COMMON, 1, 3), "ender_pulse");
        this.enderAura = this.registry.registerEnchantment(this.config.getSwordEnchantment("ender_aura", this.eyeEndBlade, Rarity.RARE, 1, 1), "ender_aura");
        this.registry.addShapedRecipe("eye_end_blade", new ItemStack(this.eyeEndBlade), " po", "dep", "sd ", 'p', OreDictUtils.ENDERPEARL, 'o', OreDictUtils.OBSIDIAN, 'd', OreDictUtils.GEM_DIAMOND, 'e', Items.ENDER_EYE, 's', OreDictUtils.STICK_WOOD);

        this.crystalineBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("crystaline_blade", 3, 570, 8, 5, 28)), "crystaline_blade");
        this.greed = this.registry.registerEnchantment(this.config.getSwordEnchantment("greed", this.crystalineBlade, Rarity.COMMON, 1, 3), "greed");
        this.wisdom = this.registry.registerEnchantment(this.config.getSwordEnchantment("wisdom", this.crystalineBlade, Rarity.RARE, 1, 1), "wisdom");
        this.registry.addShapedRecipe("crystaline", new ItemStack(this.crystalineBlade), " gg", "qpg", "sq ", 'g', OreDictUtils.BLOCK_GLASS, 'p', OreDictUtils.PANE_GLASS, 'q', OreDictUtils.GEM_QUARTZ, 's', OreDictUtils.STICK_WOOD);

        this.glacialBlade = this.registry.registerItem(new ItemSword(this.config.getMaterial("glacial_blade", 3, 680, 8, 6, 15)), "glacial_blade");
        this.frozenAspect = this.registry.registerEnchantment(this.config.getSwordEnchantment("frozen_aspect", this.glacialBlade, Rarity.COMMON, 1, 3), "frozen_aspect");
        this.frostWave = this.registry.registerEnchantment(this.config.getSwordEnchantment("frost_wave", this.glacialBlade, Rarity.RARE, 1, 1), "frost_wave");
        this.registry.addShapedRecipe("glacial", new ItemStack(this.glacialBlade), " ip", "ipi", "si ", 'i', Blocks.ICE, 'p', Blocks.PACKED_ICE, 's', OreDictUtils.STICK_WOOD);

        this.aeithersGuard = this.registry.registerItem(new ItemSword(this.config.getMaterial("aethers_guard", 3, 1796, 8, 8, 22)), "aethers_guard");
        this.ascension = this.registry.registerEnchantment(this.config.getSwordEnchantment("ascension", this.aeithersGuard, Rarity.COMMON, 1, 3), "ascension");
        this.descension = this.registry.registerEnchantment(this.config.getSwordEnchantment("descension", this.aeithersGuard, Rarity.RARE, 1, 1), "descension");
        this.registry.addShapedRecipe("aethers_guard", new ItemStack(this.aeithersGuard), "fdg", "dgi", "sif", 'f', OreDictUtils.FEATHER, 'd', OreDictUtils.GEM_DIAMOND, 'g', OreDictUtils.GLOWSTONE, 'i', OreDictUtils.INGOT_IRON, 's', OreDictUtils.STICK_WOOD);

        this.withersBane = this.registry.registerItem(new ItemSword(this.config.getMaterial("wither_bane", 3, 1869, 8, 6, 16)), "wither_bane");
        this.decay = this.registry.registerEnchantment(this.config.getSwordEnchantment("decay", this.withersBane, Rarity.COMMON, 1, 3), "decay");
        this.consumingShadows = this.registry.registerEnchantment(this.config.getSwordEnchantment("consuming_shadows", this.withersBane, Rarity.RARE, 1, 1), "consuming_shadows");
        this.registry.addShapedRecipe("wither_bane", new ItemStack(this.withersBane), " ss", "qns", "kq ", 's', Blocks.SOUL_SAND, 'q', OreDictUtils.GEM_QUARTZ, 'n', OreDictUtils.NETHER_STAR, 'k', OreDictUtils.STICK_WOOD);

        this.adminiumArk = this.registry.registerItem(new ItemSword(this.config.getMaterial("adminium_ark", 3, 9999999, 8, 99999, 999)), "adminium_ark");
        this.stealth = this.registry.registerEnchantment(this.config.getSwordEnchantment("stealth", this.adminiumArk, Rarity.COMMON, 1, 1), "stealth");
        this.extinction = this.registry.registerEnchantment(this.config.getSwordEnchantment("extinction", this.adminiumArk, Rarity.RARE, 1, 1), "extinction");
        this.registry.addShapedRecipe("adminium_ark", new ItemStack(this.adminiumArk), " bb", "fcb", "sf ", 'b', Blocks.BEDROCK, 'f', Blocks.END_PORTAL_FRAME, 'c', Blocks.COMMAND_BLOCK, 's', OreDictUtils.STICK_WOOD);

        for (final String relicName : this.relicNames) {

            this.relics.add(this.registry.registerItem(new ItemSwordRelic(), "relic_" + relicName));
        }

        this.config.syncConfigData();
    }
}