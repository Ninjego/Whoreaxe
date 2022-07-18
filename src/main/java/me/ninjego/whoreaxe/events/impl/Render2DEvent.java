package me.ninjego.whoreaxe.events.impl;

import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.renderer.Renderer3D;
import net.minecraft.client.util.math.MatrixStack;

public class Render2DEvent extends Event<Render2DEvent> {

    public static Render2DEvent instance = new Render2DEvent();

    public int screenWidth, screenHeight;
    public float tickDelta;

    public static Render2DEvent get(int screenWidth, int screenHeight, float tickDelta) {
        instance.screenHeight = screenHeight;
        instance.screenWidth = screenWidth;
        instance.tickDelta = tickDelta;
        return instance;
    }

}
