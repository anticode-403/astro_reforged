package me.anticode.astro_reforged.client;

import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.init.AstroItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.ItemCriterion;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.function.Consumer;

public class AstroReforgedDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AstroReforgedModelProvider::new);
        pack.addProvider(AstroReforgedEnglishLangProvider::new);
        pack.addProvider(AstroReforgedRecipeProvider::new);
    }

    private static class AstroReforgedModelProvider extends FabricModelProvider {
        public AstroReforgedModelProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        }

        @Override
        public void generateItemModels(ItemModelGenerator itemModelGenerator) {
            itemModelGenerator.register(AstroItems.MAUVEINE_INGOT, Models.GENERATED);
            itemModelGenerator.register(AstroItems.LOW_GRAVITY_BOMB, Models.GENERATED);
            itemModelGenerator.register(AstroItems.HIGH_GRAVITY_BOMB, Models.GENERATED);

            itemModelGenerator.register(AstroItems.MAUVEINE_BOOTS, Models.GENERATED);
            itemModelGenerator.register(AstroItems.MAUVEINE_LEGGINGS, Models.GENERATED);
            itemModelGenerator.register(AstroItems.MAUVEINE_CHESTPLATE, Models.GENERATED);
            itemModelGenerator.register(AstroItems.MAUVEINE_HELMET, Models.GENERATED);

            itemModelGenerator.register(AstroItems.MAUVEINE_SABRE, Models.GENERATED);

            itemModelGenerator.register(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        }
    }

    private static class AstroReforgedEnglishLangProvider extends FabricLanguageProvider {
        protected AstroReforgedEnglishLangProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(AstroReforged.getTranslation("attribute", "generic.gravity_reduction"), "Gravity Reduction");
            translationBuilder.add(AstroItems.MAUVEINE_INGOT, "Mauveine Ingot");
            translationBuilder.add(AstroItems.LOW_GRAVITY_BOMB, "Low Gravity Bomb");
            translationBuilder.add(AstroItems.HIGH_GRAVITY_BOMB, "High Gravity Bomb");

            translationBuilder.add(AstroItems.MAUVEINE_BOOTS, "Mauveine Boots");
            translationBuilder.add(AstroItems.MAUVEINE_LEGGINGS, "Mauveine Leggings");
            translationBuilder.add(AstroItems.MAUVEINE_CHESTPLATE, "Mauveine Chestplate");
            translationBuilder.add(AstroItems.MAUVEINE_HELMET, "Mauveine Helmet");

            translationBuilder.add(AstroItems.MAUVEINE_SABRE, "Mauveine Sabre");

            translationBuilder.add(AstroReforged.getTranslation("item", AstroItems.must_id + ".applies_to"), "Diamond Armor and Sword");
            translationBuilder.add(AstroReforged.getTranslation("item", AstroItems.must_id + ".ingredients"), "Mauveine Ingot");
            translationBuilder.add(AstroReforged.getTranslation("item", AstroItems.must_id + ".title"), "Mauveine Upgrade");
            translationBuilder.add(AstroReforged.getTranslation("item", AstroItems.must_id + ".base_slot_desc"), "Add Diamond Gear");
            translationBuilder.add(AstroReforged.getTranslation("item", AstroItems.must_id + ".add_slot_desc"), "Add Mauveine Ingot");
        }
    }

    private static class AstroReforgedRecipeProvider extends FabricRecipeProvider {
        public AstroReforgedRecipeProvider(FabricDataOutput output) {
            super(output);
        }

        @Override
        public void generate(Consumer<RecipeJsonProvider> consumer) {
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, AstroItems.MAUVEINE_INGOT)
                    .input(Items.NETHERITE_SCRAP, 2)
                    .input(Items.AMETHYST_CLUSTER, 2)
                    .criterion("criterion?", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_SCRAP))
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, AstroItems.LOW_GRAVITY_BOMB, 16)
                    .pattern("###")
                    .pattern("#m#")
                    .pattern("#i#")
                    .input('#', Items.GUNPOWDER)
                    .input('m', AstroItems.MAUVEINE_INGOT)
                    .input('i', Items.IRON_INGOT)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, AstroItems.HIGH_GRAVITY_BOMB, 16)
                    .pattern("###")
                    .pattern("#m#")
                    .pattern("#g#")
                    .input('#', Items.GUNPOWDER)
                    .input('m', AstroItems.MAUVEINE_INGOT)
                    .input('g', Items.GOLD_INGOT)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer);
            ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE, 2)
                    .pattern("#t#")
                    .pattern("#a#")
                    .pattern("###")
                    .input('#', Items.DIAMOND)
                    .input('a', Items.AMETHYST_BLOCK)
                    .input('t', AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer);
            SmithingTransformRecipeJsonBuilder.create(
                            Ingredient.ofItems(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE),
                            Ingredient.ofItems(Items.DIAMOND_SWORD),
                            Ingredient.ofItems(AstroItems.MAUVEINE_INGOT),
                            RecipeCategory.COMBAT,
                            AstroItems.MAUVEINE_SABRE)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer, AstroReforged.getId("smithing/mauveine_sabre_smithing"));
            SmithingTransformRecipeJsonBuilder.create(
                            Ingredient.ofItems(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE),
                            Ingredient.ofItems(Items.DIAMOND_HELMET),
                            Ingredient.ofItems(AstroItems.MAUVEINE_INGOT),
                            RecipeCategory.COMBAT,
                            AstroItems.MAUVEINE_HELMET)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer, AstroReforged.getId("smithing/mauveine_helmet_smithing"));
            SmithingTransformRecipeJsonBuilder.create(
                            Ingredient.ofItems(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE),
                            Ingredient.ofItems(Items.DIAMOND_CHESTPLATE),
                            Ingredient.ofItems(AstroItems.MAUVEINE_INGOT),
                            RecipeCategory.COMBAT,
                            AstroItems.MAUVEINE_CHESTPLATE)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer, AstroReforged.getId("smithing/mauveine_chestplate_smithing"));
            SmithingTransformRecipeJsonBuilder.create(
                            Ingredient.ofItems(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE),
                            Ingredient.ofItems(Items.DIAMOND_LEGGINGS),
                            Ingredient.ofItems(AstroItems.MAUVEINE_INGOT),
                            RecipeCategory.COMBAT,
                            AstroItems.MAUVEINE_LEGGINGS)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer, AstroReforged.getId("smithing/mauveine_leggings_smithing"));
            SmithingTransformRecipeJsonBuilder.create(
                            Ingredient.ofItems(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE),
                            Ingredient.ofItems(Items.DIAMOND_BOOTS),
                            Ingredient.ofItems(AstroItems.MAUVEINE_INGOT),
                            RecipeCategory.COMBAT,
                            AstroItems.MAUVEINE_BOOTS)
                    .criterion("obtain_mauveine", InventoryChangedCriterion.Conditions.items(AstroItems.MAUVEINE_INGOT))
                    .offerTo(consumer, AstroReforged.getId("smithing/mauveine_boots_smithing"));
        }
    }
}
