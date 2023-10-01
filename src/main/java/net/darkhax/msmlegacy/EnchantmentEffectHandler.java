package net.darkhax.msmlegacy;

import java.util.List;

import net.darkhax.bookshelf.util.EntityUtils;
import net.darkhax.bookshelf.util.MathsUtils;
import net.darkhax.msmlegacy.item.ItemSwordRelic;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.SpecialSpawn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@EventBusSubscriber(modid = "msmlegacy")
public class EnchantmentEffectHandler {

    @Instance("msmlegacy")
    public static MSMLegacy msm;

    @SubscribeEvent
    public static void onDamageMob (LivingHurtEvent event) {

        if (event.getSource().getTrueSource() instanceof EntityLivingBase) {

            final EntityLivingBase target = event.getEntityLiving();
            final EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
            final ItemStack heldItem = attacker.getHeldItemMainhand();

            if (!heldItem.isEmpty()) {

                checkAndApplyEffect(msm.ignite, target, attacker, heldItem, EnchantmentEffectHandler::handleIgniteEffect, event);
                checkAndApplyEffect(msm.sparks, target, attacker, heldItem, EnchantmentEffectHandler::handleSparksEffect, event);
                checkAndApplyEffect(msm.feast, target, attacker, heldItem, EnchantmentEffectHandler::handleFeastEffect, event);
                checkAndApplyEffect(msm.venomousAspect, target, attacker, heldItem, EnchantmentEffectHandler::handleVenomousAspect, event);
                checkAndApplyEffect(msm.absorb, target, attacker, heldItem, EnchantmentEffectHandler::handleAbsorbEffect, event);
                checkAndApplyEffect(msm.scorn, target, attacker, heldItem, EnchantmentEffectHandler::handleScornEffect, event);
                checkAndApplyEffect(msm.greed, target, attacker, heldItem, EnchantmentEffectHandler::handleGreedEffect, event);
                checkAndApplyEffect(msm.wisdom, target, attacker, heldItem, EnchantmentEffectHandler::handleWisdomEffect, event);
                checkAndApplyEffect(msm.frozenAspect, target, attacker, heldItem, EnchantmentEffectHandler::handleFrozenAspectEffect, event);
                checkAndApplyEffect(msm.frostWave, target, attacker, heldItem, EnchantmentEffectHandler::handleFrostWaveEffect, event);
                checkAndApplyEffect(msm.ascension, target, attacker, heldItem, EnchantmentEffectHandler::handleAscensionEffect, event);
                checkAndApplyEffect(msm.decay, target, attacker, heldItem, EnchantmentEffectHandler::handleDecayEffect, event);
                checkAndApplyEffect(msm.consumingShadows, target, attacker, heldItem, EnchantmentEffectHandler::handleConsumingShadowEffect, event);
                checkAndApplyEffect(msm.extinction, target, attacker, heldItem, EnchantmentEffectHandler::handleExtinctionEffect, event);
            }
        }

        final EntityLivingBase user = event.getEntityLiving();
        final ItemStack userItem = user.getHeldItemMainhand();

        if (!userItem.isEmpty()) {

            checkAndApplyEffect(msm.enderAura, user, userItem, EnchantmentEffectHandler::handleEnderAura, event);
        }
    }

