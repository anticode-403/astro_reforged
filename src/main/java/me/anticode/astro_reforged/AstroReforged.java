package me.anticode.astro_reforged;

import me.anticode.astro_reforged.init.AstroItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AstroReforged implements ModInitializer {

    public static String modID = "astro_reforged";
    public static final Logger LOGGER = LogManager.getLogger(modID);

    @Override
    public void onInitialize() {
        AstroItems.initialize();
    }

    public static Identifier getId (String name) {
        return new Identifier(modID, name);
    }
}
