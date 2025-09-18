package me.anticode.astro_reforged.item;

import me.anticode.astro_reforged.entity.HighGravityBombEntity;
import me.anticode.astro_reforged.entity.LowGravityBombEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class LowGravityBombItem extends Item {
    public LowGravityBombItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        world.playSound(
                user.getX(),
                user.getY(),
                user.getZ(),
                SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK,
                SoundCategory.NEUTRAL,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F),
                true
        );
        if (!world.isClient() && !user.getItemCooldownManager().isCoolingDown(this)) {
            LowGravityBombEntity gravityBombEntity = new LowGravityBombEntity(user, world);
            gravityBombEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 3.0F, 0.0F);
            world.spawnEntity(gravityBombEntity);
            user.getItemCooldownManager().set(this, 250);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            stack.decrement(1);
        }

        return TypedActionResult.success(stack, world.isClient());
    }
}
