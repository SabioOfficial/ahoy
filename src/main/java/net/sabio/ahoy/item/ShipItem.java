package net.sabio.ahoy.item;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.sabio.ahoy.client.model.ShipEntityModel;
import net.sabio.ahoy.entity.ShipEntity;
import net.sabio.ahoy.registry.AhoyEntityTypes;

public class ShipItem extends Item {
    public ShipItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hit.getType() == HitResult.Type.MISS) {
            return ActionResult.PASS;
        }
        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = hit;
            Vec3d spawnPos = new Vec3d(
                    blockHit.getPos().x,
                    blockHit.getPos().y,
                    blockHit.getPos().z
            );
            EntityDimensions dimensions = AhoyEntityTypes.SHIP.getDimensions();
            Box spawnBox = new Box(
                    spawnPos.x - 1.0, spawnPos.y, spawnPos.z - 1.0,
                    spawnPos.x + 1.0, spawnPos.y + dimensions.height(), spawnPos.z + 1.0
            );
            if (!world.isSpaceEmpty(spawnBox)) {
                player.sendMessage(Text.translatable("entity.ahoy.ship.blocked"), true);
                return ActionResult.FAIL;
            }
            if (!world.isClient() && world instanceof ServerWorld serverWorld) {
                ShipEntity ship = AhoyEntityTypes.SHIP.create(
                        serverWorld,
                        null,
                        BlockPos.ofFloored(spawnPos),
                        SpawnReason.SPAWN_ITEM_USE,
                        false,
                        false
                );
                if (ship != null) {
                    ship.setPosition(spawnPos);
                    ship.setYaw(player.getYaw());
                    world.spawnEntity(ship);
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                }
            }
        }

        return ActionResult.SUCCESS;
    }
}
