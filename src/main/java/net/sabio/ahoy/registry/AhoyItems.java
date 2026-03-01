package net.sabio.ahoy.registry;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.item.ShipItem;

public class AhoyItems {
    public static final Item SHIP_ITEM = Registry.register(
            Registries.ITEM,
            Identifier.of(Ahoy.MOD_ID, "ship"),
            new ShipItem(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "ship")))
                    .maxCount(1))
    );

    public static final Item CANNON_BALL = Registry.register(
            Registries.ITEM,
            Identifier.of(Ahoy.MOD_ID, "cannon_ball"),
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "cannon_ball")))
                    .maxCount(16))
    );

    public static final Item BALLISTA_BOLT = Registry.register(
            Registries.ITEM,
            Identifier.of(Ahoy.MOD_ID, "ballista_bolt"),
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "ballista_bolt")))
                    .maxCount(16))
    );

    public static final Item ANCHOR = Registry.register(
            Registries.ITEM,
            Identifier.of(Ahoy.MOD_ID, "anchor"),
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "anchor")))
                    .maxCount(1))
    );

    public static final Item SHIP_WHEEL = Registry.register(
            Registries.ITEM,
            Identifier.of(Ahoy.MOD_ID, "ship_wheel"),
            new Item(new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "ship_wheel")))
                    .maxCount(1))
    );

    public static void register() {
        Ahoy.LOGGER.info("Registering Ahoy Items");
    }
}