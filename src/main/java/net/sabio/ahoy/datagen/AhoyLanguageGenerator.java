package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AhoyLanguageGenerator extends FabricLanguageProvider {
    public AhoyLanguageGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, "en_us", registryLookup);
    }
    @Override
    public void generateTranslations(RegistryWrapper.@NotNull WrapperLookup registryLookup, TranslationBuilder builder) {
        builder.add("item.ahoy.ship", "Starter Ship");
        builder.add("item.ahoy.cannon_ball", "Cannon Ball");
        builder.add("item.ahoy.ballista_bolt", "Ballista Bolt");
        builder.add("item.ahoy.anchor", "Anchor");
        builder.add("item.ahoy.ship_wheel", "Ship's Wheel");
        builder.add("entity.ahoy.ship", "Ship");
        builder.add("entity.ahoy.ship.boarded", "Sneak to dismount.");
        builder.add("entity.ahoy.ship.blocked", "Move to a bigger area to spawn the ship.");
        builder.add("entity.ahoy.ship.anchored", "Anchor dropped!");
        builder.add("entity.ahoy.ship.unanchored", "Anchor raised!");
        builder.add("itemGroup.ahoy.main", "Ahoy");
    }
}
