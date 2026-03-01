package net.sabio.ahoy.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record ShipControlPayload (
    int shipId,
    boolean forward,
    boolean backward,
    boolean left,
    boolean right
) implements CustomPayload {
    public static final CustomPayload.Id<ShipControlPayload> ID = new CustomPayload.Id<>(AhoyPackets.SHIP_CONTROL_C2S);
    public static final PacketCodec<PacketByteBuf, ShipControlPayload> CODEC = PacketCodec.of(ShipControlPayload::write, ShipControlPayload::read);

    private static ShipControlPayload read(PacketByteBuf buf) {
        return new ShipControlPayload(
                buf.readInt(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean(),
                buf.readBoolean()
        );
    }
    private void write(PacketByteBuf buf) {
        buf.writeInt(shipId);
        buf.writeBoolean(forward);
        buf.writeBoolean(backward);
        buf.writeBoolean(left);
        buf.writeBoolean(right);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
