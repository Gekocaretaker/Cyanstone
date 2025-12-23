package com.gekocaretaker.cyanstone.mixin.client.render;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.gekocaretaker.cyanstone.client.render.item.tint.RedstoneTintSource;
import com.mojang.serialization.MapCodec;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.item.ItemTintSource;
import net.minecraft.client.color.item.ItemTintSources;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(ItemTintSources.class)
public class ItemTintSourcesMixin {
    @Shadow
    @Final
    public static ExtraCodecs.LateBoundIdMapper<Identifier, MapCodec<? extends ItemTintSource>> ID_MAPPER;

    @Inject(method = "bootstrap", at = @At("TAIL"))
    private static void bootstrap(CallbackInfo ci) {
        ID_MAPPER.put(Cyanstone.id("redstone"), RedstoneTintSource.CODEC);
    }
}
