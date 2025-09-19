package me.anticode.astro_reforged.api;

import java.util.UUID;

public interface AstroEntityInterface {
    void astroReforged$setGravityBombModifier(double gravityModifier, UUID uuid);
    void astroReforged$removeGravityBombModifier(UUID uuid);
}
