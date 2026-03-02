package net.sabio.ahoy.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.sabio.ahoy.Ahoy;
import net.sabio.ahoy.client.AhoyClient;
import net.sabio.ahoy.client.model.ShipEntityModel;
import net.sabio.ahoy.entity.ShipEntity;

@Environment(EnvType.CLIENT)
public class ShipEntityRenderer extends EntityRenderer<ShipEntity, ShipRenderState> {
    private static final Identifier TEXTURE =
            Identifier.of(Ahoy.MOD_ID, "textures/entity/ship.png");

    private final ShipEntityModel model;

    public ShipEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.shadowRadius = 2.5f;
        this.model = new ShipEntityModel(context.getPart(AhoyClient.SHIP_LAYER));
    }

    @Override
    public ShipRenderState createRenderState() {
        return new ShipRenderState();
    }

    @Override
    public void updateRenderState(ShipEntity entity, ShipRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.shipYaw = entity.getShipYaw();
    }

    @Override
    public void render(ShipRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        matrices.push();

        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-state.shipYaw + 180f));
        matrices.scale(-1.0f, -1.0f, 1.0f);

        RenderLayer renderLayer = RenderLayers.entityCutoutNoCull(TEXTURE);

        queue.submitModel(
                model,
                state,
                matrices,
                renderLayer,
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                OverlayTexture.DEFAULT_UV,
                0,
                null
        );

        matrices.pop();
        super.render(state, matrices, queue, cameraState);
    }
}