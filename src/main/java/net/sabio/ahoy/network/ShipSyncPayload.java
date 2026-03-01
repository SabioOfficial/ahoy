package net.sabio.ahoy.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record ShipSyncPayload (
        int shipId,
        double x, double y, double z,
        float yaw, float pitch,
        double velocityX, double velocityY, double velocityZ,
        boolean anchored,
        boolean sailsUp
) implements CustomPayload {
    public static final CustomPayload.Id<ShipSyncPayload> ID = new CustomPayload.Id<>(AhoyPackets.SHIP_SYNC_S2C);
    public static final PacketCodec<PacketByteBuf, ShipSyncPayload> CODEC = PacketCodec.of(ShipSyncPayload::write, ShipSyncPayload::read);

    private static ShipSyncPayload read(PacketByteBuf buf) {
        return new ShipSyncPayload(
                buf.readInt(),
                buf.readDouble(), buf.readDouble(), buf.readDouble(),
                buf.readFloat(), buf.readFloat(),
                buf.readDouble(), buf.readDouble(), buf.readDouble(),
                buf.readBoolean(),
                buf.readBoolean()
        );
    }

    private void write(PacketByteBuf buf) {
        buf.writeInt(shipId);
        buf.writeDouble(x); buf.writeDouble(y); buf.writeDouble(z);
        buf.writeFloat(yaw); buf.writeFloat(pitch);
        buf.writeDouble(velocityX); buf.writeDouble(velocityY); buf.writeDouble(velocityZ);
        buf.writeBoolean(anchored);
        buf.writeBoolean(sailsUp);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
