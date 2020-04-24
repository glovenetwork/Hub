package net.glove.hub.selector.editor;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class ServerItem {

    private ItemStack item;
    @Accessors(fluent = true) private boolean hasSubServer;
    private int slot;


}