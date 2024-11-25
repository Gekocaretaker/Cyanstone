package com.gekocaretaker.cyanstone.client.support;

import com.gekocaretaker.cyanstone.Cyanstone;
import com.gekocaretaker.cyanstone.client.util.Colorizer;
import com.gekocaretaker.cyanstone.world.RedstoneColors;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import earth.terrarium.chipped.common.registry.ModBlocks;
import net.minecraft.block.Block;
import java.util.ArrayList;
import java.util.List;

public class Chipped {
    private static final List<String> DISALLOWED_BLOCKS = new ArrayList<>() {{
        add("bordered_redstone_block");
        add("brick_bordered_redstone_block");
        add("curly_redstone_block_pillar");
        add("cut_redstone_block_column");
        add("edged_redstone_block_bricks");
        add("fine_redstone_block_pillar");
        add("massive_redstone_block_bricks");
        add("ornate_redstone_block_pillar");
        add("overlapping_redstone_block_tiles");
        add("polished_redstone_block");
        add("simple_redstone_block_pillar");
        add("smooth_redstone_block_column");
        add("thick_inlayed_redstone_block");
        add("tiled_bordered_redstone_block");
        add("tiled_redstone_block_column");
        add("tiny_brick_bordered_redstone_block");
    }};

    private static final List<Block> REDSTONE_BLOCK = new ArrayList<>() {{
        for (RegistryEntry<Block> block : ModBlocks.REDSTONE_BLOCK.getEntries()) {
            if (!DISALLOWED_BLOCKS.contains(block.getId().getPath())) {
                add(block.get());
            } else {
                Cyanstone.LOGGER.warn("Block '" + block.getId().toString() + "' is not allowed to be tinted.");
            }
        }
    }};

    // Use this if any blocks will have multiple layers. Since Chipped only has full redstone blocks, there is no need to use it.
    /*public static List<Block> cutoutBlocks() {
        List<Block> ret = new ArrayList<>();
        for(RegistryEntry<Block> block : ModBlocks.REDSTONE_BLOCK.getEntries()) {
            ret.add(block.get());
        }
        return ret;
    }*/

    public static void register() {
        Colorizer.block(15, REDSTONE_BLOCK.toArray(new Block[0]));
        Colorizer.item(15, REDSTONE_BLOCK.toArray(new Block[0]));

        Colorizer.block((state, world, pos, tintIndex) -> {
            if (tintIndex == 1) {
                return RedstoneColors.getColor(15);
            } else if (tintIndex == 2) {
                return RedstoneColors.getColor(2);
            } else {
                return -1;
            }
        }, ModBlocks.TINKERING_TABLE.get());
    }
}
