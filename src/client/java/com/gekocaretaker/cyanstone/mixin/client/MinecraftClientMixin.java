package com.gekocaretaker.cyanstone.mixin.client;

import com.gekocaretaker.cyanstone.client.ClientFinishedLoadingCallback;
import com.gekocaretaker.cyanstone.client.resource.RedstoneColormapResourceSupplier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import net.minecraft.resource.ReloadableResourceManagerImpl;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Shadow @Final private ReloadableResourceManagerImpl resourceManager;

    @Inject(method = "<init>(Lnet/minecraft/client/RunArgs;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setupDefaultState(IIII)V"))
    private void injectToInit(RunArgs args, CallbackInfo ci) {
        this.resourceManager.registerReloader(new RedstoneColormapResourceSupplier());
    }

    @Inject(method = "onFinishedLoading(Lnet/minecraft/client/MinecraftClient$LoadingContext;)V", at = @At("TAIL"))
    private void injectToOnFinishedLoading(MinecraftClient.LoadingContext loadingContext, CallbackInfo ci) {
        ActionResult result = ClientFinishedLoadingCallback.EVENT.invoker().exist();

        if (result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
