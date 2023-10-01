package net.darkhax.msmlegacy;

import net.darkhax.bookshelf.api.function.CachedSupplier;
import net.darkhax.bookshelf.api.registry.RegistryObject;
import net.darkhax.msmlegacy.config.relics.RelicAqueousBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicBlazeSwordConfig;
import net.darkhax.msmlegacy.config.relics.RelicInfinityBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicKeyBladeConfig;
import net.darkhax.msmlegacy.config.relics.RelicMasterSword;
import net.darkhax.msmlegacy.config.relics.RelicMoltenBlade;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagManager;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.List;
import java.util.UUID;

public class RelicHooks {

    public static final CachedSupplier<Item> AQUEOUS_BLADE = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_aqueous_blade").cast();
    public static final CachedSupplier<Item> BLAZE_SWORD = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_blaze_sword").cast();
    public static final CachedSupplier<Item> INFINITY_BLADE = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_infinity_blade").cast();
    public static final TagKey<Biome> CAN_HEROES_SHADE_SPAWN = TagKey.create(Registries.BIOME, new ResourceLocation(MSMContent.MOD_ID, "can_heroes_shade_spawn"));
    public static final CachedSupplier<Item> MASTER_SWORD = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_master_sword").cast();
    public static final CachedSupplier<Item> KEY_BLADE = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_key_blade").cast();
    public static final CachedSupplier<Item> MOLTEN_BLADE = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_molten_blade").cast();
    public static final CachedSupplier<Item> PIE_CUTTER = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_pie_cutter").cast();
    public static final CachedSupplier<Item> ADMINIUM_ARK = RegistryObject.deferred(BuiltInRegistries.ITEM, MSMContent.MOD_ID, "relic_adminium_ark").cast();

