package me.anticode.astro_reforged.init;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AstroToolMaterials {
    public static final MauveineToolMaterial MAUVEINE_TOOL_MATERIAL = new MauveineToolMaterial();

    private static class MauveineToolMaterial implements ToolMaterial {

        @Override
        public int getDurability() {
            return 1561;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 1;
        }

        @Override
        public float getAttackDamage() {
            return 1;
        }

        @Override
        public int getMiningLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 8;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(AstroItems.MAUVEINE_INGOT);
        }
    }
}
