package net.sabio.ahoy.item;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sabio.ahoy.entity.ShipEntity;
import net.sabio.ahoy.registry.AhoyEntityTypes;

public class ShipItem extends Item {
    public ShipItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();

        if (!world.isClient() && player != null) {
            Vec3d spawnPos = new Vec3d(
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5
            );

            ShipEntity ship = AhoyEntityTypes.SHIP.create(
                    (ServerWorld) world,
                    null,
                    pos,
                    SpawnReason.SPAWN_ITEM_USE,
                    false,
                    false
            );
            if (ship != null) {
                ship.setPosition(spawnPos);
                ship.setYaw(player.getYaw());
                world.spawnEntity(ship);
                if (!player.isCreative()) {
                    context.getStack().decrement(1);
                }
            }
        }
        return ActionResult.SUCCESS;
    }
}
