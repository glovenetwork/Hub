package net.glove.hub;

import net.glove.global.utils.command.CommandProvider;
import net.glove.hub.commands.TestCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
    }


    private void registerCommands(){
        CommandProvider provider = new CommandProvider(this, "hub");
        provider.registerCommand(new TestCommand());
    }

    public static Hub get(){
        return getPlugin(Hub.class);
    }
}