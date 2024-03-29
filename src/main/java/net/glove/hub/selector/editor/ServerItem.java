package net.glove.hub.selector.editor;

import lombok.*;
import net.glove.global.utils.items.ItemUtils;
import net.glove.global.utils.mongo.DocumentSerialize;
import org.bson.Document;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
@AllArgsConstructor
public class ServerItem implements DocumentSerialize {

    private ItemStack item;
    private int subMenuID;
    private int slot;
    private String server;

    @Override
    public Document serilize() {
        Document document = new Document();
        document.put("item", ItemUtils.serialize(item));
        document.put("submenuid", subMenuID);
        document.put("slot", slot);
        document.put("server", server);
        return document;
    }
}