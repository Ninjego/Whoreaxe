package me.ninjego.whoreaxe.managers.impl;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.ninjego.whoreaxe.commands.Command;
import me.ninjego.whoreaxe.commands.impl.*;
import me.ninjego.whoreaxe.managers.Manager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager extends Manager {

    protected static MinecraftClient mc = MinecraftClient.getInstance();

    private final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();
    private final CommandSource COMMAND_SOURCE = new ChatCommandSource(mc);
    private final List<Command> commandList = new ArrayList<>();
    private final Map<Class<? extends Command>, Command> commandInstances = new HashMap<>();

    @Override
    public void init() {
        addCommand(new SayCommand());
        addCommand(new PhaseCommand());
        addCommand(new VClipCommand());
        addCommand(new ServerSpooferCommand());
        addCommand(new ESPCommand());
    }

    public void addCommand(Command command) {
        command.registerAll(DISPATCHER);
        commandList.add(command);
        commandInstances.put(command.getClass(), command);
    }

    public boolean dispatch(String message){
        return dispatch(message, new ChatCommandSource(mc));
    }

    public boolean dispatch(String message, CommandSource source) {
        try {
            ParseResults<CommandSource> results = DISPATCHER.parse(message.toLowerCase(), source);
            DISPATCHER.execute(results);
            return false;
        } catch (CommandSyntaxException e) {
            return true;
        }
    }

    private final static class ChatCommandSource extends ClientCommandSource {
        public ChatCommandSource(MinecraftClient client) {
            super(null, client);
        }
    }

    public CommandDispatcher<CommandSource> getDISPATCHER() {
        return DISPATCHER;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public Map<Class<? extends Command>, Command> getCommandInstances() {
        return commandInstances;
    }

    public <T extends Command> T get(Class<T> klass) {
        return (T) commandInstances.get(klass);
    }
}
