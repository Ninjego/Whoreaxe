package me.ninjego.whoreaxe.mixin;

import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.events.impl.CharTypedEvent;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {

    @Inject(method = "onChar", at = @At("HEAD"), cancellable = true)
    private void onChar(long window, int i, int j, CallbackInfo info) {
        WhoreAxe.getInstance().onEvent(new CharTypedEvent((char) i));
    }

}
