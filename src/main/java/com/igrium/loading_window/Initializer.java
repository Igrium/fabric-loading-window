package com.igrium.loading_window;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igrium.loading_window.event.WindowOpeningCallback;

public class Initializer implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("loading-window");

    @Override
    public void onInitialize() {
        WindowOpeningCallback.EVENT.register(() -> {
            PreLaunch.frame.ifPresent(frame -> {
                frame.setVisible(false);
                frame.dispose();
            });
        });
    }
}