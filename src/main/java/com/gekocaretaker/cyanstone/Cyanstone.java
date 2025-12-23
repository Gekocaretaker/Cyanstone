package com.gekocaretaker.cyanstone;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Cyanstone implements ModInitializer {
    public static final String MOD_ID = "cyanstone";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
    }

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
