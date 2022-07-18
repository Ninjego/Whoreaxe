package me.ninjego.whoreaxe.features.impl;

import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.events.impl.Render2DEvent;
import me.ninjego.whoreaxe.features.Feature;
import me.ninjego.whoreaxe.renderer.Renderer2D;
import me.ninjego.whoreaxe.utils.Vec3;
import me.ninjego.whoreaxe.utils.color.Color;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

public class ESP extends Feature {

    public ESP() {
        super("ESP", "Player ESP", true);
    }

    private final Vec3 pos1 = new Vec3();
    private final Vec3 pos2 = new Vec3();
    private final Vec3 pos = new Vec3();

    @Override
    public void onEvent(Event e) {
        if(!this.isEnabled()) return;

        if(e instanceof Render2DEvent) {
            Render2DEvent event = (Render2DEvent) e;

            Renderer2D.COLOR.begin();

            for(Entity entity : mc.world.getEntities()) {
                Box box = entity.getBoundingBox();
                double x = MathHelper.lerp(event.tickDelta, entity.lastRenderX, entity.getX()) - entity.getX();
                double y = MathHelper.lerp(event.tickDelta, entity.lastRenderX, entity.getY()) - entity.getY();
                double z = MathHelper.lerp(event.tickDelta, entity.lastRenderX, entity.getZ()) - entity.getZ();

                pos1.set(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
                pos2.set(0,0,0);


                Renderer2D.COLOR.quad(pos1.x, pos1.y, pos2.x - pos1.x,pos2.y - pos1.y, new Color(255, 0,105));
            }

            Renderer2D.COLOR.render(null);
        }
    }
}