    public static void setupDrowned(Drowned drowned, RandomSource rng, DifficultyInstance difficulty) {

        drowned.level().registryAccess().registries().forEach(e -> {

            System.out.println(e.key() + " - " + TagManager.getTagDir(e.key()));
        });
        final RelicAqueousBladeConfig config = MSMContent.CONFIG.relics.aqueousBlade;

        // If a drowned doesn't already have a held item it will have a chance to spawn with the aqueous blade relic.
        // The chance of this happening is 0.25% plus an additional 0.25% per point of local difficulty. The drowned
        // will also receive some major buffs.
        if (config.isEnabled() && !drowned.isBaby() && drowned.getMainHandItem().isEmpty() && rng.nextFloat() < config.getChance()) {

            drowned.setItemSlot(EquipmentSlot.MAINHAND, AQUEOUS_BLADE.get().getDefaultInstance());
            drowned.setDropChance(EquipmentSlot.MAINHAND, config.dropChance);

            drowned.getAttribute(Attributes.ARMOR).addPermanentModifier(modifier("bf1c00c1-7e71-4469-bdf8-6f36e0e87eda", "Aqueous Blade Armor Bonus", config.bonusArmor, AttributeModifier.Operation.ADDITION));
            drowned.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier("10f7a49c-8446-4060-a9d2-eaae3d4d6c44", "Aqueous Blade Damage Bonus", config.bonusDamage, AttributeModifier.Operation.ADDITION));
            drowned.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier("ca380065-55df-44f6-a78d-eac4bdd44126", "Aqueous Blade Health Bonus", config.bonusHealth, AttributeModifier.Operation.ADDITION));
            drowned.heal((float) config.bonusHealth);
        }
    }

    public static void injectPiglinBarteringTrades(Piglin piglin, List<ItemStack> trades) {

        final RelicBlazeSwordConfig config = MSMContent.CONFIG.relics.blazeSword;

        // When a piglin barters with a player they will have a chance to drop the blaze sword relic. The chance of
        // this is normally 0.5% however players that have not obtained the relic yet will have a 1% chance. The player
        // requirement is implemented to make this less farmable and to minimize the amount of clogging automated
        // bartering farms receive.
        if (config.isEnabled() && !piglin.isRemoved()) {

            final Player player = piglin.getBrain().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER).orElse(null);

            if (player instanceof ServerPlayer serverPlayer) {

                final float chance = !doesPlayerHaveAdvancement(serverPlayer, "relics/relic_blaze_sword") ? config.newPlayerChance : config.baseChance;

                if (piglin.getRandom().nextFloat() < chance) {

                    trades.add(BLAZE_SWORD.get().getDefaultInstance());
                }
            }
        }
    }

    public static void setupZombie(Zombie zombie, RandomSource rng, DifficultyInstance difficulty) {

        final RelicInfinityBladeConfig config = MSMContent.CONFIG.relics.infinityBladeConfig;

        // When a standard zombie spawns underground it will have a 0.1% chance to spawn with the infinity blade. This
        // zombie will also have iron armor and some stat boosts. Killing the zombie normally has a 100% chance for the
        // Infinity Blade relic to drop.
        if (config.isEnabled() && !zombie.isBaby() && zombie.getType() == EntityType.ZOMBIE && zombie.getMainHandItem().isEmpty() && rng.nextFloat() < config.getChance() && !zombie.level().canSeeSky(zombie.blockPosition())) {

            zombie.setItemSlot(EquipmentSlot.MAINHAND, INFINITY_BLADE.get().getDefaultInstance());
            zombie.setDropChance(EquipmentSlot.MAINHAND, config.dropChance);

            if (config.useCustomArmor) {

                zombie.setItemSlot(EquipmentSlot.HEAD, Items.IRON_HELMET.getDefaultInstance());
                zombie.setDropChance(EquipmentSlot.HEAD, config.armorDropChance);

                zombie.setItemSlot(EquipmentSlot.CHEST, Items.IRON_CHESTPLATE.getDefaultInstance());
                zombie.setDropChance(EquipmentSlot.CHEST, config.armorDropChance);

                zombie.setItemSlot(EquipmentSlot.LEGS, Items.IRON_LEGGINGS.getDefaultInstance());
                zombie.setDropChance(EquipmentSlot.LEGS, config.armorDropChance);

                zombie.setItemSlot(EquipmentSlot.FEET, Items.IRON_BOOTS.getDefaultInstance());
                zombie.setDropChance(EquipmentSlot.FEET, config.armorDropChance);
            }

            zombie.getAttribute(Attributes.ARMOR).addPermanentModifier(modifier("61ee61ca-f64b-4e65-9da8-2e57423a6253", "Infinity Blade Armor Bonus", config.bonusArmor, AttributeModifier.Operation.ADDITION));
            zombie.getAttribute(Attributes.ARMOR_TOUGHNESS).addPermanentModifier(modifier("61ee61ca-f64b-4e65-9da8-2e57423a6253", "Infinity Blade Armor Toughness Bonus", config.bonusArmorToughness, AttributeModifier.Operation.ADDITION));
            zombie.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(modifier("694644a4-75be-4bfa-ad42-fc5f7176ca2a", "Infinity Blade Damage Bonus", config.bonusDamage, AttributeModifier.Operation.ADDITION));
            zombie.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(modifier("42ff2949-af37-4edf-8164-5382360707e7", "Infinity Blade Health Bonus", config.bonusHealth, AttributeModifier.Operation.ADDITION));
            zombie.heal((float) config.bonusHealth);
        }
    }

    public static void setupSkeleton(AbstractSkeleton skeleton, RandomSource rng, DifficultyInstance difficulty) {

        final RelicMasterSword config = MSMContent.CONFIG.relics.masterSwordConfig;

        if (config.isEnabled() && !skeleton.isBaby() && skeleton.getType() == EntityType.SKELETON && rng.nextFloat() < config.getChance() && isBiome(skeleton.level(), skeleton.getOnPos(), CAN_HEROES_SHADE_SPAWN)) {

            AbstractSkeleton targetSkeleton = skeleton;

            if (config.replaceSkeleton) {

                targetSkeleton = skeleton.convertTo(EntityType.WITHER_SKELETON, false);
            }

            targetSkeleton.setItemSlot(EquipmentSlot.MAINHAND, MASTER_SWORD.get().getDefaultInstance());
            targetSkeleton.setDropChance(EquipmentSlot.MAINHAND, config.dropChance);

            if (config.useCustomShield) {

                final ItemStack shieldStack = Items.SHIELD.getDefaultInstance();
                final CompoundTag shieldData = new CompoundTag();
                shieldData.putInt("Base", DyeColor.RED.getId());
                shieldData.put("Patterns", new BannerPattern.Builder().addPattern(BannerPatterns.FLOWER, DyeColor.YELLOW).toListTag());
                BlockItem.setBlockEntityData(shieldStack, BlockEntityType.BANNER, shieldData);
                targetSkeleton.setItemSlot(EquipmentSlot.OFFHAND, shieldStack);
                targetSkeleton.setDropChance(EquipmentSlot.OFFHAND, config.shieldDropChance);
            }
        }
    }

    public static void onPlayerWinsRaid(ServerPlayer player) {

        final RelicKeyBladeConfig config = MSMContent.CONFIG.relics.keyBladeConfig;

        if (config.isEnabled()) {

            // When the player is involved in defeating a raid they will have a chance to get the key blade relic. The
            // first time this happens they will have a 100% chance to obtain the relic. All further wins will have a
            // 15% chance instead.
            final float dropChance = doesPlayerHaveAdvancement(player, "relics/relic_key_blade") ? config.baseChance : config.newPlayerChance;

            if (player.level().random.nextFloat() < dropChance) {

                player.drop(KEY_BLADE.get().getDefaultInstance(), true);
            }
        }
    }

    public static void setupWitherSkeleton(WitherSkeleton skeleton, RandomSource rng, DifficultyInstance difficulty) {

        final RelicMoltenBlade config = MSMContent.CONFIG.relics.moltenBladeConfig;

        if (!skeleton.isBaby() && config.isEnabled() && rng.nextFloat() < config.getChance()) {

            skeleton.setItemSlot(EquipmentSlot.MAINHAND, MOLTEN_BLADE.get().getDefaultInstance());
            skeleton.setDropChance(EquipmentSlot.MAINHAND, config.dropChance);
        }
    }

    public static ItemStack dye(Item item, DyeColor color) {

        final ItemStack stack = item.getDefaultInstance();
        stack.getOrCreateTagElement("display").putInt("color", color.getTextColor());
        return stack;
    }

    public static boolean isBiome(Level level, BlockPos pos, TagKey<Biome> biome) {

        return level.getBiome(pos).is(biome);
    }

    public static boolean doesPlayerHaveAdvancement(ServerPlayer player, String id) {

        return doesPlayerHaveAdvancement(player, new ResourceLocation(MSMContent.MOD_ID, id));
    }

    public static boolean doesPlayerHaveAdvancement(ServerPlayer player, ResourceLocation id) {

        final Advancement advancement = player.server.getAdvancements().getAdvancement(id);
        return advancement != null && player.getAdvancements().getOrStartProgress(advancement).isDone();
    }

    private static AttributeModifier modifier(String uuid, String name, double amount, AttributeModifier.Operation operation) {

        return new AttributeModifier(UUID.fromString(uuid), name, amount, operation);
    }
}