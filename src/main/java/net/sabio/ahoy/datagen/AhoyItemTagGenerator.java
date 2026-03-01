package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.registry.AhoyItems;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AhoyItemTagGenerator extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> SHIP_MATERIALS = TagKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "ship_materials"));
    public static final TagKey<Item> NAVAL_AMMO = TagKey.of(RegistryKeys.ITEM, Identifier.of(Ahoy.MOD_ID, "naval_ammo"));
    public AhoyItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.@NotNull WrapperLookup lookup) {
        valueLookupBuilder(SHIP_MATERIALS)
                .add(Items.OAK_PLANKS)
                .add(Items.PALE_OAK_PLANKS)
                .add(Items.ACACIA_PLANKS)
                .add(Items.BAMBOO_PLANKS)
                .add(Items.BIRCH_PLANKS)
                .add(Items.CHERRY_PLANKS)
                .add(Items.CRIMSON_PLANKS)
                .add(Items.DARK_OAK_PLANKS)
                .add(Items.JUNGLE_PLANKS)
                .add(Items.MANGROVE_PLANKS)
                .add(Items.SPRUCE_PLANKS)
                .add(Items.WARPED_PLANKS)
                .add(Items.IRON_INGOT);

        valueLookupBuilder(NAVAL_AMMO)
                .add(AhoyItems.CANNON_BALL)
                .add(AhoyItems.BALLISTA_BOLT);
    }
}
