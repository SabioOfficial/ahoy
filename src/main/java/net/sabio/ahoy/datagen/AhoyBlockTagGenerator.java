package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.sabio.ahoy.Ahoy;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AhoyBlockTagGenerator extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> SHIP_HULL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ahoy.MOD_ID, "ship_hull_blocks"));
    public AhoyBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }
    @Override
    protected void configure(RegistryWrapper.@NotNull WrapperLookup lookup) {
        builder(SHIP_HULL_BLOCKS);
    }
}
