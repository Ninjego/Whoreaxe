package me.ninjego.whoreaxe.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.events.impl.Render3DEvent;
import me.ninjego.whoreaxe.renderer.Renderer3D;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow
    public abstract void updateTargetedEntity(float tickDelta);

    @Shadow public abstract void reset();

    @Mutable
    @Shadow @Final
    private Camera camera;
    @Unique
    private Renderer3D renderer;

    @Inject(method = "renderWorld", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = { "ldc=hand" }), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void onRenderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo info) {
        if(renderer == null) renderer = new Renderer3D();
        camera = this.camera;
        Render3DEvent event = Render3DEvent.get(matrices, renderer, tickDelta, camera.getPos().x, camera.getPos().getY(), camera.getPos().getZ());
        renderer.begin();
        WhoreAxe.getInstance().onEvent(event);
        renderer.render(matrices);
    }

}
