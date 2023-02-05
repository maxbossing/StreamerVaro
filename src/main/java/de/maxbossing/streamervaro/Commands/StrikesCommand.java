package de.maxbossing.streamervaro.Commands;

import de.maxbossing.streamervaro.StreamerVaro;
import de.maxbossing.streamervaro.UTils.Strikes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StrikesCommand implements CommandExecutor {

    /*
     * This Command Displays the amount of Strikes the Player executing it has and the maximum allowed Strikes for the Game
     */

    private final String MAIN = StreamerVaro.MAIN;
    private final String ERROR = StreamerVaro.ERROR;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // Check if sender is a Player
        if (!(sender instanceof Player)){
            sender.sendMessage(ERROR + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return true;
        }

        Player player = (Player) sender;

        // Check if command is right length
        if (args.length != 0 && args.length != 1) {
            player.sendMessage(ERROR + ChatColor.GOLD + "Usage : /strikes (player)");
            return true;
        }

        // Get own Strikes and Maximum allowed strikes
        if (args.length == 0) {
            sender.sendMessage(MAIN + ChatColor.GOLD + "Du hast momentan " + ChatColor.DARK_PURPLE + Strikes.getStrikes(player.getUniqueId()) + ChatColor.GOLD + " Strikes");
            sender.sendMessage(MAIN + ChatColor.GOLD + "Dieses Spiel erlaubt bis zu " + ChatColor.DARK_PURPLE + Strikes.getMaxStrikes() + ChatColor.GOLD + " Strikes");
        }
        // Get Strikes of another Player
        else if  (args.length == 1) {

            // Check if Player has needed Permissions
            if (!player.hasPermission("streamervaro.command.strikes.other")) {
                sender.sendMessage(ERROR + ChatColor.RED + "Du hast keine Berechigung diesen Command auszuführen!");
                return true;
            }
            // Check if Player is online
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ERROR + ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " Ist nicht online!");
                return true;
            }else {
                sender.sendMessage(MAIN + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.GOLD + " hat momentan " + ChatColor.DARK_PURPLE + Strikes.getStrikes(target.getUniqueId()) + ChatColor.GOLD + " Strikes");
            }
        }
        return false;
    }
}
