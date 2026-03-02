package net.sabio.ahoy.client.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModel;
import net.sabio.ahoy.client.render.entity.ShipRenderState;

public class ShipEntityModel extends EntityModel<ShipRenderState> {
    private final ModelPart bb_main;

    public ShipEntityModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("bb_main",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-24.0F, -32.0F, -40.0F, 48.0F, 32.0F, 80.0F, new Dilation(0.0F)),
                ModelTransform.origin(0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 128);
    }

    @Override
    public void setAngles(ShipRenderState state) {
        super.setAngles(state);
    }
}