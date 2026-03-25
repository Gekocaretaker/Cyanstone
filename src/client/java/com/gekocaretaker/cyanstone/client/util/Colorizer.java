package com.gekocaretaker.cyanstone.client.util;

import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockColorRegistry;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.List;

@Environment(EnvType.CLIENT)
public class Colorizer {
    /**
     * Colorize all the blocks according to the power level of the block. The block must have the POWER property.
     * @param blocks The blocks to colorize.
     */
    public static void block(Block... blocks) {
        block(state -> RedstoneColors.getColor(state.getValue(BlockStateProperties.POWER)), blocks);
    }

    /**
     * Set the color of the blocks based on a boolean property.
     * @param property The property used to determine the color
     * @param blocks The blocks to be colorized.
     */
    public static void block(BooleanProperty property, Block... blocks) {
        block(state -> state.getValue(property) ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2), blocks);
    }

    /**
     * Set the color of the blocks to a specific power level.
     * @param powerLevel The power level to set the blocks to.
     * @param blocks The blocks to colorize.
     */
    public static void block(int powerLevel, Block... blocks) {
        block(_ -> RedstoneColors.getColor(powerLevel), blocks);
    }

    /**
     * Colorizes the blocks that have a tint index of 0 according to the power level. Needs the POWER property.
     * @param blocks The blocks to colorize.
     * @deprecated Use block(Block...) instead, as both fulfill the same purpose.
     */
    @Deprecated(forRemoval = true)
    public static void blockWithOverlay(Block... blocks) {
        block(blocks);
    }

    /**
     * This colorizes if the BooleanProperty given is true.
     * @param property The property used to determine the color
     * @param blocks The blocks to be colorized.
     * @deprecated Use block(BooleanProperty, Block...) instead, as both fulfill the same purpose.
     */
    @Deprecated(forRemoval = true)
    public static void blockBoolOverlay(BooleanProperty property, Block... blocks) {
        block(property, blocks);
    }

    /**
     * Colorizes the blocks that have a tint index of 0 according to the power level given.
     * @param overlayPowerLevel The power level of the overlay.
     * @param blocks The blocks to colorize.
     * @deprecated Use block(int, Block...) instead, as both fulfill the same purpose.
     */
    @Deprecated(forRemoval = true)
    public static void blockWithOverlay(int overlayPowerLevel, Block... blocks) {
        block(overlayPowerLevel, blocks);
    }

    /**
     * A shorthand for the colorizing blocks with your own source.
     * @param source Your source. It must return an int color to colorize the block. Use -1 for no colorizing.
     * @param blocks The blocks to colorize.
     */
    public static void block(BlockTintSource source, Block... blocks) {
        BlockColorRegistry.register(List.of(source), blocks);
    }

    /**
     * A shorthand for the colorizing block with your own list of providers.
     * @param sources Your sources. Each one must return an int color to colorize the block at the specific tintIndex. Use -1 for no colorizing.
     * @param blocks The blocks to colorize.
     */
    public static void block(List<BlockTintSource> sources, Block... blocks) {
        BlockColorRegistry.register(sources, blocks);
    }
}
