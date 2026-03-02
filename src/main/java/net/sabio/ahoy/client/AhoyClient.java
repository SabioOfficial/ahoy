package net.sabio.ahoy.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.client.model.ShipEntityModel;
import net.sabio.ahoy.client.network.AhoyClientNetworking;
import net.sabio.ahoy.client.render.entity.ShipEntityRenderer;
import net.sabio.ahoy.registry.AhoyEntityTypes;

@Environment(EnvType.CLIENT)
public class AhoyClient implements ClientModInitializer {
    public static final EntityModelLayer SHIP_LAYER = new EntityModelLayer(Identifier.of(Ahoy.MOD_ID, "ship"), "main");

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(AhoyEntityTypes.SHIP, ShipEntityRenderer::new);
        AhoyClientNetworking.register();
        ShipInputHandler.register();
        EntityModelLayerRegistry.registerModelLayer(SHIP_LAYER, ShipEntityModel::getTexturedModelData);
    }
}
