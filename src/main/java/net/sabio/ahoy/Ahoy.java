package net.sabio.ahoy;

import net.fabricmc.api.ModInitializer;
import net.sabio.ahoy.config.AhoyConfig;
import net.sabio.ahoy.network.AhoyNetworking;
import net.sabio.ahoy.registry.AhoyBlocks;
import net.sabio.ahoy.registry.AhoyDataComponents;
import net.sabio.ahoy.registry.AhoyEntityTypes;
import net.sabio.ahoy.registry.AhoyItems;

import java.util.logging.Logger;

public class Ahoy implements ModInitializer {
    public static final String MOD_ID = "ahoy";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[Ahoy] Initializing...");
        AhoyConfig.init();
        AhoyDataComponents.register();
        AhoyBlocks.register();
        AhoyItems.register();
        AhoyEntityTypes.register();
        AhoyNetworking.registerServerPackets();
        LOGGER.info("[Ahoy] Initialized!");
    }
}
