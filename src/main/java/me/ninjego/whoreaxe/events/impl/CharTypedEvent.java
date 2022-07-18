package me.ninjego.whoreaxe.events.impl;

import me.ninjego.whoreaxe.events.Event;

public class CharTypedEvent extends Event<CharTypedEvent> {

    public char c;

    public CharTypedEvent(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }
}
