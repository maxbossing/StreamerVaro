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

public class RemoveStrikesCommand implements CommandExecutor {

    /*
     *This Command allows Operators to remove Strikes from the Player
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

        Player player = (Player) sender;

        // Check if command is right length
        if (args.length != 2) {
            player.sendMessage(ERROR + ChatColor.GOLD + "Usage : /removestrikes <Player> <amount>");
            return true;
        }


        // Check if Player is online
        Player target = Bukkit.getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(ERROR + ChatColor.LIGHT_PURPLE + args[0] + ChatColor.RED + " Ist nicht online!");
            return true;
        } else {
            // Try to parse args[1] to an int
            try {
                int addStrikes = Integer.parseInt(args[1]);
                // Increment Strikes of Player
                Strikes.decrementStrikes(target.getUniqueId(), addStrikes);
                sender.sendMessage(MAIN + ChatColor.GOLD + "Die Strikes von " + ChatColor.LIGHT_PURPLE + target.getName() + ChatColor.GOLD + " wurden auf " + ChatColor.DARK_PURPLE +  Strikes.getStrikes(target.getUniqueId()) + ChatColor.GOLD + " gesetzt");
            } catch (NumberFormatException exception) {
                sender.sendMessage(ERROR + ChatColor.RED + "Dein zweites Argument muss eine Zahl sein!");
                return true;
            }
        }
        return false;
    }
}
