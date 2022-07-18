package me.ninjego.whoreaxe.events.impl;

import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.renderer.Renderer3D;
import net.minecraft.client.util.math.MatrixStack;

public class Render3DEvent extends Event<Render3DEvent> {

    public static Render3DEvent instance = new Render3DEvent();

    public MatrixStack matrices;
    public Renderer3D renderer;
    public float tickDelta;
    public double offsetX, offsetY, offsetZ;

    public static Render3DEvent get(MatrixStack matrices, Renderer3D renderer, float tickDelta, double offsetX, double offsetY, double offsetZ) {
        instance.matrices = matrices;
        instance.renderer = renderer;
        instance.tickDelta = tickDelta;
        instance.offsetX = offsetX;
        instance.offsetY = offsetY;
        instance.offsetZ = offsetZ;
        return instance;
    }
}
