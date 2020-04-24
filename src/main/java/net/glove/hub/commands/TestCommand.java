package net.glove.hub.commands;

import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import org.bukkit.command.CommandSender;


@CommandInfo({"test"})
public class TestCommand implements BaseCommand {

    @CommandExecutor
    public void execute(CommandSender sender) {

    }
}