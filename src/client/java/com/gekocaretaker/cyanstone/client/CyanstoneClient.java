package com.gekocaretaker.cyanstone.client;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.gekocaretaker.cyanstone.client.resource.RedstoneColormapResourceSupplier;
import com.gekocaretaker.cyanstone.client.support.Chipped;
import com.gekocaretaker.cyanstone.client.util.Colorizer;
import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.resource.ResourceType;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;

@Environment(EnvType.CLIENT)
public class CyanstoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceLoader.get(ResourceType.CLIENT_RESOURCES).registerReloader(Cyanstone.id("redstone_colormap"), new RedstoneColormapResourceSupplier());

        ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> {
            // Make overlays transparent
            BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, Blocks.COPPER_BULB, Blocks.WAXED_COPPER_BULB,
                    Blocks.EXPOSED_COPPER_BULB, Blocks.WAXED_EXPOSED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB, Blocks.WAXED_OXIDIZED_COPPER_BULB,
                    Blocks.WEATHERED_COPPER_BULB, Blocks.WAXED_WEATHERED_COPPER_BULB, Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE,
                    Blocks.OBSERVER, Blocks.CRAFTER);

            // Blocks
            Colorizer.block(Blocks.REDSTONE_WIRE);
            Colorizer.block(15, Blocks.REDSTONE_BLOCK);
            Colorizer.block(13, Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE);
            Colorizer.blockBoolOverlay(Properties.LIT, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH);
            Colorizer.blockBoolOverlay(Properties.POWERED, Blocks.COPPER_BULB, Blocks.WAXED_COPPER_BULB,
                    Blocks.EXPOSED_COPPER_BULB, Blocks.WAXED_EXPOSED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB, Blocks.WAXED_OXIDIZED_COPPER_BULB,
                    Blocks.WEATHERED_COPPER_BULB, Blocks.WAXED_WEATHERED_COPPER_BULB, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.POWERED_RAIL,
                    Blocks.OBSERVER);
            Colorizer.block((state, world, pos, tintIndex) -> {
                if (tintIndex == 1) {
                    return RedstoneColors.getColor(2);
                } else if (tintIndex == 2) {
                    return RedstoneColors.getColor(15);
                } else {
                    return -1;
                }
            }, Blocks.COMPARATOR, Blocks.REPEATER);
            Colorizer.block((state, world, pos, tintIndex) -> {
                if (tintIndex == 1) {
                    return (state.get(Properties.CRAFTING) || state.get(Properties.TRIGGERED)) ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2);
                } else if (tintIndex == 2) {
                    return state.get(Properties.CRAFTING) ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2);
                } else {
                    return -1;
                }
            }, Blocks.CRAFTER);

            // Mod Support
            if (FabricLoader.getInstance().isModLoaded("chipped")) {
                Chipped.register();
            }
        });

        ClientFinishedLoadingCallback.EVENT.register(() -> {
            for (int i = 0; i <= 15; i++) {
                RedstoneWireBlock.COLORS[i] = RedstoneColors.getColor(i);
            }

            return ActionResult.PASS;
        });
    }
}
