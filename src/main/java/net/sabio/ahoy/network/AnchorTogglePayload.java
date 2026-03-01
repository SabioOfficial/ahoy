package net.sabio.ahoy.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record AnchorTogglePayload(int shipId) implements CustomPayload {
    public static final CustomPayload.Id<AnchorTogglePayload> ID = new CustomPayload.Id<>(AhoyPackets.SHIP_ANCHOR_TOGGLE_C2S);
    public static final PacketCodec<PacketByteBuf, AnchorTogglePayload> CODEC = PacketCodec.of(AnchorTogglePayload::write, AnchorTogglePayload::read);
    private static AnchorTogglePayload read(PacketByteBuf buf) {
        return new AnchorTogglePayload(buf.readInt());
    }
    private void write(PacketByteBuf buf) {
        buf.writeInt(shipId);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
