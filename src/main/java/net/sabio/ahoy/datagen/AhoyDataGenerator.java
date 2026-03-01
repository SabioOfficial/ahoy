package net.sabio.ahoy.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class AhoyDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(AhoyRecipeGenerator::new);
        pack.addProvider(AhoyLootTableGenerator::new);
        pack.addProvider(AhoyItemTagGenerator::new);
        pack.addProvider(AhoyBlockTagGenerator::new);
        pack.addProvider(AhoyLanguageGenerator::new);
    }
}
