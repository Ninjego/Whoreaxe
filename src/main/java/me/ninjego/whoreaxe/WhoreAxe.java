package me.ninjego.whoreaxe;

import me.ninjego.whoreaxe.features.Feature;
import me.ninjego.whoreaxe.managers.ManagerProcessor;
import me.ninjego.whoreaxe.managers.impl.CommandManager;
import me.ninjego.whoreaxe.events.Event;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WhoreAxe implements ClientModInitializer {
	public static final String MOD_ID = "whoreaxe";
	public static final String MOD_NAME = "WhoreAxe";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static WhoreAxe instance;
	private ManagerProcessor managerProcessor;

	public static MinecraftClient mc;
	public static WhoreAxe getInstance() {
		return instance;
	}

	public ManagerProcessor getManagerProcessor() {
		return managerProcessor;
	}

	public void onEvent(Event e) {
		for(Feature feature : managerProcessor.getFeatureManager().getFeatureList()) {
			feature.onEvent(e);
		}
	}

	@Override
	public void onInitializeClient() {
		instance = this;
		mc = MinecraftClient.getInstance();

		managerProcessor = new ManagerProcessor();
		managerProcessor.init();

		managerProcessor.load();

	}
}
