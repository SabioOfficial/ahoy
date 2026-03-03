package net.sabio.ahoy.client.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.sabio.ahoy.client.render.entity.ShipRenderState;

public class ShipEntityModel extends EntityModel<ShipRenderState> {
    private final ModelPart all;
    private final ModelPart floor;
    private final ModelPart railings;
    private final ModelPart seats;

    public ShipEntityModel(ModelPart root) {
        super(root);
        this.all = root.getChild("all");
        this.floor = this.all.getChild("floor");
        this.railings = this.all.getChild("railings");
        this.seats = this.all.getChild("seats");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData all = modelPartData.addChild("all", ModelPartBuilder.create(), ModelTransform.of(0.0F, -11.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData floor = all.addChild("floor", ModelPartBuilder.create().uv(0, 0).cuboid(32.0F, -2.0F, -16.0F, 10.0F, 2.0F, 32.0F, new Dilation(0.0F))
                .uv(0, 34).cuboid(42.0F, -2.0F, -15.0F, 4.0F, 2.0F, 30.0F, new Dilation(0.0F))
                .uv(0, 66).cuboid(28.0F, -2.0F, -15.0F, 4.0F, 2.0F, 30.0F, new Dilation(0.0F))
                .uv(68, 64).cuboid(24.0F, -2.0F, -14.0F, 4.0F, 2.0F, 28.0F, new Dilation(0.0F))
                .uv(0, 98).cuboid(20.0F, -2.0F, -13.0F, 4.0F, 2.0F, 26.0F, new Dilation(0.0F))
                .uv(132, 28).cuboid(16.0F, -2.0F, -11.0F, 4.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(132, 94).cuboid(12.0F, -2.0F, -9.0F, 4.0F, 2.0F, 18.0F, new Dilation(0.0F))
                .uv(148, 0).cuboid(8.0F, -2.0F, -7.0F, 4.0F, 2.0F, 14.0F, new Dilation(0.0F))
                .uv(148, 16).cuboid(4.0F, -2.0F, -5.0F, 4.0F, 2.0F, 10.0F, new Dilation(0.0F))
                .uv(68, 34).cuboid(46.0F, -2.0F, -14.0F, 4.0F, 2.0F, 28.0F, new Dilation(0.0F))
                .uv(84, 0).cuboid(50.0F, -2.0F, -13.0F, 6.0F, 2.0F, 26.0F, new Dilation(0.0F))
                .uv(60, 121).cuboid(56.0F, -2.0F, -12.0F, 6.0F, 2.0F, 24.0F, new Dilation(0.0F))
                .uv(0, 126).cuboid(62.0F, -2.0F, -11.0F, 4.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(132, 52).cuboid(66.0F, -2.0F, -10.0F, 4.0F, 2.0F, 20.0F, new Dilation(0.0F))
                .uv(132, 74).cuboid(70.0F, -2.0F, -9.0F, 4.0F, 2.0F, 18.0F, new Dilation(0.0F))
                .uv(88, 147).cuboid(76.0F, -2.0F, -7.0F, 2.0F, 2.0F, 14.0F, new Dilation(0.0F))
                .uv(52, 147).cuboid(74.0F, -2.0F, -8.0F, 2.0F, 2.0F, 16.0F, new Dilation(0.0F)), ModelTransform.origin(-37.0F, 0.0F, 0.0F));

        ModelPartData railings = all.addChild("railings", ModelPartBuilder.create().uv(160, 159).cuboid(-3.0F, -15.0F, -2.0F, 2.0F, 15.0F, 4.0F, new Dilation(0.0F))
                .uv(60, 98).cuboid(-5.0F, -18.0F, -1.0F, 2.0F, 18.0F, 2.0F, new Dilation(0.0F))
                .uv(144, 159).cuboid(-1.0F, -12.0F, -3.0F, 2.0F, 12.0F, 6.0F, new Dilation(0.0F))
                .uv(16, 165).cuboid(1.0F, -11.0F, -5.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(28, 165).cuboid(1.0F, -11.0F, 3.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(40, 165).cuboid(5.0F, -11.0F, 5.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(52, 165).cuboid(9.0F, -11.0F, 7.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(64, 165).cuboid(13.0F, -11.0F, 9.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(76, 165).cuboid(17.0F, -11.0F, 11.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(120, 170).cuboid(21.0F, -11.0F, 12.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(132, 170).cuboid(25.0F, -11.0F, 13.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 150).cuboid(29.0F, -11.0F, 14.0F, 10.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 172).cuboid(39.0F, -11.0F, 13.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(172, 157).cuboid(43.0F, -11.0F, 12.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 161).cuboid(47.0F, -11.0F, 11.0F, 6.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(88, 163).cuboid(53.0F, -11.0F, 10.0F, 6.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(172, 168).cuboid(59.0F, -11.0F, 9.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(88, 174).cuboid(63.0F, -11.0F, 8.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(100, 174).cuboid(67.0F, -11.0F, 7.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(52, 126).cuboid(71.0F, -11.0F, 6.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(44, 150).cuboid(73.0F, -11.0F, 5.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(52, 137).cuboid(73.0F, -10.0F, 4.0F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(24, 150).cuboid(73.0F, -9.0F, -4.0F, 2.0F, 7.0F, 8.0F, new Dilation(0.0F))
                .uv(152, 177).cuboid(73.0F, -10.0F, -5.0F, 2.0F, 8.0F, 1.0F, new Dilation(0.0F))
                .uv(112, 174).cuboid(73.0F, -11.0F, -7.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(144, 177).cuboid(71.0F, -11.0F, -8.0F, 2.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(12, 176).cuboid(67.0F, -11.0F, -9.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(176, 16).cuboid(63.0F, -11.0F, -10.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(24, 176).cuboid(59.0F, -11.0F, -11.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(36, 176).cuboid(43.0F, -11.0F, -14.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(48, 176).cuboid(39.0F, -11.0F, -15.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(60, 176).cuboid(25.0F, -11.0F, -15.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(72, 176).cuboid(21.0F, -11.0F, -14.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(176, 74).cuboid(17.0F, -11.0F, -13.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(176, 85).cuboid(13.0F, -11.0F, -11.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(176, 96).cuboid(9.0F, -11.0F, -9.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(176, 107).cuboid(5.0F, -11.0F, -7.0F, 4.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(120, 159).cuboid(29.0F, -11.0F, -16.0F, 10.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(104, 163).cuboid(47.0F, -11.0F, -13.0F, 6.0F, 9.0F, 2.0F, new Dilation(0.0F))
                .uv(164, 146).cuboid(53.0F, -11.0F, -12.0F, 6.0F, 9.0F, 2.0F, new Dilation(0.0F)), ModelTransform.origin(-34.0F, 0.0F, 0.0F));

        ModelPartData seats = all.addChild("seats", ModelPartBuilder.create().uv(68, 94).cuboid(-9.0F, -8.0F, -13.0F, 6.0F, 1.0F, 26.0F, new Dilation(0.0F))
                .uv(120, 121).cuboid(12.0F, -8.0F, -12.0F, 6.0F, 1.0F, 24.0F, new Dilation(0.0F))
                .uv(120, 146).cuboid(31.0F, -7.0F, -6.0F, 10.0F, 1.0F, 12.0F, new Dilation(0.0F))
                .uv(84, 28).cuboid(31.0F, -7.0F, -7.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(84, 32).cuboid(31.0F, -7.0F, -8.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(94, 32).cuboid(31.0F, -7.0F, 7.0F, 4.0F, 1.0F, 1.0F, new Dilation(0.0F))
                .uv(84, 30).cuboid(31.0F, -7.0F, 6.0F, 8.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(-2.0F, -1.0F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }

    @Override
    public void setAngles(ShipRenderState state) {
        super.setAngles(state);
    }
}