package net.darkhax.msmlegacy.item;

import net.darkhax.msmlegacy.MSMContent;
import net.darkhax.msmlegacy.config.SwordStatsConfig;
import net.darkhax.msmlegacy.enchantments.SwordEnchantment;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.Map;

public class MSMSwordItem extends SwordItem {

    private final TagKey<Item> repairMaterialTag;
    private final Ingredient repairMaterials;

    public MSMSwordItem(String type, Rarity rarity, SwordStatsConfig config) {

        super(config.getAstier(), 0, config.swingSpeedPenalty, new Item.Properties().stacksTo(1).rarity(rarity));
        this.repairMaterialTag = TagKey.create(Registries.ITEM, new ResourceLocation(MSMContent.MOD_ID, "repair/" + type));
        this.repairMaterials = Ingredient.of(this.repairMaterialTag);
    }

    @Override
    public boolean isValidRepairItem(ItemStack self, ItemStack material) {

        return this.repairMaterials.test(material);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity user, int slotId, boolean isSelected) {

        if (isSelected && user instanceof ServerPlayer serverPlayer) {

            for (Map.Entry<Enchantment, Integer> enchData : EnchantmentHelper.getEnchantments(stack).entrySet()) {

                if (enchData.getKey() instanceof SwordEnchantment swordEnchantment) {

                    swordEnchantment.onHeldTick(stack, level, serverPlayer, slotId, enchData.getValue());
                }
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        final ItemStack usedStack = player.getItemInHand(hand);

        for (Map.Entry<Enchantment, Integer> enchData : EnchantmentHelper.getEnchantments(usedStack).entrySet()) {

            if (enchData.getKey() instanceof SwordEnchantment swordEnchantment) {

                final InteractionResultHolder<ItemStack> result = swordEnchantment.onItemUsed(level, player, hand, enchData.getValue());

                if (result != null) {

                    return result;
                }
            }
        }

        return super.use(level, player, hand);
    }

    public final Ingredient getRepairMaterials() {

        return this.repairMaterials;
    }
}
