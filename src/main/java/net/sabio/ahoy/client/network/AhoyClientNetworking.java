package net.sabio.ahoy.client.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.Entity;
import net.sabio.ahoy.entity.ShipEntity;
import net.sabio.ahoy.network.AnchorTogglePayload;
import net.sabio.ahoy.network.ShipControlPayload;
import net.sabio.ahoy.network.ShipSyncPayload;
import net.sabio.ahoy.network.WeatherUpdatePayload;

@Environment(EnvType.CLIENT)
public final class AhoyClientNetworking {
    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ShipSyncPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                if (context.client().world == null) return;
                Entity entity = context.client().world.getEntityById(payload.shipId());
                if (entity instanceof ShipEntity ship) {
                    ship.applySync(payload.x(), payload.y(), payload.z(), payload.yaw(), payload.velocityX(), payload.velocityY(), payload.velocityZ(), payload.anchored(), payload.sailsUp());
                }
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(WeatherUpdatePayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                // TODO: do stuff here :3
            });
        });
    }
    public static void sendShipControl(int shipId, boolean forward, boolean backward, boolean left, boolean right) {
        ClientPlayNetworking.send(new ShipControlPayload(shipId, forward, backward, left, right));
    }
    public static void sendAnchorToggle(int shipId) {
        ClientPlayNetworking.send(new AnchorTogglePayload(shipId));
    }
}
