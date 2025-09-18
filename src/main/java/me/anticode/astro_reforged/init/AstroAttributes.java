package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.puffish.attributesmod.api.DynamicEntityAttribute;

public class AstroAttributes {
    public static final EntityAttribute GRAVITY_REDUCTION = register(AstroReforged.getId("generic.gravity_reduction"), new DynamicEntityAttribute(AstroReforged.getTranslation("attribute", "generic.gravity_reduction")));

    private static EntityAttribute register(Identifier id, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, id, attribute);
    }

    public static void initialize() {}
}
