package net.glove.hub.selector;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import net.glove.global.utils.items.ItemBuilder;
import net.glove.global.utils.menu.Button;
import net.glove.global.utils.menu.Menu;
import net.glove.hub.selector.editor.CommonItem;
import net.glove.hub.selector.editor.ServerItem;
import net.glove.hub.selector.editor.ServerMenu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ServerSelectorMenu extends Menu {

    private String title;
    private List<ServerItem> items;
    private List<CommonItem> commonItems;

    @Override
    public String getTitle(Player player) {
        return title;
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();
        items.forEach(item -> buttons.put(item.getSlot(), new ServerButton(item)));
        commonItems.forEach(item -> buttons.put(item.getSlot(), new CommonButton(item)));
        return buttons;
    }

    @AllArgsConstructor
    private class CommonButton extends Button {
        private CommonItem commonItem;

        @Override
        public ItemStack getButtonItem(Player player) {
            return commonItem.getItem();
        }
    }

    @AllArgsConstructor
    private class ServerButton extends Button{

        private ServerItem serverItem;

        @Override
        public ItemStack getButtonItem(Player player) {

            ItemStack itemStack = serverItem.getItem().clone();

            String name = itemStack.getItemMeta().getDisplayName()
                .replace("{online}", "&aOnline")
                .replace("{players_online}", "&a1")
                .replace("{max_players}", "&a100");

            List<String> lore = Lists.newArrayList();

            if(itemStack.getItemMeta().hasLore()){
                lore = itemStack.getItemMeta().getLore().stream()
                    .filter(string -> !ChatColor.stripColor(string).toLowerCase().contains("submenu:"))
                    .filter(string -> !ChatColor.stripColor(string).toLowerCase().contains("server:"))
                    .map(string -> string.replace("{online}", "&aOnline"))
                    .map(string -> string.replace("{players_online}", "&a1"))
                    .map(string -> string.replace("{max_players}", "&a100"))
                    .collect(Collectors.toList());
            }

            return new ItemBuilder(itemStack).name(name).lore(lore).build();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            if(serverItem.getSubMenuID() == 0){
                player.sendMessage("Sending to " + serverItem.getServer() + "...");
                return;
            }

            ServerMenu menu = ServerMenu.getMenus().get(serverItem.getSubMenuID());
            menu.open(player);
        }
    }

}