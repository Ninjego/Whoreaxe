package me.ninjego.whoreaxe.commands.impl;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.ninjego.whoreaxe.commands.Command;
import net.minecraft.command.CommandSource;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class VClipCommand extends Command {
    public VClipCommand() {
        super("vclip", "Clip vertical");
    }

    @Override
    public void literalBuilder(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("amount", IntegerArgumentType.integer())).executes(context -> {
            int amount = context.getArgument("amount", Integer.class);
            for(int i = 0; i < amount * 10; i++) {
                mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getPos().x, mc.player.getPos().y + 0.1, mc.player.getPos().z, true));
                mc.player.setPosition(mc.player.getPos().x, mc.player.getPos().y + 0.1, mc.player.getPos().z);
            }
            return SINGLE_SUCCESS;
        });
    }
}
