package me.anticode.astro_reforged.entity;

import gravity_changer.api.GravityChangerAPI;
import me.anticode.astro_reforged.init.AstroEntities;
import me.anticode.astro_reforged.init.AstroItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class LowGravityBombEntity extends GravityBombEntity {
    public LowGravityBombEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.gravityBombType = GravityBombType.LOW;
    }

    public LowGravityBombEntity(World world, LivingEntity owner) {
        super(GravityBombType.LOW, world, owner);
        this.gravityBombType = GravityBombType.LOW;
    }

    @Override
    protected Item getDefaultItem() {
        return AstroItems.LOW_GRAVITY_BOMB;
    }
}
