/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package me.ninjego.whoreaxe.renderer;

public enum ShapeMode {
    Lines,
    Sides,
    Both;

    public boolean lines() {
        return this == Lines || this == Both;
    }

    public boolean sides() {
        return this == Sides ||this == Both;
    }
}
