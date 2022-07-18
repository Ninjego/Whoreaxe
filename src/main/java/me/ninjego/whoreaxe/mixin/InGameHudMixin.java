package me.ninjego.whoreaxe.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.events.impl.Render2DEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow
    private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow @Final
    private MinecraftClient client;


    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        WhoreAxe.getInstance().onEvent(Render2DEvent.get(scaledWidth, scaledHeight, tickDelta));
        RenderSystem.applyModelViewMatrix();
    }
}
