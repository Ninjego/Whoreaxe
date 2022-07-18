/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package me.ninjego.whoreaxe.utils;

public interface ICopyable<T extends ICopyable<T>> {
    T set(T value);

    T copy();
}
