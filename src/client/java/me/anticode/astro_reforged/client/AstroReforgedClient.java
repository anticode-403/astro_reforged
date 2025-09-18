package me.anticode.astro_reforged.client;

import me.anticode.astro_reforged.entity.GravityBombEntity;
import me.anticode.astro_reforged.init.AstroEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class AstroReforgedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AstroEntities.HIGH_GRAVITY_BOMB, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(AstroEntities.LOW_GRAVITY_BOMB, FlyingItemEntityRenderer::new);
    }
}