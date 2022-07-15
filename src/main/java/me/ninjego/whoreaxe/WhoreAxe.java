package me.ninjego.whoreaxe;

import me.ninjego.whoreaxe.commands.CommandManager;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhoreAxe implements ClientModInitializer {
	public static final String MOD_ID = "whoreaxe";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static WhoreAxe instance;
	private CommandManager commandManager;

	public static WhoreAxe getInstance() {
		return instance;
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	@Override
	public void onInitializeClient() {

		instance = this;
		commandManager = new CommandManager();
		commandManager.initCommands();

	}
}
