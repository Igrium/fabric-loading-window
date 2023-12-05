package com.igrium.loading_window.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.igrium.loading_window.event.WindowOpeningCallback;

import net.minecraft.client.WindowEventHandler;
import net.minecraft.client.WindowSettings;
import net.minecraft.client.util.MonitorTracker;
import net.minecraft.client.util.Window;

@Mixin(Window.class)
public class WindowMixin {

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;assertInInitPhase()V", shift = Shift.AFTER))
    void loading_window$onInit(WindowEventHandler eventHandler, MonitorTracker monitorTracker, WindowSettings settings,
            @Nullable String videoMode, String title, CallbackInfo ci) {
        WindowOpeningCallback.EVENT.invoker().onWindowOpening();
    }
}
