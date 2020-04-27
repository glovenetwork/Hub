package net.glove.hub.selector.editor.commands.subCommands;

import net.glove.global.utils.color.CC;
import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.editor.ServerMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@CommandInfo({"editmenu", "edit"})
public class EditMenu implements BaseCommand {

    @CommandExecutor
    public void execute(Player player, Integer id){
        ServerMenu menu = ServerMenu.getMenus().get(id);
        if(menu == null){
            player.sendMessage(CC.translate("&cMenu not found."));
            return;
        }

        Inventory inventory = Bukkit.createInventory(null, 9 * 6, CC.translate("&9Editing&7:&f " + menu.getTitle()));

        menu.getCommonItems().forEach(commonItem -> inventory.setItem(commonItem.getSlot(), commonItem.getItem()));

        menu.getServerItems().forEach(serverItem -> inventory.setItem(serverItem.getSlot(), serverItem.getItem()));

        player.openInventory(inventory);
        CreateMenu.getPlayerOpenMenu().put(player.getUniqueId(), id);
    }

}