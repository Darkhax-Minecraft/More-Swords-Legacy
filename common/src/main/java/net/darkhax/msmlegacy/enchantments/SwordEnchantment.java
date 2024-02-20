package net.darkhax.msmlegacy.enchantments;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.types.EnchantmentConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Optional;

public class SwordEnchantment extends Enchantment {

    private final EnchantmentConfig config;
    private final TagKey<Enchantment> incompatibleEnchantments;
    private final TagKey<Item> compatibleItems;

    public SwordEnchantment(Rarity rarity, String type, EnchantmentConfig config) {

        super(rarity, EnchantmentCategory.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
        this.config = config;
        incompatibleEnchantments = TagKey.create(Registries.ENCHANTMENT, new ResourceLocation(MSMContent.MOD_ID, "compatibility/incompatible_with_" + type));
        compatibleItems = TagKey.create(Registries.ITEM, new ResourceLocation(MSMContent.MOD_ID, "compatibility/compatible_with_" + type));
    }

    @Override
    public int getMinLevel() {
        return config.minLevel;
    }

    @Override
    public int getMaxLevel() {
        return config.maxLevel;
    }

    @Override
    public boolean isTradeable() {
        return config.isTradeable;
    }

    @Override
    public boolean isDiscoverable() {
        return config.isDiscoverable;
    }

    @Override
    public boolean checkCompatibility(Enchantment other) {

        final ResourceLocation enchantmentId = BuiltInRegistries.ENCHANTMENT.getKey(other);

        if (enchantmentId != null) {

            final Optional<Holder.Reference<Enchantment>> holder = BuiltInRegistries.ENCHANTMENT.getHolder(ResourceKey.create(Registries.ENCHANTMENT, enchantmentId));

            if (holder != null && holder.isPresent()) {

                return !holder.get().is(this.incompatibleEnchantments) && super.checkCompatibility(other);
            }
        }

        return false;
    }

    @Nullable
    public InteractionResultHolder<ItemStack> onItemUsed(Level worldLevel, Player player, InteractionHand hand, int level) {

        return null;
    }

    public void onHeldTick(ItemStack stack, Level level, Player user, int slotId, int enchLevel) {

    }

    public Component getName() {

        return Component.translatable(this.getDescriptionId());
    }

    @Override
    public boolean canEnchant(ItemStack item) {

        return item.is(this.compatibleItems);
    }

    /**
     * This method is provided to satisfy Forge and NeoForge APIs. It is not intended to be directly invoked and is not
     * considered part of the MSM API. Please use {@link #canEnchant(ItemStack)} instead.
     */
    @Deprecated
    public boolean canApplyAtEnchantingTable(ItemStack item) {

        return item.is(this.compatibleItems);
    }
}