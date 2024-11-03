package com.gekocaretaker.cyanstone.client.util;

import com.gekocaretaker.cyanstone.mixin.client.MinecraftClientAccessor;
import com.gekocaretaker.cyanstone.world.RedstoneColors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

@Environment(EnvType.CLIENT)
public class Colorizer {
    /**
     * Colorize the items entirely. Used for Redstone Dust & Block.
     * @param powerLevel The power level that the item(s) would have. Eg. Redstone Block has 15.
     * @param items The items that this color will be applied to.
     */
    public static void item(int powerLevel, ItemConvertible... items) {
        item((stack, tintIndex) -> {
            return RedstoneColors.getColor(powerLevel);
        }, items);
    }

    /**
     * Similar to item(powerLevel, items), however this colorizes all layers except layer0.
     * @param overlayPowerLevel The power level that the item(s) would have. Eg. Redstone Torch has 15 while Comparator has 0.
     * @param items The items that this color will be applied to.
     */
    public static void itemWithOverlay(int overlayPowerLevel, ItemConvertible... items) {
        item((stack, tintIndex) -> {
            if (tintIndex != 0) {
                return RedstoneColors.getColor(overlayPowerLevel);
            } else {
                return -1;
            }
        }, items);
    }

    /**
     * A shorthand for the colorizing items with your own provider.
     * @param provider Your provider. It must return an int color to colorize the item. Use -1 for no colorizing.
     * @param items The items to colorize.
     */
    public static void item(ItemColorProvider provider, ItemConvertible... items) {
        ((MinecraftClientAccessor) MinecraftClient.getInstance()).getItemColors().register(provider, items);
    }

    /**
     * Colorize all the blocks according to the power level of the block. The block must have the POWER property.
     * @param blocks The blocks to colorize.
     */
    public static void block(Block... blocks) {
        block((state, world, pos, tintIndex) -> {
            return RedstoneColors.getColor(state.get(Properties.POWER));
        }, blocks);
    }

    /**
     * Set the color of the blocks to a specific power level.
     * @param powerLevel The power level to set the blocks to.
     * @param blocks The blocks to colorize.
     */
    public static void block(int powerLevel, Block... blocks) {
        block((state, world, pos, tintIndex) -> {
            return RedstoneColors.getColor(powerLevel);
        }, blocks);
    }

    /**
     * Colorizes the blocks that have any tint index besides 0 according to the power level. Needs the POWER property.
     * @param blocks The blocks to colorize.
     */
    public static void blockWithOverlay(Block... blocks) {
        block((state, world, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return RedstoneColors.getColor(state.get(Properties.POWER));
            } else {
                return -1;
            }
        }, blocks);
    }

    /**
     * This colorizes if the BooleanProperty given is true.
     * @param blocks The blocks to be colorized.
     */
    public static void blockBoolOverlay(Property<Boolean> property, Block... blocks) {
        block((state, world, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return state.get(property) ? RedstoneColors.getColor(15) : RedstoneColors.getColor(2);
            } else {
                return -1;
            }
        }, blocks);
    }

    /**
     * Colorizes the blocks that have any tint index besides 0 according to the power level given.
     * @param overlayPowerLevel The power level of the overlay.
     * @param blocks The blocks to colorize.
     */
    public static void blockWithOverlay(int overlayPowerLevel, Block... blocks) {
        block((state, world, pos, tintIndex) -> {
            if (tintIndex != 0) {
                return RedstoneColors.getColor(overlayPowerLevel);
            } else {
                return -1;
            }
        }, blocks);
    }

    /**
     * A shorthand for the colorizing blocks with your own provider.
     * @param provider Your provider. It must return an int color to colorize the block. Use -1 for no colorizing.
     * @param blocks The blocks to colorize.
     */
    public static void block(BlockColorProvider provider, Block... blocks) {
        MinecraftClient.getInstance().getBlockColors().registerColorProvider(provider, blocks);
    }
}
