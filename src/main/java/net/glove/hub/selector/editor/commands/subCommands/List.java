package net.glove.hub.selector.editor.commands.subCommands;

import net.glove.global.utils.color.CC;
import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.editor.ServerMenu;
import org.bukkit.entity.Player;

@CommandInfo("list")
public class List implements BaseCommand {

    @CommandExecutor
    public void execute(Player player){
        ServerMenu.getMenus().values().forEach(serverMenu ->
            player.sendMessage(CC.translate("&eID&7:&f " + serverMenu.getID() + ", &eTitle&7:&f " + serverMenu.getTitle())));
    }

}