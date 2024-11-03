package com.gekocaretaker.cyanstone.mixin;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(RedstoneTorchBlock.class)
public class RedstoneTorchBlockMixin {
    @Shadow @Final public static BooleanProperty LIT;

    /**
     * @author gekocaretaker
     * @reason Cannot figure out how to just replace world.addParticle() while using 'state' from randomDisplayTick.
     */
    @Overwrite
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((Boolean) state.get(LIT)) {
            double d = (double) pos.getX() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            double e = (double) pos.getY() + 0.7 + (random.nextDouble() - 0.5) * 0.2;
            double f = (double) pos.getZ() + 0.5 + (random.nextDouble() - 0.5) * 0.2;
            world.addParticle(new DustParticleEffect(Cyanstone.VEC_COLOR, 1.0F), d, e, f, 0.0, 0.0, 0.0);
        }
    }
}
