package me.ninjego.whoreaxe.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.util.math.Matrix4f;

import static me.ninjego.whoreaxe.WhoreAxe.mc;

public class Utils {

    public static boolean rendering3D = true;

    public static void unscaledProjection() {
        RenderSystem.setProjectionMatrix(Matrix4f.projectionMatrix(0, mc.getWindow().getFramebufferWidth(), 0, mc.getWindow().getFramebufferHeight(), 1000, 3000));
        rendering3D = false;
    }

    public static void scaledProjection() {
        RenderSystem.setProjectionMatrix(Matrix4f.projectionMatrix(0, (float) (mc.getWindow().getFramebufferWidth() / mc.getWindow().getScaleFactor()), 0, (float) (mc.getWindow().getFramebufferHeight() / mc.getWindow().getScaleFactor()), 1000, 3000));
        rendering3D = true;
    }

}
