package com.gekocaretaker.cyanstone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joml.Vector3f;

import java.util.*;

public class Cyanstone implements ModInitializer {
    public static final String MOD_ID = "cyanstone";
    public static final Logger LOGGER = LogManager.getLogger();
    public static Vector3f VEC_COLOR = Vec3d.unpackRgb(0).toVector3f();

    private static final Map<String, String> colors = new HashMap<>(){{
        put("black", "Black");
        put("blue", "Blue");
        put("brown", "Brown");
        put("cyan", "Cyan");
        put("gray", "Gray");
        put("green", "Green");
        put("light_blue", "Aqua");
        put("light_gray", "Cloud");
        put("lime", "Lime");
        put("magenta", "Magenta");
        put("orange", "Orange");
        put("pink", "Pink");
        put("purple", "Purple");
        put("white", "White");
        put("yellow", "Yellow");
    }};

    @Override
    public void onInitialize() {
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(MOD_ID);
        //modContainer.ifPresent(container -> ResourceManagerHelper.registerBuiltinResourcePack(id("rs_cyan"), container, Text.of("Cyanstone"), ResourcePackActivationType.NORMAL));
        colors.forEach((folder, name) -> {
            modContainer.ifPresent(container -> ResourceManagerHelper.registerBuiltinResourcePack(id("rs_" + folder), container, Text.of(name + "stone"), ResourcePackActivationType.NORMAL));
        });
    }

    public static Identifier id(String name) {
        return Identifier.of(MOD_ID, name);
    }
}
