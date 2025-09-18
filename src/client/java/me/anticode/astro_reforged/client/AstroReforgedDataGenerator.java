package me.anticode.astro_reforged.client;

import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.init.AstroItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class AstroReforgedDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AstroReforgedModelProvider::new);
        pack.addProvider(AstroReforgedEnglishLangProvider::new);
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
        }
    }

    private static class AstroReforgedEnglishLangProvider extends FabricLanguageProvider {
        protected AstroReforgedEnglishLangProvider(FabricDataOutput dataOutput) {
            super(dataOutput);
        }

        @Override
        public void generateTranslations(TranslationBuilder translationBuilder) {
            translationBuilder.add(AstroReforged.getTranslation("attribute", "generic.gravity"), "Gravity");
            translationBuilder.add(AstroItems.MAUVEINE_INGOT, "Mauveine Ingot");
            translationBuilder.add(AstroItems.LOW_GRAVITY_BOMB, "Low Gravity Bomb");
            translationBuilder.add(AstroItems.HIGH_GRAVITY_BOMB, "High Gravity Bomb");
            translationBuilder.add(AstroItems.MAUVEINE_BOOTS, "Mauveine Boots");
            translationBuilder.add(AstroItems.MAUVEINE_LEGGINGS, "Mauveine Leggings");
            translationBuilder.add(AstroItems.MAUVEINE_CHESTPLATE, "Mauveine Chestplate");
            translationBuilder.add(AstroItems.MAUVEINE_HELMET, "Mauveine Helmet");
        }
    }
}
