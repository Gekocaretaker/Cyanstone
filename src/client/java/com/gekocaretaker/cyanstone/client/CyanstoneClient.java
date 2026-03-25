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
import net.fabricmc.fabric.api.resource.v1.ResourceLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.List;

@Environment(EnvType.CLIENT)
public class CyanstoneClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES).registerReloadListener(Cyanstone.id("redstone_colormap"), new RedstoneColormapResourceSupplier());

        ClientLifecycleEvents.CLIENT_STARTED.register(_ -> {
            // Blocks
            Colorizer.block(Blocks.REDSTONE_WIRE);
            Colorizer.block(15, Blocks.REDSTONE_BLOCK);
            Colorizer.block(13, Blocks.REDSTONE_ORE, Blocks.DEEPSLATE_REDSTONE_ORE);
            Colorizer.block(BlockStateProperties.LIT, Blocks.REDSTONE_TORCH, Blocks.REDSTONE_WALL_TORCH);
            Colorizer.block(BlockStateProperties.POWERED, Blocks.COPPER_BULB, Blocks.WAXED_COPPER_BULB,
                    Blocks.EXPOSED_COPPER_BULB, Blocks.WAXED_EXPOSED_COPPER_BULB, Blocks.OXIDIZED_COPPER_BULB, Blocks.WAXED_OXIDIZED_COPPER_BULB,
                    Blocks.WEATHERED_COPPER_BULB, Blocks.WAXED_WEATHERED_COPPER_BULB, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.POWERED_RAIL,
                    Blocks.OBSERVER, Blocks.REPEATER);
            Colorizer.block(List.of(
                    _ -> RedstoneColors.getColor(2),
                    _ -> RedstoneColors.getColor(15)
            ), Blocks.COMPARATOR);
            Colorizer.block(List.of(
                    state -> (state.getValue(BlockStateProperties.CRAFTING) || state.getValue(BlockStateProperties.TRIGGERED))
                            ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2),
                    state -> state.getValue(BlockStateProperties.CRAFTING)
                            ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2)
            ), Blocks.CRAFTER);

            // Mod Support
            if (FabricLoader.getInstance().isModLoaded("chipped")) {
                Chipped.register();
            }
        });

        ClientFinishedLoadingCallback.EVENT.register(() -> {
            for (int i = 0; i <= 15; i++) {
                RedStoneWireBlock.COLORS[i] = RedstoneColors.getColor(i);
            }

            return InteractionResult.PASS;
        });
    }
}
