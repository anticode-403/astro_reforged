package me.anticode.astro_reforged.entity;

import gravity_changer.api.GravityChangerAPI;
import me.anticode.astro_reforged.init.AstroEntities;
import me.anticode.astro_reforged.init.AstroItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class GravityBombEntity extends ThrownItemEntity {
    private boolean hasLanded = false;
    private final int bomb_duration = 200;
    private int active_duration = 0;
    protected GravityBombType gravityBombType = GravityBombType.HIGH;
    public enum GravityBombType {
        HIGH (0),
        LOW (1),
        ANTI (2);

        private final int index;

        GravityBombType(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public static GravityBombType valueFromIndex(int index) {
            return values()[index];
        }
    }

    public GravityBombEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        GravityChangerAPI.setBaseGravityStrength(this, 0.5);
    }

    public GravityBombEntity(GravityBombType gravityBombType, World world, LivingEntity owner) {
        super(switch (gravityBombType) {
            case HIGH -> AstroEntities.HIGH_GRAVITY_BOMB;
            case LOW -> AstroEntities.LOW_GRAVITY_BOMB;
            case ANTI -> AstroEntities.LOW_GRAVITY_BOMB;
        }, owner, world); // A little hacky but whatever
        this.gravityBombType = gravityBombType;
        GravityChangerAPI.setBaseGravityStrength(this, 0.5);
    }

    @Override
    protected Item getDefaultItem() {
        return AstroItems.HIGH_GRAVITY_BOMB;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleEffect() {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.AMETHYST_CLUSTER.getDefaultState());
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        ParticleEffect particleEffect = this.getParticleEffect();
        if (status == 3) {
            for(int i = 0; i < 32; ++i) {
                this.getWorld().addParticle(
                        particleEffect,
                        this.getX() + (this.random.nextDouble() - 0.5D) * 4D,
                        this.getY(),
                        this.getZ() + (this.random.nextDouble() - 0.5D) * 4D,
                        this.random.nextGaussian(),
                        0.0D,
                        this.random.nextGaussian()
                );
            }
        }
        else if (status == 5) {
            for(int i = 0; i < 32; ++i) {
                this.getWorld().addParticle(
                        particleEffect,
                        this.getX() + (this.random.nextDouble() - 0.5D) * 20D,
                        this.getY() + (this.random.nextDouble() - 0.5D) * 20D,
                        this.getZ() + (this.random.nextDouble() - 0.5D) * 20D,
                        0.0D,
                        0.0D,
                        0.0D
                );
            }
        }
        else if (status == 10) {
            for(int i = 0; i < 32; ++i) {
                this.getWorld().addParticle(
                        particleEffect,
                        this.getX() + (this.random.nextDouble() - 0.5D) * 2D,
                        this.getY(),
                        this.getZ() + (this.random.nextDouble() - 0.5D) * 2D,
                        this.random.nextGaussian(),
                        this.random.nextDouble(),
                        this.random.nextGaussian()
                );
            }
        }
    }

    @Override
    public void tick() {
        if (hasLanded()) {
            active_duration++;
            getWorld().sendEntityStatus(this, (byte)5);
            List<Entity> nearbyEntities = getWorld().getOtherEntities(this, Box.of(this.getPos(), 20D, 20D, 20D));
            for (Entity entity : nearbyEntities) {
                if (entity instanceof LivingEntity) {
                    switch (gravityBombType) {
                        case HIGH -> {
                            GravityChangerAPI.setBaseGravityStrength(entity, 12);
                        }
                        case LOW ->  {
                            GravityChangerAPI.setBaseGravityStrength(entity, 0.1);
                        }
                        case ANTI -> {
                            GravityChangerAPI.setBaseGravityStrength(entity, -0.1);
                        }
                    }
                }
            }
            if (active_duration >= bomb_duration) {
                getWorld().sendEntityStatus(this, (byte)10);
                this.discard();
            }
        }
        super.tick();
    }

    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        World world = this.getWorld();
        if (!world.isClient()) {
            world.sendEntityStatus(this, (byte)3);
            this.land();
        }

        super.onBlockHit(blockHitResult);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        setVelocity(getVelocity().multiply(-0.4));
    }

    public boolean hasLanded() {
        return hasLanded;
    }

    public void land() {
        this.setVelocity(Vec3d.ZERO);
        GravityChangerAPI.setBaseGravityStrength(this, 0);
        this.hasLanded = true;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.hasLanded = nbt.getBoolean("hasLanded");
        this.active_duration = nbt.getInt("active_duration");
        this.gravityBombType = GravityBombType.valueFromIndex(nbt.getInt("bomb_type"));
        super.readNbt(nbt);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putBoolean("hasLanded", hasLanded);
        nbt.putInt("active_duration", active_duration);
        nbt.putInt("bomb_type", gravityBombType.getIndex());
        return super.writeNbt(nbt);
    }
}
