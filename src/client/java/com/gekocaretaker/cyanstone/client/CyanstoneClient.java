package com.gekocaretaker.cyanstone.client;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.gekocaretaker.cyanstone.client.support.Chipped;
import com.gekocaretaker.cyanstone.client.util.Colorizer;
import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Items;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class CyanstoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register(minecraftClient -> {
            // Make overlays transparent
            BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), Blocks.COPPER_BULB, Blocks.WAXED_COPPER_BULB,
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

            // Items
            Colorizer.item(15, Items.REDSTONE, Items.REDSTONE_BLOCK);
            Colorizer.itemWithOverlay(13, Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE);
            Colorizer.itemWithOverlay(15, Items.REDSTONE_TORCH);
            Colorizer.itemWithOverlay(1, Items.COMPARATOR, Items.REPEATER, Items.COPPER_BULB, Items.WAXED_COPPER_BULB,
                    Items.EXPOSED_COPPER_BULB, Items.WAXED_EXPOSED_COPPER_BULB, Items.OXIDIZED_COPPER_BULB, Items.WAXED_OXIDIZED_COPPER_BULB,
                    Items.WEATHERED_COPPER_BULB, Items.WAXED_WEATHERED_COPPER_BULB, Items.ACTIVATOR_RAIL, Items.DETECTOR_RAIL, Items.POWERED_RAIL,
                    Items.OBSERVER, Items.CRAFTER);

            // Mod Support
            if (FabricLoader.getInstance().isModLoaded("chipped")) {
                Chipped.register();
            }
        });

        ClientFinishedLoadingCallback.EVENT.register(() -> {
            for (int i = 0; i <= 15; i++) {
                RedstoneWireBlock.COLORS[i] = Vec3d.unpackRgb(RedstoneColors.getColor(i));
            }
            Cyanstone.VEC_COLOR = Vec3d.unpackRgb(RedstoneColors.getColor(15)).toVector3f();

            return ActionResult.PASS;
        });
    }
}
