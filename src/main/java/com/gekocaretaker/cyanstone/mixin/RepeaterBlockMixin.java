package com.gekocaretaker.cyanstone.mixin;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RepeaterBlock.class)
public class RepeaterBlockMixin {
    @WrapOperation(
            method = "randomDisplayTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V")
    )
    private void replaceAddParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original) {
        instance.addParticle(new DustParticleEffect(Cyanstone.VEC_COLOR, 1.0F), x, y, z, velocityX, velocityY, velocityZ);
    }
}
