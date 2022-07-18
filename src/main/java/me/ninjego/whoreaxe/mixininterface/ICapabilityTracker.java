/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package me.ninjego.whoreaxe.mixininterface;

public interface ICapabilityTracker {
    boolean get();

    void set(boolean state);
}
