package com.gekocaretaker.cyanstone.client;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface ClientFinishedLoadingCallback {
    Event<ClientFinishedLoadingCallback> EVENT = EventFactory.createArrayBacked(ClientFinishedLoadingCallback.class,
            (listeners) -> () -> {
                for (ClientFinishedLoadingCallback listener : listeners) {
                    ActionResult result = listener.exist();

                    if (result != ActionResult.SUCCESS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult exist();
}
