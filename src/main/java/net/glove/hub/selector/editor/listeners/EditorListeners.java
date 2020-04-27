package net.glove.hub.selector.editor.listeners;

import net.glove.hub.selector.editor.CommonItem;
import net.glove.hub.selector.editor.ServerItem;
import net.glove.hub.selector.editor.ServerMenu;
import net.glove.hub.selector.editor.commands.subCommands.CreateMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EditorListeners implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();
        if(!CreateMenu.getPlayerOpenMenu().containsKey(player.getUniqueId())){
            return;
        }
        ServerMenu serverMenu = ServerMenu.getMenus().get(CreateMenu.getPlayerOpenMenu().get(player.getUniqueId()));
        Inventory inventory = event.getInventory();
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            if(inventory.getItem(slot) == null){
                serverMenu.getServerItems().remove(serverMenu.getSeverItemBySlot(slot));
                serverMenu.getCommonItems().remove(serverMenu.getCommonItemBySlot(slot));
                continue;
            }
            ItemStack itemStack = inventory.getItem(slot);
            if(itemStack.hasItemMeta() && itemStack.getItemMeta().hasDisplayName()){
                int subMenu = 0;
                String server = "";
                if(itemStack.getItemMeta().hasLore()){
                    for (String lore: itemStack.getItemMeta().getLore()) {
                        if(ChatColor.stripColor(lore).toLowerCase().contains("submenu:")){
                           subMenu = Integer.parseInt(ChatColor.stripColor(lore).toLowerCase().replace("submenu: ", ""));
                        }else if(ChatColor.stripColor(lore).toLowerCase().contains("server:")){
                            server = ChatColor.stripColor(lore).replace("server: ", "");
                        }
                    }
                }

                ServerItem item = serverMenu.getSeverItemBySlot(slot);
                if(item == null){
                   serverMenu.getServerItems().add(new ServerItem(itemStack, subMenu, slot, server));
                }else {
                    item.setItem(itemStack);
                }
            }else {
                CommonItem item = serverMenu.getCommonItemBySlot(slot);
                if(item == null){
                    serverMenu.getCommonItems().add(new CommonItem(itemStack, slot));
                }else {
                    item.setItem(itemStack);
                }
            }
        }
        serverMenu.save();
        CreateMenu.getPlayerOpenMenu().remove(player.getUniqueId());
    }

}