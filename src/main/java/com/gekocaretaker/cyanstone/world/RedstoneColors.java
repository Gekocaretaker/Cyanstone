package com.gekocaretaker.cyanstone.world;

public class RedstoneColors {
    private static int[] colorMap = new int[16];

    public RedstoneColors() {
    }

    public static void setColorMap(int[] pixels) {
        colorMap = pixels;
    }

    public static int getColor(int power) {
        return power >= colorMap.length ? 0 : colorMap[power];
    }

    public static int getDefaultColor() {
        return getColor(0);
    }
}
