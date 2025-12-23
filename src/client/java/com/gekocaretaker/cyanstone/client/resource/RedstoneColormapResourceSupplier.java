package com.gekocaretaker.cyanstone.client.resource;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.LegacyStuffWrapper;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class RedstoneColormapResourceSupplier extends SimplePreparableReloadListener<int[]> {
    private static final Identifier REDSTONE_COLORMAP_LOC = Identifier.withDefaultNamespace("textures/colormap/redstone.png");

    public RedstoneColormapResourceSupplier() {
    }

    protected int[] prepare(ResourceManager resourceManager, ProfilerFiller profiler) {
        try {
            return LegacyStuffWrapper.getPixels(resourceManager, REDSTONE_COLORMAP_LOC);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load redstone color texture", e);
        }
    }

    @Override
    protected void apply(int[] prepared, ResourceManager manager, ProfilerFiller profiler) {
        RedstoneColors.setColorMap(prepared);
    }
}
