package com.gekocaretaker.cyanstone.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.InteractionResult;

public interface ClientFinishedLoadingCallback {
    Event<ClientFinishedLoadingCallback> EVENT = EventFactory.createArrayBacked(ClientFinishedLoadingCallback.class,
            (listeners) -> () -> {
                for (ClientFinishedLoadingCallback listener : listeners) {
                    InteractionResult result = listener.exist();

                    if (result != InteractionResult.SUCCESS) {
                        return result;
                    }
                }
                return InteractionResult.PASS;
            });

    InteractionResult exist();
}
