package me.ninjego.whoreaxe.features.impl;

import io.netty.buffer.Unpooled;
import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.events.impl.PacketEvent;
import me.ninjego.whoreaxe.features.Feature;
import me.ninjego.whoreaxe.mixin.CustomPayloadC2SPacketAccessor;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;

import java.nio.charset.StandardCharsets;

public class ServerSpoofer extends Feature {
    public ServerSpoofer() {
        super("ServerSpoofer", "Spoof server login request", true);
    }

    @Override
    public void onEvent(Event e) {
        if(!isEnabled()) return;

        if(e instanceof PacketEvent) {
            PacketEvent event = (PacketEvent) e;
            if(event.isOutgoing()) {
                if(!(event.packet instanceof CustomPayloadC2SPacket)) return;
                CustomPayloadC2SPacketAccessor packet = (CustomPayloadC2SPacketAccessor) event.packet;
                Identifier identifier = packet.getChannel();
                if (identifier.equals(CustomPayloadC2SPacket.BRAND)) {
                    packet.setData(new PacketByteBuf(Unpooled.buffer()).writeString("vanilla"));
                } else if(packet.getData().toString(StandardCharsets.UTF_8).toLowerCase().contains("fabric")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
