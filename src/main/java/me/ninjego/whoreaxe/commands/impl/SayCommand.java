package me.ninjego.whoreaxe.commands.impl;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.ninjego.whoreaxe.commands.Command;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.command.CommandSource;
import net.minecraft.network.message.MessageSignature;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Stream;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class SayCommand extends Command {

    public SayCommand() {
        super("say", "Sends chat message.");
    }

    @Override
    public void literalBuilder(LiteralArgumentBuilder<CommandSource> builder) {
        builder.then(argument("message", StringArgumentType.greedyString()).executes(context -> {

            String msg = context.getArgument("message", String.class);

            if (msg != null) {
                mc.player.sendChatMessage(msg);
            }

            return SINGLE_SUCCESS;
        }));
    }
}
