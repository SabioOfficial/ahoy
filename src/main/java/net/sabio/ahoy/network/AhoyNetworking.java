package net.sabio.ahoy.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.entity.ShipEntity;

public final class AhoyNetworking {
    public static void registerServerPackets() {
        PayloadTypeRegistry.playC2S().register(ShipControlPayload.ID, ShipControlPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(AnchorTogglePayload.ID, AnchorTogglePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ShipSyncPayload.ID, ShipSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(WeatherUpdatePayload.ID, WeatherUpdatePayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ShipControlPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> {
                Entity entity = player.getEntityWorld().getEntityById(payload.shipId());
                if (entity instanceof ShipEntity ship && ship.isBeingControlledBy(player)) {
                    ship.applyControlInput(payload.forward(), payload.backward(), payload.left(), payload.right());
                }
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(AnchorTogglePayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> {
                Entity entity = player.getEntityWorld().getEntityById(payload.shipId());
                if (entity instanceof ShipEntity ship && ship.isBeingControlledBy(player)) {
                    ship.toggleAnchor();
                }
            });
        });

        Ahoy.LOGGER.info("[Ahoy] Registered Ahoy Server Packets...");
    }
}
