package me.anticode.astro_reforged.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import me.anticode.astro_reforged.init.AstroArmorMaterials;
import me.anticode.astro_reforged.init.AstroAttributes;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.puffish.attributesmod.AttributesMod;

import java.util.EnumMap;
import java.util.UUID;

public class MauveineArmorItem extends ArmorItem {
    private static final EnumMap<Type, UUID> MODIFIERS = (EnumMap) Util.make(new EnumMap(ArmorItem.Type.class), (uuidMap) -> {
        uuidMap.put(ArmorItem.Type.BOOTS, UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"));
        uuidMap.put(ArmorItem.Type.LEGGINGS, UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"));
        uuidMap.put(ArmorItem.Type.CHESTPLATE, UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"));
        uuidMap.put(ArmorItem.Type.HELMET, UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150"));
    });

    public MauveineArmorItem(Type type, Settings settings) {
        super(AstroArmorMaterials.MAUVEINE_ARMOR_MATERIAL, type, settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(ItemStack stack, EquipmentSlot slot) {
        if (!slot.isArmorSlot()) return super.getAttributeModifiers(stack, slot);
        if (slot == this.getSlotType()) {
            UUID uuid = MODIFIERS.get(this.getType());
            ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
            builder.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(uuid, "Armor modifier", this.getProtection(), EntityAttributeModifier.Operation.ADDITION));
            builder.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(uuid, "Armor toughness", this.getToughness(), EntityAttributeModifier.Operation.ADDITION));
            builder.put(AstroAttributes.GRAVITY_REDUCTION, new EntityAttributeModifier(uuid, "Mauveine gravity reduction", 0.2, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
            builder.put(AttributesMod.FALL_REDUCTION, new EntityAttributeModifier(uuid, "Mauveine fall reduction", 0.25, EntityAttributeModifier.Operation.MULTIPLY_BASE));
            return builder.build();
        }
        return super.getAttributeModifiers(stack, slot);
    }
}
