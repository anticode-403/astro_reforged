package me.anticode.astro_reforged;

import me.anticode.astro_reforged.init.AstroAttributes;
import me.anticode.astro_reforged.init.AstroEntities;
import me.anticode.astro_reforged.init.AstroItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AstroReforged implements ModInitializer {

    public static String modID = "astro_reforged";
    public static final Logger LOGGER = LogManager.getLogger(modID);

    @Override
    public void onInitialize() {
        AstroItems.initialize();
        AstroAttributes.initialize();
        AstroEntities.initialize();

        LootTableEvents.MODIFY.register((resourceManager, lootManager,
                                         id, supplier, setter) -> {
            if (setter.isBuiltin()) {
                if (id.equals(Identifier.of("minecraft", "chests/ancient_city"))) {
                    LootPool.Builder poolBuilder = LootPool.builder().with(
                            ItemEntry.builder(AstroItems.MAUVEINE_UPGRADE_SMITHING_TEMPLATE)
                    ).rolls(UniformLootNumberProvider.create(0, 1));
                    supplier.pool(poolBuilder);
                }
            }
        });
    }

    public static Identifier getId (String name) {
        return new Identifier(modID, name);
    }

    public static String getTranslation (String type, String name) {
        return type + "." + modID + "." + name;
    }
}
