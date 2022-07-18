package me.ninjego.whoreaxe.commands.impl;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.ninjego.whoreaxe.WhoreAxe;
import me.ninjego.whoreaxe.commands.Command;
import net.minecraft.command.CommandSource;

import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class ServerSpooferCommand extends Command {
    public ServerSpooferCommand() {
        super("serverspoofer", "Spoof server login request toggle command", "spoof");
    }

    @Override
    public void literalBuilder(LiteralArgumentBuilder<CommandSource> builder) {
        builder.executes(context -> {

            WhoreAxe.getInstance().getManagerProcessor().getFeatureManager().getFeature("ServerSpoofer").toggle();

            return SINGLE_SUCCESS;
        });
    }
}
