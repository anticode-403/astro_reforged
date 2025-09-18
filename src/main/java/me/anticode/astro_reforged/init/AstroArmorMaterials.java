package me.anticode.astro_reforged.init;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class AstroArmorMaterials {
    public static final MauveineArmorMaterial MAUVEINE_ARMOR_MATERIAL = new MauveineArmorMaterial();

    public static class MauveineArmorMaterial implements ArmorMaterial {

        @Override
        public int getDurability(ArmorItem.Type type) {
            int DURABILITY_MULTIPLIER = 25;
            return switch (type) {
                case BOOTS -> 13 *  DURABILITY_MULTIPLIER;
                case LEGGINGS -> 15 *  DURABILITY_MULTIPLIER;
                case CHESTPLATE -> 16 *  DURABILITY_MULTIPLIER;
                case HELMET -> 11 *  DURABILITY_MULTIPLIER;
                default -> 0;
            };
        }

        @Override
        public int getProtection(ArmorItem.Type type) {
            return switch (type) {
                case BOOTS, HELMET -> 3;
                case LEGGINGS -> 6;
                case CHESTPLATE -> 8;
                default -> 0;
            };
        }

        @Override
        public int getEnchantability() {
            return 19;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(AstroItems.MAUVEINE_INGOT);
        }

        @Override
        public String getName() {
            return "mauveine";
        }

        @Override
        public float getToughness() {
            return 2;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}
