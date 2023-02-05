package de.maxbossing.streamervaro.Commands;

import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StopGameCommand implements CommandExecutor {

    private final String ERROR = StreamerVaro.ERROR;
    private final String MAIN = StreamerVaro.MAIN;


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if sender is a Player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ERROR + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return true;
        }

        // Check if command is right lenght
        if (args.length != 0) {
            sender.sendMessage(ERROR + ChatColor.GOLD + "Usage : /stopgame");
            return true;
        }

        if (StreamerVaro.isRunning()) {
            sender.sendMessage(ERROR + ChatColor.RED + "Das Spiel läuft nicht!");
            return true;
        }

        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(MAIN + ChatColor.BOLD + ChatColor.GOLD + "Das Spiel wurde Gestoppt!");
        });
        StreamerVaro.setRunning(false);

        return false;
    }
}
