package com.gekocaretaker.cyanstone.mixin;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.RedstoneOreBlock;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RedstoneOreBlock.class)
public class RedstoneOreBlockMixin {
    @WrapOperation(
            method = "spawnParticles(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V")
    )
    private static void cyanstone$spawnParticles(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original) {
        instance.addParticleClient(new DustParticleEffect(RedstoneColors.getColor(15), 1.0F), x, y, z, velocityX, velocityY, velocityZ);
    }
}
