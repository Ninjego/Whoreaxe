package me.ninjego.whoreaxe.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandSource;

import java.util.*;

public abstract class Command {

    protected static MinecraftClient mc;

    private final String name, description;
    private final List<String> aliases = new ArrayList<>();

    public Command(String name, String description, String... aliases) {
        mc = MinecraftClient.getInstance();
        this.name = name;
        this.description = description;
        Collections.addAll(this.aliases, aliases);
    }

    protected static <T> RequiredArgumentBuilder<CommandSource, T> argument(final String name, final ArgumentType<T> type) {
        return RequiredArgumentBuilder.argument(name, type);
    }

    protected static LiteralArgumentBuilder<CommandSource> literal(final String name) {
        return LiteralArgumentBuilder.literal(name);
    }

    public final void registerAll(CommandDispatcher<CommandSource> dispatcher) {
        register(dispatcher, name);
        for(String alias : aliases) register(dispatcher, alias);
    }

    public void register(CommandDispatcher<CommandSource> dispatcher, String arg1) {
        LiteralArgumentBuilder<CommandSource> builder = LiteralArgumentBuilder.literal(arg1);
        literalBuilder(builder);
        dispatcher.register(builder);
    }

    public abstract void literalBuilder(LiteralArgumentBuilder<CommandSource> builder);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAliases() {
        return aliases;
    }

}
