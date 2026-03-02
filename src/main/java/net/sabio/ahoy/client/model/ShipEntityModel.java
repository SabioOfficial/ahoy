package net.sabio.ahoy.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.sabio.ahoy.client.render.entity.ShipRenderState;

public class ShipEntityModel extends EntityModel<ShipRenderState> {
    private final ModelPart all;
    private final ModelPart floor;
    private final ModelPart railing;

    public ShipEntityModel(ModelPart root) {
        super(root);
        this.all = root.getChild("all");
        this.floor = this.all.getChild("floor");
        this.railing = this.all.getChild("railing");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(),
                ModelTransform.of(0.0F, -12.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        all.addChild("floor", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(5.0F, -8.0F, -16.0F, 64.0F, 8.0F, 32.0F, new Dilation(0.0F))
                        .uv(0, 122).cuboid(75.0F, -8.0F, -6.0F, 2.0F, 8.0F, 12.0F, new Dilation(0.0F))
                        .uv(104, 90).cuboid(73.0F, -8.0F, -9.0F, 2.0F, 8.0F, 18.0F, new Dilation(0.0F))
                        .uv(0, 90).cuboid(71.0F, -8.0F, -12.0F, 2.0F, 8.0F, 24.0F, new Dilation(0.0F))
                        .uv(0, 52).cuboid(69.0F, -8.0F, -15.0F, 2.0F, 8.0F, 30.0F, new Dilation(0.0F))
                        .uv(28, 122).cuboid(-3.0F, -8.0F, -6.0F, 2.0F, 8.0F, 12.0F, new Dilation(0.0F))
                        .uv(52, 90).cuboid(1.0F, -8.0F, -12.0F, 2.0F, 8.0F, 24.0F, new Dilation(0.0F))
                        .uv(64, 52).cuboid(3.0F, -8.0F, -15.0F, 2.0F, 8.0F, 30.0F, new Dilation(0.0F))
                        .uv(104, 116).cuboid(-1.0F, -8.0F, -9.0F, 2.0F, 8.0F, 18.0F, new Dilation(0.0F)),
                ModelTransform.origin(-37.0F, 0.0F, 0.0F));

        all.addChild("railing", ModelPartBuilder.create()
                        .uv(94, 129).cuboid(-34.0F, -4.0F, -4.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(0, 40).cuboid(-98.0F, -4.0F, 25.0F, 64.0F, 4.0F, 2.0F, new Dilation(0.0F))
                        .uv(0, 46).cuboid(-98.0F, -4.0F, -5.0F, 64.0F, 4.0F, 2.0F, new Dilation(0.0F))
                        .uv(84, 122).cuboid(-100.0F, -4.0F, -4.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(94, 122).cuboid(-102.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(128, 68).cuboid(-104.0F, -4.0F, 2.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(84, 129).cuboid(-104.0F, -4.0F, 17.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(128, 82).cuboid(-102.0F, -4.0F, 20.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(128, 75).cuboid(-100.0F, -4.0F, 23.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(56, 122).cuboid(-106.0F, -4.0F, 5.0F, 2.0F, 4.0F, 12.0F, new Dilation(0.0F))
                        .uv(66, 138).cuboid(-33.0F, -4.0F, 23.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(56, 138).cuboid(-31.0F, -4.0F, 20.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(94, 136).cuboid(-30.0F, -4.0F, 17.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(128, 52).cuboid(-28.0F, -4.0F, 5.0F, 2.0F, 4.0F, 12.0F, new Dilation(0.0F))
                        .uv(84, 136).cuboid(-30.0F, -4.0F, 2.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F))
                        .uv(132, 40).cuboid(-32.0F, -4.0F, -1.0F, 2.0F, 4.0F, 3.0F, new Dilation(0.0F)),
                ModelTransform.origin(66.0F, -8.0F, -11.0F));

        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void setAngles(ShipRenderState state) {
        super.setAngles(state);
    }
}