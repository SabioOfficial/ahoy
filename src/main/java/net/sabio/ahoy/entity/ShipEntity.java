package net.sabio.ahoy.entity;

import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.sabio.ahoy.config.AhoyConfig;
import net.sabio.ahoy.network.ShipSyncPayload;
import net.sabio.ahoy.registry.AhoyItems;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShipEntity extends Entity {
    public static final float SHIP_HEIGHT = 2.0f;

    public double clientX, clientY, clientZ;
    public float clientYaw;
    public int interpolationSteps;

    private float inputForward;
    private float inputSideways;

    private boolean anchored = false;
    private boolean sailsUp = true;

    private double velocityX;
    private double velocityZ;
    private float shipYaw;

    private float shipHealth = 160f;
    private static final float MAX_SHIP_HEALTH = 160f;

    private int broadcastTimer = 0;
    private static final int BROADCAST_INTERVAL = 1;

    private SimpleInventory inventory;

    private ServerBossBar bossBar;
    private final Set<ServerPlayerEntity> mountedPlayers = new HashSet<>();

    private static final Vec3d[] SEAT_OFFSETS = {
            new Vec3d(0, 1.2, 0),
            new Vec3d(1.2, 1.0, 1),
            new Vec3d(-1.2, 1.0, 1),
            new Vec3d(0, 1.0, -1.5),
    };

    private void syncBossBarPlayers() {
        Set<ServerPlayerEntity> current = getPassengerList().stream()
                .filter(entity -> entity instanceof ServerPlayerEntity)
                .map(entity -> (ServerPlayerEntity) entity)
                .collect(Collectors.toSet());
        for (ServerPlayerEntity player : current) {
            if (!mountedPlayers.contains(player)) {
                bossBar.addPlayer(player);
                updateBossBar();
            }
        }
        for (ServerPlayerEntity player : mountedPlayers) {
            if (!current.contains(player)) {
                bossBar.removePlayer(player);
            }
        }
        mountedPlayers.clear();
        mountedPlayers.addAll(current);
    }

    public ShipEntity(EntityType<?> type, World world) {
        super(type, world);
        this.intersectionChecked = true;
        this.inventory = new SimpleInventory(AhoyConfig.get().shipInventorySlots);
        this.bossBar = new ServerBossBar(
                Text.literal("Ship Health"),
                BossBar.Color.GREEN,
                BossBar.Style.NOTCHED_10
        );
    }

    private void updateBossBar() {
        float percentage = shipHealth / MAX_SHIP_HEALTH;
        bossBar.setPercent(percentage);
    }

    @Override
    public boolean canHit() {
        return true;
    }

    @Override
    public boolean isCollidable(@Nullable Entity entity) {
        return true;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        // TODO: data watcher values for anchored/sails state add here
    }

    @Override
    protected Box calculateDefaultBoundingBox(Vec3d pos) {
        double extent = 2.0;
        return new Box(
                pos.x - extent, pos.y, pos.z - extent,
                pos.x + extent, pos.y + SHIP_HEIGHT, pos.z + extent
        );
    }

    @Override
    public boolean canAddPassenger(Entity passenger) {
        return this.getPassengerList().size() < AhoyConfig.get().shipMaxPassengers;
    }

    @Override
    protected Vec3d getPassengerAttachmentPos(Entity passenger, EntityDimensions dimensions, float tickDelta) {
        List<Entity> passengers = this.getPassengerList();
        int index = passengers.indexOf(passenger);
        if (index < 0) index = 0;
        int seatIdx = Math.min(index, SEAT_OFFSETS.length - 1);
        Vec3d offset = SEAT_OFFSETS[seatIdx];

        float yawRad = this.shipYaw * MathHelper.RADIANS_PER_DEGREE;
        double rotatedX = offset.x * MathHelper.cos(-yawRad) - offset.z * MathHelper.sin(-yawRad);
        double rotatedZ = offset.x * MathHelper.sin(-yawRad) - offset.z * MathHelper.cos(-yawRad);

        return new Vec3d(rotatedX, offset.y, rotatedZ);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        bossBar.removePlayer(player);
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        if (bossBar != null) bossBar.clearPlayers();
    }

    public Entity getPilot() {
        List<Entity> passengers = this.getPassengerList();
        return passengers.isEmpty() ? null : passengers.getFirst();
    }

    public boolean isBeingControlledBy(PlayerEntity player) {
        return getPilot() == player;
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        if (!this.getEntityWorld().isClient()) {
            if (this.hasPassenger(player)) {
                player.stopRiding();
            } else {
                player.startRiding(this);
                player.setYaw(this.shipYaw);
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    bossBar.addPlayer(serverPlayer);
                    updateBossBar();
                }
                player.sendMessage(Text.translatable("entity.ahoy.ship.boarded"), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    public void applySync(double x, double y, double z, float yaw, double velocityX, double velocityY, double velocityZ, boolean anchored, boolean sailsUp) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = yaw;
        this.velocityX = velocityX;
        this.velocityZ = velocityZ;
        this.anchored = anchored;
        this.sailsUp = sailsUp;
        if (this.interpolationSteps <= 1) {
            this.interpolationSteps = 1;
        }
    }

    public void applyControlInput(boolean forward, boolean backward, boolean left, boolean right) {
        if (anchored) return;
        this.inputForward = forward ? 1f : (backward ? -0.5f : 0f);
        this.inputSideways = left ? 1f : (right ? -1f : 0f);
    }

    public void toggleAnchor() {
        this.anchored = !this.anchored;
        if (this.anchored) {
            this.velocityX = 0;
            this.velocityZ = 0;
        }
        broadcastSyncPacket();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getEntityWorld().isClient()) {
            clientTick();
        } else {
            serverTick();
        }
    }

    private void serverTick() {
        syncBossBarPlayers();

        boolean inWater = this.isTouchingWater();
        double targetY = getY();

        if (inWater) {
            targetY = Math.floor(getY()) + 0.2;
            double currentY = getY();
            double yDiff = targetY - currentY;
            double newY = currentY + (yDiff * 0.3);
            setPosition(getX(), newY, getZ());
        } else {
            Vec3d velocity = getVelocity();
            double gravityVelocity = velocity.y - 0.08;
            if (gravityVelocity < -3.92) gravityVelocity = -3.92;
            setVelocity(velocity.x, gravityVelocity, velocity.z);
            move(MovementType.SELF, getVelocity());
        }

        if (anchored) {
            velocityX *= 0.5;
            velocityZ *= 0.5;
            broadcastSyncPacket();
            return;
        }

        float baseSpeed = AhoyConfig.get().shipBaseSpeed;
        float turnSpeed = AhoyConfig.get().shipTurnSpeed;

        if (inputSideways != 0) {
            shipYaw -= inputSideways * turnSpeed;
            while (shipYaw > 180f) shipYaw -= 360f;
            while (shipYaw < -180f) shipYaw += 360f;
        }
        setYaw(shipYaw);

        if (inputForward != 0) {
            float yawRad = shipYaw * MathHelper.RADIANS_PER_DEGREE;
            velocityX += -MathHelper.sin(yawRad) * inputForward * baseSpeed;
            velocityZ += MathHelper.cos(yawRad) * inputForward * baseSpeed;
        }
        if (inWater) {
            velocityX *= 0.92;
            velocityZ *= 0.92;
        } else {
            velocityX *= 0.3;
            velocityZ *= 0.3;
        }

        double maxSpeed = sailsUp ? baseSpeed * 6 : baseSpeed * 3;
        double currentSpeed = Math.sqrt(velocityX * velocityX + velocityZ * velocityZ);
        if (currentSpeed > maxSpeed) {
            double scale = maxSpeed / currentSpeed;
            velocityX *= scale;
            velocityZ *= scale;
        }

        if (inWater) {
            move(MovementType.SELF, new Vec3d(velocityX, 0, velocityZ));
        } else {
            move(MovementType.SELF, new Vec3d(velocityX, getVelocity().y, velocityZ));
        }

        broadcastTimer++;
        if (broadcastTimer >= BROADCAST_INTERVAL) {
            broadcastSyncPacket();
            broadcastTimer = 0;
        }

        inputForward = 0;
        inputSideways = 0;
    }

    private void clientTick() {
        if (interpolationSteps > 0) {
            double alpha = 1.0 / interpolationSteps;
            this.setPosition(
                    this.getX() + (clientX - this.getX()) * alpha,
                    this.getY() + (clientY - this.getY()) * alpha,
                    this.getZ() + (clientZ - this.getZ()) * alpha
            );
            float deltaYaw = clientYaw - this.shipYaw;
            while (deltaYaw >  180f) deltaYaw -= 360f;
            while (deltaYaw < -180f) deltaYaw += 360f;
            this.shipYaw += (float) (deltaYaw * alpha);
            while (this.shipYaw >  180f) this.shipYaw -= 360f;
            while (this.shipYaw < -180f) this.shipYaw += 360f;
            this.setYaw(this.shipYaw);
            interpolationSteps--;
        }
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        passenger.setYaw(this.shipYaw);
        passenger.setHeadYaw(this.shipYaw);
    }

    private void broadcastSyncPacket() {
        if (!(this.getEntityWorld() instanceof ServerWorld serverWorld)) return;
        ShipSyncPayload payload = new ShipSyncPayload(
                this.getId(),
                getX(), getY(), getZ(),
                shipYaw,getPitch(),
                velocityX, getVelocity().y, velocityZ,
                anchored, sailsUp
        );
        for (ServerPlayerEntity player : PlayerLookup.tracking(this)) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    @Override
    public void readCustomData(ReadView view) {
        this.shipYaw = view.getFloat("ShipYaw", 0f);
        this.anchored = view.getBoolean("Anchored", false);
        this.sailsUp = view.getBoolean("SailsUp", true);
        this.velocityX = view.getDouble("VelocityX", 0.0);
        this.velocityZ = view.getDouble("VelocityZ", 0.0);
        this.shipHealth = view.getFloat("ShipHealth", MAX_SHIP_HEALTH);
        this.inventory = new SimpleInventory(AhoyConfig.get().shipInventorySlots);
        view.getOptionalListReadView("Inventory").ifPresent(invList -> {
            int[] slot = {0};
            for (ReadView slotView : invList) {
                int slotIndex = slotView.getInt("Slot", 0);
                if (slotIndex < inventory.size()) {
                    slotView.read("Stack", ItemStack.CODEC).ifPresent(stack ->
                            inventory.setStack(slotIndex, stack));
                }
            }
        });
    }

    @Override
    public void writeCustomData(WriteView view) {
        view.putFloat("ShipYaw", shipYaw);
        view.putBoolean("Anchored", anchored);
        view.putBoolean("SailsUp", sailsUp);
        view.putDouble("VelocityX", velocityX);
        view.putDouble("VelocityZ", velocityZ);
        view.putFloat("ShipHealth", shipHealth);

        WriteView.ListView invList = view.getList("Inventory");
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                WriteView slotView = invList.add();
                slotView.putInt("Slot", i);
                slotView.put("Stack", ItemStack.CODEC, stack);
            }
        }
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        if (this.isRemoved()) return false;
        if (source.getAttacker() instanceof PlayerEntity player && player.isCreative()) {
            this.discard();
            return true;
        }

        shipHealth -= amount;
        if (shipHealth <= 0) {
            this.discard();
            this.dropStack(world, new ItemStack(AhoyItems.SHIP_ITEM));
        }
        return true;
    }

    public boolean isAnchored() {return anchored;}
    public boolean areSailsUp() {return sailsUp;}
    public float getShipYaw() {return shipYaw;}
    public SimpleInventory getInventory() {return inventory;}
}
