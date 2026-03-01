package net.sabio.ahoy.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.sabio.ahoy.weather.OceanWeather;

public record WeatherUpdatePayload (
        OceanWeather.Type weatherType,
        float intensity
) implements CustomPayload {
    public static final CustomPayload.Id<WeatherUpdatePayload> ID = new CustomPayload.Id<>(AhoyPackets.WEATHER_UPDATE_S2C);
    public static final PacketCodec<PacketByteBuf, WeatherUpdatePayload> CODEC = PacketCodec.of(WeatherUpdatePayload::write, WeatherUpdatePayload::read);
    private static WeatherUpdatePayload read(PacketByteBuf buf) {
        return new WeatherUpdatePayload(
                buf.readEnumConstant(OceanWeather.Type.class),
                buf.readFloat()
        );
    }
    private void write(PacketByteBuf buf) {
        buf.writeEnumConstant(weatherType);
        buf.writeFloat(intensity);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}