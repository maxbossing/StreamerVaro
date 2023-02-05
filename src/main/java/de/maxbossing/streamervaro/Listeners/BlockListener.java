package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission("streamervaro.bypass.runninggame"))return;
        if (!StreamerVaro.isRunning())event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission("streamervaro.bypass.runninggame"))return;
        if (!StreamerVaro.isRunning())event.setCancelled(true);

        //TODO: Add Strike Check for Portal obstruction when Builders are done

    }

}
