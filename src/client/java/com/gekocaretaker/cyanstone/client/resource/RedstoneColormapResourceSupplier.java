package com.gekocaretaker.cyanstone.client.resource;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.RawTextureDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SinglePreparationResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class RedstoneColormapResourceSupplier extends SinglePreparationResourceReloader<int[]> {
    private static final Identifier REDSTONE_COLORMAP_LOC = Identifier.ofVanilla("textures/colormap/redstone.png");

    public RedstoneColormapResourceSupplier() {
    }

    protected int[] prepare(ResourceManager resourceManager, Profiler profiler) {
        try {
            return RawTextureDataLoader.loadRawTextureData(resourceManager, REDSTONE_COLORMAP_LOC);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load redstone color texture", e);
        }
    }

    @Override
    protected void apply(int[] prepared, ResourceManager manager, Profiler profiler) {
        RedstoneColors.setColorMap(prepared);
    }
}
