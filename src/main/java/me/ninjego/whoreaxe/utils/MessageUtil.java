package me.ninjego.whoreaxe.utils;

import me.ninjego.whoreaxe.WhoreAxe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

public class MessageUtil {

    protected static final MinecraftClient mc = MinecraftClient.getInstance();

    private static final MutableText prefix = Text.empty().append("\2478[").append(Text.empty().append(WhoreAxe.MOD_NAME).setStyle(Style.EMPTY.withColor(16711835))).append("\2478]\2477 ");

    public static void info(String text) {
        mc.player.sendMessage(prefix.copy().append(text));
    }

    public static void error(String error) {
        mc.player.sendMessage(Text.of(prefix + "\247c" + error));
    }

}
