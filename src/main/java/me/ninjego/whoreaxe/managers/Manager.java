package me.ninjego.whoreaxe.managers;

import net.minecraft.client.MinecraftClient;

public abstract class Manager {

    protected static MinecraftClient mc = MinecraftClient.getInstance();

    public abstract void init();

}
