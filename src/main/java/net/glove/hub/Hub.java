package net.glove.hub;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import lombok.Setter;
import net.glove.global.utils.command.CommandProvider;
import net.glove.hub.commands.TestCommand;
import net.glove.hub.selector.editor.ServerMenu;
import net.glove.hub.selector.editor.commands.SelectorMenuEditorCommand;
import net.glove.hub.selector.editor.listeners.EditorListeners;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter @Setter
public class Hub extends JavaPlugin {

    private MongoDatabase mongoDatabase;

    @Override
    public void onEnable() {
        loadMongo();
        registerCommands();
        registerListeners();
        ServerMenu.load();
    }

    private void registerCommands(){
        CommandProvider provider = new CommandProvider(this, "hub");
        provider.registerCommand(new TestCommand());
        provider.registerCommand(new SelectorMenuEditorCommand());
    }

    private void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new EditorListeners(), this);
    }

    private void loadMongo(){
        Configuration config = getConfig();
        if (config.getBoolean("MONGO.AUTHENTICATION.ENABLED")) {
            ServerAddress serverAddress = new ServerAddress(config.getString("MONGO.HOST"),
                config.getInt("MONGO.PORT"));

            MongoCredential credential = MongoCredential.createCredential(
                config.getString("MONGO.AUTHENTICATION.USERNAME"), config.getString("MONGO.AUTHENTICATION.DATABASE"),
                config.getString("MONGO.AUTHENTICATION.PASSWORD").toCharArray());

            MongoClient mongoClient = new MongoClient(serverAddress, credential, MongoClientOptions.builder().build());
            mongoDatabase = mongoClient.getDatabase(config.getString("MONGO.DATABASE"));
        } else {
            mongoDatabase = new MongoClient(config.getString("MONGO.HOST"),
                config.getInt("MONGO.PORT")).getDatabase(config.getString("MONGO.DATABASE"));
        }
    }

    public static Hub get(){
        return getPlugin(Hub.class);
    }
}