package com.gekocaretaker.cyanstone.client.render.item.tint;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.tint.TintSource;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record RedstoneTintSource(int power) implements TintSource {
    public static final MapCodec<RedstoneTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(Codecs.rangedInt(0, 15).fieldOf("power").forGetter(RedstoneTintSource::power))
                .apply(instance, RedstoneTintSource::new);
    });

    public RedstoneTintSource() {
        this(0);
    }

    public RedstoneTintSource(int power) {
        this.power = power;
    }

    public int getTint(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user) {
        return RedstoneColors.getColor(this.power);
    }

    public MapCodec<RedstoneTintSource> getCodec() {
        return CODEC;
    }

    public int power() {
        return this.power;
    }
}
