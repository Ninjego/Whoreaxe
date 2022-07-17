package me.ninjego.whoreaxe.mixin;

import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.events.EventType;
import me.ninjego.whoreaxe.events.impl.TickEvent;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftClient.class, priority =  1001)
public class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    private void onPreTick(CallbackInfo info) {
        TickEvent tickEvent = new TickEvent();
        tickEvent.setType(EventType.PRE);
        WhoreAxe.getInstance().onEvent(tickEvent);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTick(CallbackInfo info) {
        TickEvent tickEvent = new TickEvent();
        tickEvent.setType(EventType.POST);
        WhoreAxe.getInstance().onEvent(tickEvent);
    }

}
