package me.ninjego.whoreaxe.managers;

import me.ninjego.whoreaxe.managers.impl.CommandManager;
import me.ninjego.whoreaxe.managers.impl.FeatureManager;

import java.util.ArrayList;
import java.util.List;

public class ManagerProcessor extends Manager {

    private List<Manager> managerList = new ArrayList<>();

    private FeatureManager featureManager;

    private CommandManager commandManager;

    @Override
    public void init() {
        featureManager = new FeatureManager();
        managerList.add(featureManager);

        commandManager = new CommandManager();
        managerList.add(commandManager);
    }

    public void load() {
        for(Manager manager : managerList) {
            manager.init();
        }
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }

    public List<Manager> getManagerList() {
        return managerList;
    }
}
