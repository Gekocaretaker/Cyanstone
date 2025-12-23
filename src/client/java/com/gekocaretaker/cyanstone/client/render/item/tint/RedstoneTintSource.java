package com.gekocaretaker.cyanstone.client.render.item.tint;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public record RedstoneTintSource(int power) implements ItemTintSource {
    public static final MapCodec<RedstoneTintSource> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return instance.group(ExtraCodecs.intRange(0, 15).fieldOf("power").forGetter(RedstoneTintSource::power))
                .apply(instance, RedstoneTintSource::new);
    });

    public RedstoneTintSource() {
        this(0);
    }

    public RedstoneTintSource(int power) {
        this.power = power;
    }

    @Override
    public int calculate(ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity user) {
        return RedstoneColors.getColor(this.power);
    }

    public MapCodec<RedstoneTintSource> type() {
        return CODEC;
    }

    public int power() {
        return this.power;
    }
}
