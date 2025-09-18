package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.item.HighGravityBombItem;
import me.anticode.astro_reforged.item.LowGravityBombItem;
import me.anticode.astro_reforged.item.MauveineArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AstroItems {
    public static final Item MAUVEINE_INGOT = register(new Item(new Item.Settings()), "mauveine_ingot");
    public static final Item LOW_GRAVITY_BOMB = register(new LowGravityBombItem(new Item.Settings()), "low_gravity_bomb");
    public static final Item HIGH_GRAVITY_BOMB = register(new HighGravityBombItem(new Item.Settings()), "high_gravity_bomb");
    public static final Item MAUVEINE_HELMET = register(new MauveineArmorItem(ArmorItem.Type.HELMET, new Item.Settings()), "mauveine_helmet");
    public static final Item MAUVEINE_CHESTPLATE = register(new MauveineArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Settings()), "mauveine_chestplate");
    public static final Item MAUVEINE_LEGGINGS = register(new MauveineArmorItem(ArmorItem.Type.LEGGINGS, new Item.Settings()), "mauveine_leggings");
    public static final Item MAUVEINE_BOOTS = register(new MauveineArmorItem(ArmorItem.Type.BOOTS, new Item.Settings()), "mauveine_boots");

    public static Item register (Item item, String id) {
        Identifier identifier = AstroReforged.getId(id);
        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
            itemGroup.add(MAUVEINE_INGOT);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> {
           itemGroup.add(LOW_GRAVITY_BOMB);
           itemGroup.add(HIGH_GRAVITY_BOMB);

           itemGroup.add(MAUVEINE_HELMET);
           itemGroup.add(MAUVEINE_CHESTPLATE);
           itemGroup.add(MAUVEINE_LEGGINGS);
           itemGroup.add(MAUVEINE_BOOTS);
        });
    }
}
