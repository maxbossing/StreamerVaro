package de.maxbossing.streamervaro.Commands;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Teams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeamCommand implements CommandExecutor {

    /*
     * This Command allows players to send requests to other players to team up
     */


    private final String MAIN = StreamerVaro.MAIN;
    private final String ERROR = StreamerVaro.ERROR;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if sender is a Player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ERROR + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return true;
        }

        // Check if command is right lenght
        if (args.length != 1) {
            sender.sendMessage(ERROR + ChatColor.GOLD + "Usage : /team <Player>");
            return true;
        }

        // Check if Player is online
        Player player = (Player) sender;
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(ERROR + ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " ist nicht online!");
            return true;
        } else {
            Teams.sendTeamRequest(player, target);
            player.sendMessage(MAIN + ChatColor.LIGHT_PURPLE + args[0] + ChatColor.GOLD + " wurde eine Team Request gesendet");
        }

        return false;
    }
}
