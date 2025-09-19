package me.anticode.astro_reforged.mixin;

import gravity_changer.api.GravityChangerAPI;
import me.anticode.astro_reforged.api.AstroEntityInterface;
import me.anticode.astro_reforged.init.AstroAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.puffish.attributesmod.api.DynamicModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements AstroEntityInterface {
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

    @Inject(method = "createLivingAttributes", require = 1, allow = 1, at = @At("RETURN"))
    private static void createLivingAttributes(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue()
                .add(AstroAttributes.GRAVITY_REDUCTION);
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void tickMovement(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        if (livingEntity.getWorld().isClient()) return;
        double oldGravity = GravityChangerAPI.getGravityStrength(livingEntity);
        double gravityModifierTotal = 1.0D;
        for (double gravityModifier : gravityModifiers.values()) {
            gravityModifierTotal *= gravityModifier;
        }
        double gravityTarget = gravityModifierTotal;
        if (!livingEntity.isSneaking()) {
            gravityTarget = DynamicModification.create()
                    .withNegative(AstroAttributes.GRAVITY_REDUCTION, livingEntity)
                    .applyTo(gravityModifierTotal);
        }
        if (gravityTarget != oldGravity) {
            GravityChangerAPI.setBaseGravityStrength(livingEntity, gravityTarget);
        }
    }
}
