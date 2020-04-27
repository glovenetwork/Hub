package net.glove.hub.selector.editor;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.glove.global.utils.items.ItemUtils;
import net.glove.hub.Hub;
import net.glove.hub.selector.ServerSelectorMenu;
import org.bson.Document;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Getter @Setter
@RequiredArgsConstructor
public class ServerMenu {

    @Getter private static MongoCollection<Document> collection = Hub.get().getMongoDatabase().getCollection("menus");
    @Getter private static Map<Integer, ServerMenu> menus = Maps.newHashMap();

    @NonNull private final int ID;
    private String title = "";
    private List<ServerItem> serverItems = Lists.newArrayList();
    private List<CommonItem> commonItems = Lists.newArrayList();

    public void open(Player player){
        new ServerSelectorMenu(title, serverItems, commonItems).openMenu(player);
    }

    public ServerItem getSeverItemBySlot(int slot){
        return serverItems.stream().filter(serverItem -> serverItem.getSlot() == slot).findFirst().orElse(null);
    }

    public CommonItem getCommonItemBySlot(int slot){
        return commonItems.stream().filter(commonItem -> commonItem.getSlot() == slot).findFirst().orElse(null);
    }

    public static void load(){
        collection.find().forEach((Consumer<? super Document>) document -> {
            ServerMenu serverMenu = new ServerMenu(document.getInteger("id"));


            serverMenu.setTitle(document.getString("title"));

            if(document.containsKey("items")){
                List<Document> documents = document.getList("items", Document.class);

                documents.forEach(item -> {
                    ItemStack itemStack = ItemUtils.deSerialized(item.getString("item"));
                    int subMenu = item.getInteger("submenuid");
                    int slot = item.getInteger("slot");
                    String server = "";
                    if(item.containsKey("server")){
                        server = item.getString("server");
                    }
                    ServerItem serverItem = new ServerItem(itemStack, subMenu, slot, server);
                    serverMenu.getServerItems().add(serverItem);
                });
            }
            if(document.containsKey("commonitems")){
                List<Document> documents = document.getList("commonitems", Document.class);

                documents.forEach(item -> {
                    ItemStack itemStack = ItemUtils.deSerialized(item.getString("item"));
                    int slot = item.getInteger("slot");
                    CommonItem serverItem = new CommonItem(itemStack, slot);
                    serverMenu.getCommonItems().add(serverItem);
                });
            }

            menus.put(serverMenu.getID(), serverMenu);
        });
    }

    public void save(){
        Document document = new Document();

        document.put("id", ID);
        if(!serverItems.isEmpty()){
            document.put("items", serverItems.stream().map(ServerItem::serilize).collect(Collectors.toList()));
        }
        if(!commonItems.isEmpty()){
            document.put("commonitems", commonItems.stream().map(CommonItem::serilize).collect(Collectors.toList()));
        }
        document.put("title", title);

        collection.replaceOne(Filters.eq("id", ID), document, new ReplaceOptions().upsert(true));
    }
}