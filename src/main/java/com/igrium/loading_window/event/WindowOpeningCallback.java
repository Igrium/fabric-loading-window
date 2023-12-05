package com.igrium.loading_window.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

/**
 * Called right before the Minecraft window opens.
 */
public interface WindowOpeningCallback {

    public static final Event<WindowOpeningCallback> EVENT = EventFactory.createArrayBacked(
        WindowOpeningCallback.class, listeners -> () -> {
            for (var l : listeners) {
                l.onWindowOpening();
            }
        });

    void onWindowOpening();
}
