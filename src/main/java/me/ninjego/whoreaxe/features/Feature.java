package me.ninjego.whoreaxe.features;

import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.utils.MessageUtil;
import net.minecraft.client.MinecraftClient;

public class Feature {

    protected MinecraftClient mc = MinecraftClient.getInstance();

    private String name, description;
    private boolean toggleable, enabled;

    public Feature(String name, String description, boolean toggleable) {
        this.name = name;
        this.description = description;
        this.toggleable = toggleable;
        this.enabled = false;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onEvent(Event e) {}

    public void toggle() {
        enabled = !enabled;
        if(enabled) {
            onEnable();
            MessageUtil.info("\2475" + name + " \2477has been toggled \247aON");
        } else {
            onDisable();
            MessageUtil.info("\2475" + name + " \2477has been toggled \247cOFF");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isToggleable() {
        return toggleable;
    }

    public void setToggleable(boolean toggleable) {
        this.toggleable = toggleable;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }
}
