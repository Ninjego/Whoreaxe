package me.ninjego.whoreaxe.features.impl;

import me.ninjego.whoreaxe.events.Event;
import me.ninjego.whoreaxe.events.EventType;
import me.ninjego.whoreaxe.events.impl.TickEvent;
import me.ninjego.whoreaxe.features.Feature;
import me.ninjego.whoreaxe.utils.Timer;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class Phase extends Feature {

    private Timer timer;

    public Phase() {
        super("Phase", "Phase through walls", true);
    }

    @Override
    public void onEnable() {
        this.timer = new Timer();
    }

    @Override
    public void onDisable() {
        mc.player.noClip = false;
    }

    @Override
    public void onEvent(Event e) {
        if(!this.isEnabled())
            return;

        if(e instanceof TickEvent) {
            TickEvent event = (TickEvent) e;
            double mz2;
            double z;
            double x;
            double mx2;
            mx2 = Math.cos(Math.toRadians((double) mc.player.headYaw + 90.0F));
            mz2 = Math.sin(Math.toRadians((double) mc.player.headYaw + 90.0F));
            x = (double) mc.player.input.movementForward * 0.3D * mx2 + (double) mc.player.input.movementSideways * 0.3D * mz2;
            z = (double) mc.player.input.movementForward * 0.3D * mz2 + (double) mc.player.input.movementSideways * 0.3D * mz2;
            if(event.type == EventType.POST && mc.player.collides() && !mc.player.isHoldingOntoLadder() && !mc.player.isInsideWall() && this.timer.hasPassed(150.0D)) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getPos().x + x, mc.player.getPos().y, mc.player.getPos().z + z, true));

                for(int i = 0; i < 1; i++) {
                    mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getPos().x + x, mc.player.getPos().y, mc.player.getPos().z + z, true));
                }

                mc.player.setPosition(mc.player.getPos().x + x, mc.player.getPos().y, mc.player.getPos().z + z);
            }
        }
    }
}
