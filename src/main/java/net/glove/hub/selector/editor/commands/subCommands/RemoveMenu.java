package net.glove.hub.selector.editor.commands.subCommands;

import net.glove.global.utils.color.CC;
import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.editor.ServerMenu;
import org.bukkit.entity.Player;

@CommandInfo({"remove", "removemenu"})
public class RemoveMenu implements BaseCommand {

    @CommandExecutor
    public void execute(Player player, Integer id){
        if(ServerMenu.getMenus().containsKey(id)){
            player.sendMessage(CC.translate("&cMenu not found."));
            return;
        }
        ServerMenu.getMenus().remove(id);
    }

}