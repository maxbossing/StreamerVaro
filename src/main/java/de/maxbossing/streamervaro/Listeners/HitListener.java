package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Teams;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.MainHand;

public class HitListener implements Listener {

    //Cancel hit if Players are on the same team
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player && event.getDamager() instanceof Player))return;
        if (event.getEntity().hasPermission("streamervaro.bypass.friendlyfire"))return;
        if (Teams.getTeamMate((Player) event.getDamager()).equals(event.getEntity())){

            event.setCancelled(true);
        }
    }

    // Cancel Damage if Game is not running
    @EventHandler
    public void onHit(EntityDamageEvent event) {
        if (event.getEntity().hasPermission("streamervaro.bypass.runninggame"))return;
        if (!StreamerVaro.isRunning()) event.setCancelled(true);
    }
}
