package net.sabio.ahoy.registry;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.entity.ShipEntity;

public class AhoyEntityTypes {
    public static final RegistryKey<EntityType<?>> SHIP_KEY = RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Ahoy.MOD_ID, "ship"));
    public static final EntityType<ShipEntity> SHIP = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(Ahoy.MOD_ID, "ship"),
            FabricEntityTypeBuilder.<ShipEntity>create(SpawnGroup.MISC, ShipEntity::new)
                    .dimensions(EntityDimensions.fixed(5.0f, 2.5f))
                    .trackRangeBlocks(128)
                    .trackedUpdateRate(3)
                    .build(SHIP_KEY)
    );
    public static void register() {
        Ahoy.LOGGER.info("[Ahoy] Registering Ahoy Entity Types...");
    }
}
