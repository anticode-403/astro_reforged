package me.anticode.astro_reforged.mixin;

import gravity_changer.api.GravityChangerAPI;
import me.anticode.astro_reforged.api.AstroEntityInterface;
import me.anticode.astro_reforged.init.AstroAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.puffish.attributesmod.api.DynamicModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.UUID;

@Mixin(Entity.class)
public abstract class EntityMixin implements AstroEntityInterface {
    @Shadow
    public abstract boolean hasNoGravity();

    @Unique
    private final HashMap<UUID, Double> gravityModifiers = new HashMap<>();

    @Override
    public void astroReforged$setGravityBombModifier(double gravityModifier, UUID uuid) {
        gravityModifiers.putIfAbsent(uuid, gravityModifier);
    }

    @Override
    public void astroReforged$removeGravityBombModifier(UUID uuid) {
        gravityModifiers.remove(uuid);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void astroReforged$tick(CallbackInfo ci) {
        if (hasNoGravity()) return;
        Entity entity = (Entity) (Object) this;
        if (entity.getWorld().isClient()) return;
        if (entity instanceof LivingEntity) return;
        double oldGravity = GravityChangerAPI.getGravityStrength(entity);
        double gravityModifierTotal = 1.0D;
        for (double gravityModifier : gravityModifiers.values()) {
            gravityModifierTotal *= gravityModifier;
        }
        if (gravityModifierTotal != oldGravity) {
            GravityChangerAPI.setBaseGravityStrength(entity, gravityModifierTotal);
        }
    }
}
