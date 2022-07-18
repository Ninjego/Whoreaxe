package me.ninjego.whoreaxe.events.impl;

import me.ninjego.whoreaxe.events.Event;
import net.minecraft.network.Packet;

public class PacketEvent extends Event<PacketEvent> {
    public Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public Packet<?> getPacket() {
        return packet;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }
}
