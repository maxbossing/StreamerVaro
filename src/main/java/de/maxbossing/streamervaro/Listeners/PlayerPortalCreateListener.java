package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Strikes;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCreatePortalEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class PlayerPortalCreateListener implements Listener {

    /*
     * This listener checks for potential breaks of rule 3: no nether portals other than the one at spawn
     */


    private final String MAIN = StreamerVaro.MAIN;

    @EventHandler
    public void onCreate(PortalCreateEvent event) {

        // Check if portal created is a Nether Portal
        if (!event.getReason().equals(PortalCreateEvent.CreateReason.FIRE))return;

        // Check if a Player activated the Portal
        if (!(event.getEntity() instanceof Player))return;

        Player player = (Player) event.getEntity();

        // Chack if Player has Bypass Permission
        if (player.hasPermission("streamervaro.bypass.portal"))return;


        // cancel event and give player one Strike
        event.setCancelled(true);
        Strikes.incrementStrikes(player.getUniqueId(), 1);
        player.sendMessage(MAIN + ChatColor.RED + " Du hast gegen Regel 3 verstoßen und hast deswegen einen Strike erhalten!");
        player.sendMessage(MAIN + ChatColor.GOLD + "Du hast momentan " + ChatColor.DARK_PURPLE + Strikes.getStrikes(player.getUniqueId()) + ChatColor.GOLD + " Strikes");
    }
}
