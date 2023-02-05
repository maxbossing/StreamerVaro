package de.maxbossing.streamervaro.Listeners;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Strikes;
import de.maxbossing.streamervaro.UTils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.util.UUID;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getPlayer().hasPermission("streamervaro.bypass.death"))return;
        Teams.removeTeammate(event.getPlayer());
        event.getPlayer().banPlayer("Du bist gestorben und wirst deswegen ausgeschlossen");
        Bukkit.getOnlinePlayers().forEach(player -> {

            player.playSound(player, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10, 1);

            player.sendMessage(StreamerVaro.MAIN + ChatColor.BOLD + ChatColor.DARK_PURPLE + event.getPlayer() + ChatColor.RED + " Ist Gestorben!");

        });


        File file = new File(StreamerVaro.getInstance().getDataFolder(), "teams.yml");
        YamlConfiguration teams = YamlConfiguration.loadConfiguration(file);


        if (teams.getConfigurationSection("players").getKeys(false).size() > 2)return;


        if (teams.getConfigurationSection("players").getKeys(false).size() == 2)  {
                Player player1 = Bukkit.getPlayer(UUID.fromString((String) teams.getConfigurationSection("players").getKeys(false).toArray()[0]));
                Player possibleMate = Bukkit.getPlayer(UUID.fromString(teams.getString((String) teams.getConfigurationSection("players").getKeys(false).toArray()[1])));
                if (Teams.getTeamMate(possibleMate).equals(player1)) {
                    Bukkit.getOnlinePlayers().forEach(p -> {
                        p.sendMessage( StreamerVaro.MAIN + ChatColor.GOLD + ChatColor.BOLD + "DAS SPIEL IST VORBEI!!");
                    });
                    StreamerVaro.setRunning(false);
                }
        }else {
            Bukkit.getOnlinePlayers().forEach(p -> {
                p.sendMessage( StreamerVaro.MAIN + ChatColor.GOLD + ChatColor.BOLD + "DAS SPIEL IST VORBEI!!");
            });
            StreamerVaro.setRunning(false);
        }
    }
}
