package me.ninjego.whoreaxe.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.ninjego.whoreaxe.commands.impl.SayCommand;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientCommandSource;
import net.minecraft.command.CommandSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.ninjego.whoreaxe.commands.Command.mc;

public class CommandManager {

    private final CommandDispatcher<CommandSource> DISPATCHER = new CommandDispatcher<>();
    private final CommandSource COMMAND_SOURCE = new ChatCommandSource(mc);
    private final List<Command> commandList = new ArrayList<>();
    private final Map<Class<? extends Command>, Command> commandInstances = new HashMap<>();

    public void initCommands() {
        addCommand(new SayCommand());
    }

    public void addCommand(Command command) {
        command.registerAll(DISPATCHER);
        commandList.add(command);
        commandInstances.put(command.getClass(), command);
    }

    public void dispatch(String message) throws CommandSyntaxException {
        dispatch(message, new ChatCommandSource(mc));
    }

    public void dispatch(String message, CommandSource source) throws CommandSyntaxException {
        ParseResults<CommandSource> results = DISPATCHER.parse(message, source);
        DISPATCHER.execute(results);
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
