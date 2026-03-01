package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.registry.AhoyItems;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class AhoyLootTableGenerator extends SimpleFabricLootTableProvider {
    public static final RegistryKey<LootTable> SUNKEN_RUINS_CHEST =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Ahoy.MOD_ID, "chests/sunken_ruins"));
    public static final RegistryKey<LootTable> SUNKEN_TEMPLE_CHEST =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Ahoy.MOD_ID, "chests/sunken_temple"));
    public static final RegistryKey<LootTable> TREASURE_SHIPWRECK_CHEST =
            RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Ahoy.MOD_ID, "chests/treasure_shipwreck"));
    public AhoyLootTableGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        exporter.accept(SUNKEN_RUINS_CHEST, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(3, 6))
                        .with(ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4))))
                        .with(ItemEntry.builder(Items.GOLD_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 2))))
                        .with(ItemEntry.builder(Items.PRISMARINE_CRYSTALS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 6))))
                        .with(ItemEntry.builder(AhoyItems.CANNON_BALL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 4))))
                ));
        exporter.accept(SUNKEN_TEMPLE_CHEST, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(4, 8))
                        .with(ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3))))
                        .with(ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5))))
                        .with(ItemEntry.builder(Items.TRIDENT))
                        .with(ItemEntry.builder(AhoyItems.BALLISTA_BOLT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 8))))
                ));
        exporter.accept(TREASURE_SHIPWRECK_CHEST, LootTable.builder()
                .pool(LootPool.builder()
                        .rolls(UniformLootNumberProvider.create(5, 10))
                        .with(ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2, 5))))
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3))))
                        .with(ItemEntry.builder(Items.TRIDENT))
                        .with(ItemEntry.builder(Items.HEART_OF_THE_SEA))
                        .with(ItemEntry.builder(AhoyItems.SHIP_ITEM))
                        .with(ItemEntry.builder(AhoyItems.ANCHOR))
                ));
    }
}