    @SubscribeEvent
    public static void onItemUsedEvent (PlayerInteractEvent.RightClickItem event) {

        if (!event.getItemStack().isEmpty() && event.getHand() == EnumHand.MAIN_HAND) {

            checkAndApplyEffect(msm.vitality, event.getEntityPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleVitalityEffect, event);
            checkAndApplyEffect(msm.enderPulse, event.getEntityPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleEnderPulseEffect, event);
            checkAndApplyEffect(msm.stealth, event.getEntityPlayer(), event.getItemStack(), EnchantmentEffectHandler::handleStealthEffect, event);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick (TickEvent.PlayerTickEvent event) {

        final EntityPlayer player = event.player;
        final ItemStack heldItem = player.getHeldItemMainhand();

        if (!heldItem.isEmpty()) {

            checkAndApplyEffect(msm.descension, player, heldItem, EnchantmentEffectHandler::handleDescensionEffect);
        }
    }

    @SubscribeEvent
    public static void onSpecialSpawn (SpecialSpawn event) {

        if ((event.getEntity() instanceof EntitySkeleton || event.getEntity() instanceof EntityZombie) && MathsUtils.tryPercentage(MSMLegacy.config.getSpawnChance())) {

            final Item item = msm.registry.getItems().get(event.getWorld().rand.nextInt(msm.registry.getItems().size()));

            if (item != msm.adminiumArk && (MSMLegacy.config.isAllowRelics() || !(item instanceof ItemSwordRelic))) {

                event.getEntityLiving().setHeldItem(EnumHand.MAIN_HAND, new ItemStack(item));
            }
        }
    }

    private static void handleExtinctionEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        for (final Entity entity : target.world.loadedEntityList) {

            if (entity instanceof EntityLiving && entity != attacker && entity.getClass() == target.getClass()) {

                entity.setDead();
            }
        }
    }

    private static void handleConsumingShadowEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        for (final EntityLivingBase entity : EntityUtils.<EntityLivingBase> getEntitiesInArea(EntityLivingBase.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker) {

                entity.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60 * level, level));
                target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 60 * level, level));
            }
        }
    }

    private static void handleDecayEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60 * level));
        target.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 60 * level, level));
    }

    private static void handleDescensionEffect (EntityLivingBase user, ItemStack item, int level) {

        if (user.isSneaking()) {

            user.motionY *= 0.6;
            user.fallDistance = 0;
        }
    }

    private static void handleFrostWaveEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        for (final EntityLivingBase entity : EntityUtils.<EntityLivingBase> getEntitiesInArea(EntityLivingBase.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker) {

                entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100 * level, level));
            }
        }
    }

    private static void handleFrozenAspectEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200 * level, level));
    }

    private static void handleAscensionEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 30, 4 * level));
    }

    private static void handleEnderAura (EntityLivingBase user, ItemStack item, int level, LivingHurtEvent event) {

        for (int i = 0; i < 3; i++) {

            if (MathsUtils.tryPercentage(0.30d)) {

                final AxisAlignedBB bounds = user.getEntityBoundingBox().grow(30d);
                final List<Entity> entities = user.world.getEntitiesInAABBexcluding(user, bounds, entity -> entity instanceof EntityLivingBase);

                if (!entities.isEmpty()) {

                    final Entity randomEntity = entities.get(user.world.rand.nextInt(entities.size()));

                    if (randomEntity instanceof EntityLivingBase) {

                        final EntityLivingBase living = (EntityLivingBase) randomEntity;

                        user.setPositionAndUpdate(living.posX, living.posY, living.posZ);
                    }
                }
            }
        }
    }

    private static void handleEnderPulseEffect (EntityLivingBase user, ItemStack item, int level, RightClickItem event) {

        final RayTraceResult results = MathsUtils.rayTrace(user, 16d * level);

        if (results != null && results.typeOfHit == Type.BLOCK) {

            final BlockPos tpPos = results.getBlockPos().offset(results.sideHit);
            user.setPositionAndUpdate(tpPos.getX() + 0.5, tpPos.getY(), tpPos.getZ() + 0.5f);
            item.damageItem(50, user);
            user.attackEntityFrom(DamageSource.FALL, 1);
        }
    }

    private static void handleGreedEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.isNonBoss() && MathsUtils.tryPercentage(0.07 * level) && attacker instanceof EntityPlayer) {

            final EntityPlayer player = (EntityPlayer) attacker;
            int exp = EntityUtils.getExperiencePoints(target, player);
            exp = net.minecraftforge.event.ForgeEventFactory.getExperienceDrop(target, player, exp);

            attacker.world.spawnEntity(new EntityXPOrb(target.world, target.posX, target.posY, target.posZ, exp));
        }
    }

    private static void handleWisdomEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof EntityPlayer) {

            event.setAmount(event.getAmount() + Math.min(0.625f * level * (((EntityPlayer) attacker).experienceLevel / 25f), 5f * level));
        }
    }

    private static void handleScornEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (target.dimension != 0) {

            event.setAmount(event.getAmount() * (level + 1));
        }
    }

    private static void handleAbsorbEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (attacker instanceof EntityPlayer && MathsUtils.tryPercentage(0.05 * level)) {

            final EntityPlayer player = (EntityPlayer) attacker;
            final int foodAmount = MathsUtils.nextIntInclusive(0, 2);
            player.getFoodStats().addStats(foodAmount, foodAmount);
        }
    }

    private static void handleVenomousAspect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        target.addPotionEffect(new PotionEffect(MobEffects.POISON, 120 * level, level));
    }

    private static void handleStealthEffect (EntityLivingBase user, ItemStack item, int level, RightClickItem event) {

        user.setInvisible(!user.isInvisible());
    }

    private static void handleVitalityEffect (EntityLivingBase attacker, ItemStack item, int level, RightClickItem event) {

        item.damageItem(128, attacker);
        attacker.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 600, level - 1));
        attacker.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 10, level - 1));
        attacker.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 500, level - 1));
    }

    private static void handleFeastEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (item.getItemDamage() > 0 && MathsUtils.tryPercentage(0.10 * level)) {

            item.setItemDamage(Math.max(0, item.getItemDamage() - MathsUtils.nextIntInclusive(1 * level, 3 * level)));
        }
    }

    private static void handleSparksEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        for (final EntityLivingBase entity : EntityUtils.<EntityLivingBase> getEntitiesInArea(EntityLivingBase.class, target.getEntityWorld(), target.getPosition(), level)) {

            if (entity != attacker && entity != target && !entity.isImmuneToFire() && !entity.isPotionActive(MobEffects.FIRE_RESISTANCE)) {

                entity.setFire(4 + level);
                target.attackEntityFrom(DamageSource.ON_FIRE, level);
            }
        }
    }

    private static void handleIgniteEffect (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event) {

        if (!target.isImmuneToFire() && !target.isPotionActive(MobEffects.FIRE_RESISTANCE)) {

            target.attackEntityFrom(DamageSource.ON_FIRE, level * 2f);
            target.setFire(1);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, EntityLivingBase target, EntityLivingBase attacker, ItemStack heldItem, EnchantmentEffectAttack effect, LivingHurtEvent event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(attacker, target, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, EntityLivingBase attacker, ItemStack heldItem, EnchantmentEffectItemUse effect, RightClickItem event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(attacker, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchant, EntityLivingBase user, ItemStack heldItem, EnchantmentEffectAttacked effect, LivingHurtEvent event) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchant, heldItem);

        if (level > 0) {

            effect.apply(user, heldItem, level, event);
        }
    }

    private static void checkAndApplyEffect (Enchantment enchantment, EntityLivingBase user, ItemStack heldItem, EnchantmentEffectTick effect) {

        final int level = EnchantmentHelper.getEnchantmentLevel(enchantment, heldItem);

        if (level > 0) {

            effect.apply(user, heldItem, level);
        }
    }

    private interface EnchantmentEffectAttack {

        void apply (EntityLivingBase attacker, EntityLivingBase target, ItemStack item, int level, LivingHurtEvent event);
    }

    private interface EnchantmentEffectItemUse {

        void apply (EntityLivingBase attacker, ItemStack item, int level, RightClickItem event);
    }

    private interface EnchantmentEffectAttacked {

        void apply (EntityLivingBase user, ItemStack item, int level, LivingHurtEvent event);
    }

    private interface EnchantmentEffectTick {

        void apply (EntityLivingBase user, ItemStack item, int level);
    }
}