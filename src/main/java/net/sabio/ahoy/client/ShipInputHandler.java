package net.sabio.ahoy.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.sabio.ahoy.client.network.AhoyClientNetworking;
import net.sabio.ahoy.entity.ShipEntity;

@Environment(EnvType.CLIENT)
public final class ShipInputHandler {
    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(ShipInputHandler::onTick);
    }

    private static void onTick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        if (!(client.player.getVehicle() instanceof ShipEntity ship)) return;
        if (ship.getPilot() != client.player) return;

        GameOptions keys = client.options;
        AhoyClientNetworking.sendShipControl(
                ship.getId(),
                keys.forwardKey.isPressed(),
                keys.backKey.isPressed(),
                keys.leftKey.isPressed(),
                keys.rightKey.isPressed()
        );
    }
}
