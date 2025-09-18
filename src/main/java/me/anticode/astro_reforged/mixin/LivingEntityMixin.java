package me.anticode.astro_reforged.mixin;

import gravity_changer.api.GravityChangerAPI;
import me.anticode.astro_reforged.AstroReforged;
import me.anticode.astro_reforged.init.AstroAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.puffish.attributesmod.api.DynamicModification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow
    public abstract AttributeContainer getAttributes();

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
        double gravityTarget = DynamicModification.create()
                .withNegative(AstroAttributes.GRAVITY_REDUCTION, livingEntity)
                .applyTo(1.0);
        if (gravityTarget != oldGravity) {
            GravityChangerAPI.setBaseGravityStrength(livingEntity, gravityTarget);
        }
    }
}
