package net.sabio.ahoy.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.entity.ShipEntity;

@Environment(EnvType.CLIENT)
public class ShipEntityRenderer extends EntityRenderer<ShipEntity, EntityRenderState> {
    public ShipEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 2.5f;
    }

    @Override
    public EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void render(EntityRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));
        matrices.pop();
        super.render(renderState, matrices, queue, cameraState);
    }
}
