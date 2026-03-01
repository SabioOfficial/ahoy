package net.sabio.ahoy.network;

import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;

public final class AhoyPackets {
    public static final Identifier SHIP_CONTROL_C2S = Identifier.of(Ahoy.MOD_ID, "ship_control");
    public static final Identifier SHIP_ANCHOR_TOGGLE_C2S = Identifier.of(Ahoy.MOD_ID, "ship_anchor_toggle");
    public static final Identifier SHIP_SYNC_S2C = Identifier.of(Ahoy.MOD_ID, "ship_sync");
    public static final Identifier SHIP_POSITION_S2C = Identifier.of(Ahoy.MOD_ID, "ship_position");
    public static final Identifier CREW_STATE_S2C = Identifier.of(Ahoy.MOD_ID, "crew_state");
    public static final Identifier WEATHER_UPDATE_S2C = Identifier.of(Ahoy.MOD_ID, "weather_update");

    private AhoyPackets() {}
}
