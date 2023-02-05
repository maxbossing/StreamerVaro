package de.maxbossing.streamervaro.Commands;

import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class LeaveTeamCommand implements CommandExecutor {

    private final String MAIN = StreamerVaro.MAIN;
    private final String ERROR = StreamerVaro.ERROR;

    private ConfigManager teams = StreamerVaro.getTeams();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // Check if sender is a Player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ERROR + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return true;
        }

        Player player = (Player) sender;

        // Check if command is right length
        if (args.length != 0) {
            sender.sendMessage(ERROR + ChatColor.GOLD + "Usage : /leaveteam");
            return true;
        }

        //Check if Player is in team
        if (!teams.exists(player.getUniqueId().toString())) {
            sender.sendMessage(ERROR + ChatColor.RED + "Du bist in keinem Team!");
            return true;
        }

        Player teammate = Bukkit.getPlayer(UUID.fromString(teams.getString(player.getUniqueId().toString())));

        Teams.removeTeam(player, teammate);
        return false;
    }
}
