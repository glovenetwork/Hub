package net.glove.hub.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListeners implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
    }

}