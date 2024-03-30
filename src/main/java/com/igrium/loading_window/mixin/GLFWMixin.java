package com.igrium.loading_window.mixin;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.NativeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.igrium.loading_window.WindowOpenListener;

// I know this is BAAAD but it's the only way I can think to get notified when the window is created regardless of MC version.
@Mixin(GLFW.class)
public class GLFWMixin {

    @Inject(method = "glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", at = @At("HEAD"), remap = false)
    private static void loading_window$onCreateWindow(int width, int height,
            @NativeType("char const *") CharSequence title, @NativeType("GLFWmonitor *") long monitor,
            @NativeType("GLFWwindow *") long share, CallbackInfoReturnable<Long> ci) {
        WindowOpenListener.trigger();
    }
}
