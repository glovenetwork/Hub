package net.glove.hub.selector.editor.commands.subCommands;

import com.google.common.collect.Maps;
import lombok.Getter;
import net.glove.global.utils.color.CC;
import net.glove.global.utils.command.BaseCommand;
import net.glove.global.utils.command.annotation.CommandExecutor;
import net.glove.global.utils.command.annotation.CommandInfo;
import net.glove.hub.selector.editor.ServerMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.UUID;

@CommandInfo({"createmenu", "create"})
public class CreateMenu implements BaseCommand {

    @Getter private static Map<UUID, Integer> playerOpenMenu = Maps.newHashMap();

    @CommandExecutor
    public void execute(Player player, String title){
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, CC.translate("&9Editing&7:&f " + title));
        player.openInventory(inventory);
        ServerMenu serverMenu = new ServerMenu(ServerMenu.getMenus().size() + 1);
        serverMenu.setTitle(title);
        ServerMenu.getMenus().put(serverMenu.getID(), serverMenu);
        playerOpenMenu.put(player.getUniqueId(), serverMenu.getID());
    }

}