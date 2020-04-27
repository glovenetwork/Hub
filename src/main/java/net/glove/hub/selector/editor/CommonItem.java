package net.glove.hub.selector.editor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.glove.global.utils.items.ItemUtils;
import net.glove.global.utils.mongo.DocumentSerialize;
import org.bson.Document;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
@AllArgsConstructor
public class CommonItem implements DocumentSerialize {

    private ItemStack item;
    private int slot;

    @Override
    public Document serilize() {
        Document document = new Document();
        document.put("item", ItemUtils.serialize(item));
        document.put("slot", slot);
        return document;
    }
}