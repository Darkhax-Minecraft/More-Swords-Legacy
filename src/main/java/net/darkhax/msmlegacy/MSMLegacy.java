package net.darkhax.msmlegacy;

import java.io.File;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "msmlegacy", name = "More Swords: Legacy", version = "@VERSION@", dependencies = "required-after:bookshelf@[2.3.577,)", certificateFingerprint = "@FINGERPRINT@")
public class MSMLegacy {

	private final CreativeTabs creativeTab = new CreativeTabMSMLegacy();
	private final RegistryHelper registry = new RegistryHelper("msmlegacy").setTab(creativeTab).enableAutoRegistration();
	private final ConfigurationHandler config = new ConfigurationHandler(new File("config/msmlegacy.cfg"));
	
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
	public void onPreInit(FMLPreInitializationEvent event) {
		
		 dawnStar = registry.registerItem(new ItemSword(config.getMaterial("dawn_star", 3, 1286, 8, 6, 22)), "dawn_star");		
		 ignite = registry.registerEnchantment(config.getSwordEnchantment("ignite", dawnStar, Rarity.COMMON,  1, 3), "ignite");
		 sparks = registry.registerEnchantment(config.getSwordEnchantment("sparks", dawnStar, Rarity.RARE, 1, 1), "sparks");
		
		 vampiricBlade = registry.registerItem(new ItemSword(config.getMaterial("vampiric_blade", 3, 812, 8, 7, 12)), "vampiric_blade");
		 feast = registry.registerEnchantment(config.getSwordEnchantment("feast", vampiricBlade, Rarity.COMMON,  1, 3), "feast");
		 vitality = registry.registerEnchantment(config.getSwordEnchantment("vitality", vampiricBlade, Rarity.RARE, 1, 1), "vitality");

		 gladiolus = registry.registerItem(new ItemSword(config.getMaterial("gladiolus", 3, 645, 8, 6, 10)), "gladiolus");
		 venomousAspect = registry.registerEnchantment(config.getSwordEnchantment("venomous_aspect", gladiolus, Rarity.COMMON,  1, 3), "venomous_aspect");
		 absorb = registry.registerEnchantment(config.getSwordEnchantment("absorb", gladiolus, Rarity.RARE, 1, 1), "absorb");
		
		 draconicBlade = registry.registerItem(new ItemSword(config.getMaterial("draconic_blade", 3, 1089, 8, 7, 16)), "draconic_blade");
		 keenEdge = registry.registerEnchantment(config.getSwordEnchantment("keen_edge", draconicBlade, Rarity.COMMON,  1, 3), "keen_edge");
		 scorn = registry.registerEnchantment(config.getSwordEnchantment("scorn", draconicBlade, Rarity.RARE, 1, 1), "scorn");
		
		 eyeEndBlade = registry.registerItem(new ItemSword(config.getMaterial("eye_end_blade", 3, 1580, 8, 8, 22)), "eye_end_blade");
		 enderPulse = registry.registerEnchantment(config.getSwordEnchantment("ender_pulse", eyeEndBlade, Rarity.COMMON,  1, 3), "ender_pulse");
		 enderAura = registry.registerEnchantment(config.getSwordEnchantment("ender_aura", eyeEndBlade, Rarity.RARE, 1, 1), "ender_aura");
		
		 crystalineBlade = registry.registerItem(new ItemSword(config.getMaterial("crystaline_blade", 3, 570, 8, 5, 28)), "crystaline_blade");
		 greed = registry.registerEnchantment(config.getSwordEnchantment("greed", crystalineBlade, Rarity.COMMON,  1, 3), "greed");
		 wisdom = registry.registerEnchantment(config.getSwordEnchantment("wisdom", crystalineBlade, Rarity.RARE, 1, 1), "wisdom");
		
		 glacialBlade = registry.registerItem(new ItemSword(config.getMaterial("glacial_blade", 3, 680, 8, 6, 15)), "glacial_blade");
		 frozenAspect = registry.registerEnchantment(config.getSwordEnchantment("frozen_aspect", glacialBlade, Rarity.COMMON,  1, 3), "frozen_aspect");
		 frostWave = registry.registerEnchantment(config.getSwordEnchantment("frost_wave", glacialBlade, Rarity.RARE, 1, 1), "frost_wave");
		
		 aeithersGuard = registry.registerItem(new ItemSword(config.getMaterial("aethers_guard", 3, 1796, 8, 8, 22)), "aethers_guard");
		 ascension = registry.registerEnchantment(config.getSwordEnchantment("ascension", aeithersGuard, Rarity.COMMON,  1, 3), "ascension");
		 descension = registry.registerEnchantment(config.getSwordEnchantment("descension", aeithersGuard, Rarity.RARE, 1, 1), "descension");
		
		 withersBane = registry.registerItem(new ItemSword(config.getMaterial("wither_bane", 3, 1869, 8, 6, 16)), "wither_bane");
		 consumingShadows = registry.registerEnchantment(config.getSwordEnchantment("consuming_shadows", withersBane, Rarity.COMMON,  1, 3), "consuming_shadows");
		 decay = registry.registerEnchantment(config.getSwordEnchantment("decay", withersBane, Rarity.RARE, 1, 1), "decay");
		
		 adminiumArk = registry.registerItem(new ItemSword(config.getMaterial("adminium_ark", 3, 9999999, 8, 99999, 999)), "adminium_ark");
		 stealth = registry.registerEnchantment(config.getSwordEnchantment("stealth", adminiumArk, Rarity.COMMON,  1, 3), "stealth");
		 extinction = registry.registerEnchantment(config.getSwordEnchantment("extinction", adminiumArk, Rarity.RARE, 1, 1), "extinction");
		 
		 config.syncConfigData();
	}
}