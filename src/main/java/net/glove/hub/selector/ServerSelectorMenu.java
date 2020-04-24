package net.glove.hub.selector;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import net.glove.global.utils.items.ItemBuilder;
import net.glove.global.utils.menu.Button;
import net.glove.global.utils.menu.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ServerSelectorMenu extends Menu {

    @Override
    public String getTitle(Player player) {
        return "Sever Selector";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = Maps.newHashMap();

        buttons.put(buttons.size(), new ServerButton(
            Material.DIRT,
            (short)0,
            "Survival",
            Arrays.asList(
                "&aClick to join",
                "&aPlayers online&7:&e (1/100)"
            )));

        return buttons;
    }


    @AllArgsConstructor
    private class ServerButton extends Button{

        private Material material;
        private short durability;
        private String name;
        private List<String> lore;

        @Override
        public ItemStack getButtonItem(Player player) {
            return new ItemBuilder(material).durability(durability).name(name).lore(lore).build();
        }
    }

}