package me.anticode.astro_reforged.init;

import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.entity.GravityBombEntity;
import me.anticode.astro_reforged.entity.LowGravityBombEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class AstroEntities {
    public static final EntityType<GravityBombEntity> HIGH_GRAVITY_BOMB = Registry.register(
            Registries.ENTITY_TYPE,
            AstroReforged.getId("gravity_bomb"),
            FabricEntityTypeBuilder.<GravityBombEntity>create(SpawnGroup.MISC, GravityBombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );
    public static final EntityType<LowGravityBombEntity> LOW_GRAVITY_BOMB = Registry.register(
            Registries.ENTITY_TYPE,
            AstroReforged.getId("low_gravity_bomb"),
            FabricEntityTypeBuilder.<LowGravityBombEntity>create(SpawnGroup.MISC, LowGravityBombEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                    .trackRangeBlocks(64).trackedUpdateRate(10)
                    .build()
    );
}
