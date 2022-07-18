package me.ninjego.whoreaxe.commands.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.commands.Command;
import me.ninjego.whoreaxe.features.Feature;
import net.minecraft.command.CommandSource;

public class ESPCommand extends Command {

    public ESPCommand() {
        super("esp", "ESP feature toggle");
    }


    @Override
    public void literalBuilder(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {

            WhoreAxe.getInstance().getManagerProcessor().getFeatureManager().getFeature("ESP").toggle();

            return 1;
        });
    }
}
