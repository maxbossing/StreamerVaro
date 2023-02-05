package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener  {
    ConfigManager strikes = StreamerVaro.getstrikes();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        //Check if player is in Strikes file
        if (!strikes.exists(event.getPlayer().getUniqueId().toString())) {
            strikes.set(event.getPlayer().getUniqueId().toString(), 0, true);
        }
    }
}
