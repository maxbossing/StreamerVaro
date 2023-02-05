package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class TeamAcceptListener implements Listener {

    private final String MAIN = StreamerVaro.MAIN;

    @EventHandler
    public void onAccept(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        //Check if inventory is request menu
        if (Teams.getRequester(event.getInventory())== null)return;

        // Get Player who requested
        Player requester = Teams.getRequester(event.getInventory());

        Inventory inv = event.getInventory();

        event.setCancelled(true);

        if (event.getSlot() == 10) {
            Teams.addTeam(player, requester);
        } else if (event.getSlot() == 16){
            requester.sendMessage(MAIN + ChatColor.LIGHT_PURPLE + player.getName() + ChatColor.GOLD + " hat deine Anfrage Abgelehnt!");
        }

        Teams.removeRequest(inv);

        player.closeInventory();


    }
}
