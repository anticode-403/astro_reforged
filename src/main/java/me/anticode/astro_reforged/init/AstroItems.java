package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.item.HighGravityBombItem;
import me.anticode.astro_reforged.item.LowGravityBombItem;
import me.anticode.astro_reforged.item.MauveineArmorItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.List;

public class AstroItems {
    private static final Identifier EMPTY_ARMOR_SLOT_HELMET_TEXTURE = new Identifier("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE = new Identifier("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE = new Identifier("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_ARMOR_SLOT_BOOTS_TEXTURE = new Identifier("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = new Identifier("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = new Identifier("item/empty_slot_ingot");

    public static final Item MAUVEINE_INGOT = register(new Item(new Item.Settings()), "mauveine_ingot");
    public static final Item LOW_GRAVITY_BOMB = register(new LowGravityBombItem(new Item.Settings().maxCount(16)), "low_gravity_bomb");
    public static final Item HIGH_GRAVITY_BOMB = register(new HighGravityBombItem(new Item.Settings().maxCount(16)), "high_gravity_bomb");

    public static final Item MAUVEINE_HELMET = register(new MauveineArmorItem(ArmorItem.Type.HELMET, new Item.Settings()), "mauveine_helmet");
    public static final Item MAUVEINE_CHESTPLATE = register(new MauveineArmorItem(ArmorItem.Type.CHESTPLATE, new Item.Settings()), "mauveine_chestplate");
    public static final Item MAUVEINE_LEGGINGS = register(new MauveineArmorItem(ArmorItem.Type.LEGGINGS, new Item.Settings()), "mauveine_leggings");
    public static final Item MAUVEINE_BOOTS = register(new MauveineArmorItem(ArmorItem.Type.BOOTS, new Item.Settings()), "mauveine_boots");

    public static final Item MAUVEINE_SABRE = register(new SwordItem(AstroToolMaterials.MAUVEINE_TOOL_MATERIAL, 4, -2, new Item.Settings()), "mauveine_sabre");

    public static final String must_id = "mauveine_upgrade_smithing_template";
    public static final Item MAUVEINE_UPGRADE_SMITHING_TEMPLATE = register(new SmithingTemplateItem(
            Text.translatable(AstroReforged.getTranslation("item", must_id + ".applies_to")).formatted(Formatting.BLUE),
            Text.translatable(AstroReforged.getTranslation("item", must_id + ".ingredients")).formatted(Formatting.BLUE),
            Text.translatable(AstroReforged.getTranslation("item", must_id + ".title")).formatted(Formatting.GRAY),
            Text.translatable(AstroReforged.getTranslation("item", must_id + ".base_slot_desc")),
            Text.translatable(AstroReforged.getTranslation("item", must_id + ".add_slot_desc")),
            getEmptyBaseSlotTextures(),
            List.of(EMPTY_SLOT_INGOT_TEXTURE)
    ), must_id);

    public static Item register (Item item, String id) {
        Identifier identifier = AstroReforged.getId(id);
        return Registry.register(Registries.ITEM, identifier, item);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((itemGroup) -> {
            itemGroup.addAfter(new ItemStack(Items.NETHERITE_INGOT), MAUVEINE_INGOT);
            itemGroup.addAfter(new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), MAUVEINE_UPGRADE_SMITHING_TEMPLATE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> {
           itemGroup.addAfter(new ItemStack(Items.CROSSBOW), LOW_GRAVITY_BOMB);
           itemGroup.addAfter(new ItemStack(Items.CROSSBOW), HIGH_GRAVITY_BOMB);

           itemGroup.addAfter(new ItemStack(Items.NETHERITE_BOOTS), MAUVEINE_BOOTS);
           itemGroup.addAfter(new ItemStack(Items.NETHERITE_BOOTS), MAUVEINE_LEGGINGS);
           itemGroup.addAfter(new ItemStack(Items.NETHERITE_BOOTS), MAUVEINE_CHESTPLATE);
           itemGroup.addAfter(new ItemStack(Items.NETHERITE_BOOTS), MAUVEINE_HELMET);

           itemGroup.addAfter(new ItemStack(Items.NETHERITE_SWORD), MAUVEINE_SABRE);
        });
    }

    private static List<Identifier> getEmptyBaseSlotTextures() {
        return List.of(EMPTY_ARMOR_SLOT_HELMET_TEXTURE, EMPTY_SLOT_SWORD_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE_TEXTURE,
                EMPTY_ARMOR_SLOT_LEGGINGS_TEXTURE, EMPTY_ARMOR_SLOT_BOOTS_TEXTURE);
    }
}
