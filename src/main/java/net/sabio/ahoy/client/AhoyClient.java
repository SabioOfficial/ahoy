package net.sabio.ahoy.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.sabio.ahoy.client.network.AhoyClientNetworking;
import net.sabio.ahoy.client.render.entity.ShipEntityRenderer;
import net.sabio.ahoy.registry.AhoyEntityTypes;

@Environment(EnvType.CLIENT)
public class AhoyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AhoyEntityTypes.SHIP, ShipEntityRenderer::new);
        AhoyClientNetworking.register();
        ShipInputHandler.register();
    }
}
