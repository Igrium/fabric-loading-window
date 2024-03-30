package com.igrium.loading_window;

import java.util.LinkedList;
import java.util.List;

public final class WindowOpenListener {
    private static final List<Runnable> listeners = new LinkedList<>();
    
    public static List<Runnable> getListeners() {
        return listeners;
    }

    public static void trigger() {
        for (var l : listeners) {
            l.run();
        }
    }
}
