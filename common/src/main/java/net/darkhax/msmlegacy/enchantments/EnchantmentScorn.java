package net.darkhax.msmlegacy.enchantments;

import net.darkhax.bookshelf.api.registry.RegistryObject;
import net.darkhax.msmlegacy.MSMContent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.function.Supplier;

public class EnchantmentScorn extends SwordEnchantment {

    private static TagKey<DimensionType> ignored_dimensions = TagKey.create(Registries.DIMENSION_TYPE, new ResourceLocation(MSMContent.MOD_ID, "scorn_inactive_types"));
    private static Supplier<Enchantment> scorn = RegistryObject.deferred(BuiltInRegistries.ENCHANTMENT, new ResourceLocation(MSMContent.MOD_ID, "scorn")).cast();

    public EnchantmentScorn(String type) {

        super(Rarity.UNCOMMON, type, MSMContent.CONFIG.enchantments.scorn);
    }

    public static float modifyBaseDamage(Player player, float baseDamage) {

        final int scornLevel = EnchantmentHelper.getEnchantmentLevel(scorn.get(), player);

        if (scornLevel > 0 && !player.level().dimensionTypeRegistration().is(ignored_dimensions)) {

            return baseDamage * (MSMContent.CONFIG.enchantments.scorn.damageModifier.getValue(scornLevel));
        }

        return baseDamage;
    }
}