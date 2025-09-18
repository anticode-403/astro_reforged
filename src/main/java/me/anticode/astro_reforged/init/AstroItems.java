package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AstroItems {
    public static final Item MAUVEINE_INGOT = register(new Item(new Item.Settings()), "mauveine_ingot");

    public static Item register (Item item, String id) {
        Identifier identifier = AstroReforged.getId(id);
        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
            itemGroup.add(MAUVEINE_INGOT);
        });
    }
}
