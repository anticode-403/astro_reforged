package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class AstroAttributes {
    public static final EntityAttribute GRAVITY = register(AstroReforged.getId("generic.gravity"), new ClampedEntityAttribute(AstroReforged.getTranslation("attribute", "generic.gravity"), 1.0d, -20.0d, 20.0d));

    private static EntityAttribute register(Identifier id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, id, attribute);
    }

    public static void initialize() {}
}
