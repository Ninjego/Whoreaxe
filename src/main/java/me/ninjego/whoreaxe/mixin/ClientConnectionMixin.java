package me.ninjego.whoreaxe.mixin;

import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.events.EventDirection;
import me.ninjego.whoreaxe.events.impl.PacketEvent;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.PacketListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {

    @Inject(method = "handlePacket", at = @At("HEAD"), cancellable = true)
    private static <T extends PacketListener> void onHandlePacket(Packet<T> packet, PacketListener listener, CallbackInfo info) {
        PacketEvent event = new PacketEvent(packet);
        event.setDirection(EventDirection.IN);
        WhoreAxe.getInstance().onEvent(event);
        if(event.isCancelled()) {
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "send(Lnet/minecraft/network/Packet;)V", cancellable = true)
    private void onSendPacketHead(Packet<?> packet, CallbackInfo info) {
        PacketEvent event = new PacketEvent(packet);
        event.setDirection(EventDirection.OUT);
        WhoreAxe.getInstance().onEvent(event);
        if(event.isCancelled()) {
            info.cancel();
        }
    }

}
