package com.gekocaretaker.cyanstone.mixin.client;

import com.gekocaretaker.cyanstone.client.ClientFinishedLoadingCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {
    @Inject(method = "onResourceLoadFinished(Lnet/minecraft/client/Minecraft$GameLoadCookie;)V", at = @At("TAIL"), cancellable = true)
    private void injectToOnFinishedLoading(Minecraft.GameLoadCookie gameLoadCookie, CallbackInfo ci) {
        InteractionResult result = ClientFinishedLoadingCallback.EVENT.invoker().exist();

        if (result == InteractionResult.FAIL) {
            ci.cancel();
        }
    }
}
