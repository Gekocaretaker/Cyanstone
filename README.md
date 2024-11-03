# Cyanstone

Cyanstone is a mod that allows the user (you!) to color redstone however you want, using just 2 images!

In vanilla Minecraft, the redstone wire colors are a hardcoded colormap, with all the other redstone blocks having the color built into the texture.

## For you
There are 15 built in resource packs exist (+ the default red) for the 16 dye colors. These will probably fit most wants.

## Your own stuff
Want to customize the redstone past what is given? Using just a colormap, you can color redstone to fit what you want.

To take full advantage of the mod, you can make your own resource pack. You need to have a 16x1 image at *minecraft/textures/colormap/redstone.png*, with each pixel being a different color. This goes darkest to lightest. There is a template for you to modify at *redstone_grayscale_template.png*.

If you want to change the trim color to match with the rest of your redstone needs, modify the 8x1 color palette *minecraft/textures/trims/color_palettes/redstone.png*, going lightest to darkest. There again is a grayscale template for you to start with.

If you want the names to match your custom redstone style, you can use the en_us.json file to your advantage.

## Advanced
This section will only be for mod developers who want to add support for their own redstone blocks and items (and possibly entities, if you want to try).

*If your block is opaque and will have parts of the texture that will not be colorized, make sure to set the block render layer to cutout.*

First you need to tell the game that your object needs to be colorized. There is a util class included called Colorizer that gives easy ways to colorize your blocks and items. It contains methods that will help keep your code cleaner. Each method contains a description that will hopefully help you know which one to use.

Next, the textures need to be dealt with. If you are dealing with blocks, just take the redstone parts and use your image editors grayscale feature. In GIMP, this is Colors>Desaturate>Color to Gray. If you need multiple overlay layers, like with the crafter, then do so. If you are working with an item, the base texture must not have any of the redstone part, or there will be texture clipping.

For your models, duplicate every element that will have a overlay, set the textures to the overlay, and add tintindex, set it to 1, 2, 3, etc. for what values you need. This will be decided by what you did with the Colorizer and renderer. Items just need item/generated and use layer0 as the base, and layerN, with N being the tintindex.

### Additional Information for Mod Developers
If your block has particles, you can just use *new DustParticleEffect(Cyanstone.VEC_COLOR, 1.0F)* for the particle to add to the world. The colors will be taken care of with this.