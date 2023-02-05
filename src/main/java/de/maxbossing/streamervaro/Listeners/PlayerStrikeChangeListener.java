package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.Enums.StrikeEventType;
import de.maxbossing.streamervaro.Events.PlayerStrikeChangeEvent;
import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Strikes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerStrikeChangeListener implements Listener {

    @EventHandler
    public void onPlayerStrikeChangeEvent(PlayerStrikeChangeEvent event) {

        //Bans Player if maximum of Strikes is achieved

        if (event.getEventType().equals(StrikeEventType.REMOVE_STRIKE))return;
        if (event.getStrikes() >= Strikes.getMaxStrikes()) {
            event.getPlayer().setHealth(0);
            event.getPlayer().banPlayer("Du hast die Grenze von " + Strikes.getMaxStrikes() + " Strikes Überschritten und wirst deswegen ausgeschlossen");
            Bukkit.getOnlinePlayers().forEach(player -> {

                player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 1);

                player.sendMessage(StreamerVaro.MAIN + ChatColor.BOLD + ChatColor.DARK_PURPLE + event.getPlayer() + ChatColor.RED + " Ist ausgeschieden!");

            });
        }
    }
}
