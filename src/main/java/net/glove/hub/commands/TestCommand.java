package net.glove.hub.commands;

import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.ServerSelectorMenu;
import org.bukkit.entity.Player;


@CommandInfo({"test"})
public class TestCommand implements BaseCommand {

    @CommandExecutor
    public void execute(Player player) {
        new ServerSelectorMenu().openMenu(player);
    }
}