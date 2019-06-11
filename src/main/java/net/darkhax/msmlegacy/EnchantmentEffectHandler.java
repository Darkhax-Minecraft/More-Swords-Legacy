package net.darkhax.msmlegacy;

import net.darkhax.bookshelf.util.EntityUtils;
import net.darkhax.bookshelf.util.MathsUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = "msmlegacy")
public class EnchantmentEffectHandler {

	@Instance("msmlegacy")
	public static MSMLegacy msm;
	
	@SubscribeEvent
	public static void onDamageMob(LivingHurtEvent event) {
		
		if (event.getSource().getTrueSource() instanceof EntityLivingBase ) {
			
			final EntityLivingBase target = event.getEntityLiving();
			final EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
			final ItemStack heldItem = attacker.getHeldItemMainhand();
			
			if (!heldItem.isEmpty()) {
				
				checkAndApplyEffect(msm.ignite, target, attacker, heldItem, EnchantmentEffectHandler::handleIgniteEffect);
				checkAndApplyEffect(msm.sparks, target, attacker, heldItem, EnchantmentEffectHandler::handleSparksEffect);
				checkAndApplyEffect(msm.feast, target, attacker, heldItem, EnchantmentEffectHandler::handleFeastEffect);
				checkAndApplyEffect(msm.venomousAspect, target, attacker, heldItem, EnchantmentEffectHandler::handleVenomousAspect);
				checkAndApplyEffect(msm.absorb, target, attacker, heldItem, EnchantmentEffectHandler::handleAbsorbEffect);
			}
		}
	}
	
	@SubscribeEvent
	public static void onItemUsedEvent(PlayerInteractEvent.RightClickItem event) {
		
		if (!event.getItemStack().isEmpty()) {
			
			checkAndApplyEffect(msm.vitality, event.getEntityPlayer(), event.getEntityPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleVitalityEffect);
		}
	}
	
	private static void handleAbsorbEffect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		if (attacker instanceof EntityPlayer && MathsUtils.tryPercentage(0.05 * level)) {
			
			final EntityPlayer player = (EntityPlayer) attacker;
			final int foodAmount = MathsUtils.nextIntInclusive(0, 2);
			player.getFoodStats().addStats(foodAmount, foodAmount);
		}
	}
	
	private static void handleVenomousAspect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		target.addPotionEffect(new PotionEffect(MobEffects.POISON, 120 * level, 0));
	}
	
	private static void handleVitalityEffect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		item.damageItem(128, attacker);
		attacker.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, level - 1));
		attacker.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, level - 1));
		attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 500, level - 1));
	}
	
	private static void handleFeastEffect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		if (item.getItemDamage() > 0 && MathsUtils.tryPercentage(0.10 * level)) {
			
			item.setItemDamage(Math.max(0, item.getItemDamage() - MathsUtils.nextIntInclusive(1 * level, 3 * level)));
		}
	}
	
	private static void handleSparksEffect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		for (EntityLivingBase entity : EntityUtils.<EntityLivingBase>getEntitiesInArea(EntityLivingBase.class, target.getEntityWorld(), target.getPosition(), level)) {
			
			if (entity != attacker && entity != target && !entity.isImmuneToFire() && !entity.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
				
				entity.setFire(4 + level);
				target.attackEntityFrom(DamageSource.ON_FIRE, level);
			}
		}
	}
	
	private static void handleIgniteEffect(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level) {
		
		if (!target.isImmuneToFire() && !target.isPotionActive(MobEffects.FIRE_RESISTANCE)) {
			
			target.attackEntityFrom(DamageSource.ON_FIRE, level * 2f);
			target.setFire(1);
			
			if (target instanceof EntityCreeper) {
				
				((EntityCreeper) target).ignite();
			}
		}
	}
	
	private static void checkAndApplyEffect(Enchantment enchant, EntityLivingBase target, EntityLivingBase attacker, ItemStack heldItem, EnchantmentEffect effect) {
		
		final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);
		
		if (level > 0) {
			
			effect.apply(attacker, target, heldItem, level);
		}
	}
	
	private interface EnchantmentEffect {
		
		void apply(EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level);
	}
}