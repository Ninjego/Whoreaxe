package me.ninjego.whoreaxe.managers.impl;

import me.ninjego.whoreaxe.features.Feature;
import me.ninjego.whoreaxe.features.impl.ESP;
import me.ninjego.whoreaxe.features.impl.Phase;
import me.ninjego.whoreaxe.features.impl.ServerSpoofer;
import me.ninjego.whoreaxe.managers.Manager;

import java.util.ArrayList;
import java.util.List;

public class FeatureManager extends Manager {

    private List<Feature> featureList = new ArrayList<>();

    @Override
    public void init() {
        featureList.add(new Phase());
        featureList.add(new ServerSpoofer());
        featureList.add(new ESP());
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public Feature getFeature(String name) {
        for(Feature feature : featureList) {
            if(feature.getName().equalsIgnoreCase(name)) {
                return feature;
            }
        }
        return null;
    }
}
