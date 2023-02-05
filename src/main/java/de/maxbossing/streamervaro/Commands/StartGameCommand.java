package de.maxbossing.streamervaro.Commands;

import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class StartGameCommand implements CommandExecutor {

    private final String MAIN = StreamerVaro.MAIN;
    private final String ERROR = StreamerVaro.ERROR;

    private ConfigManager config = StreamerVaro.getconfig();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Check if sender is a Player
        if (!(sender instanceof Player)) {
            sender.sendMessage(ERROR + ChatColor.RED + "Du musst ein Spieler sein um diesen Command auszuführen!");
            return true;
        }

        // Check if command is right lenght
        if (args.length != 0) {
            sender.sendMessage(ERROR + ChatColor.GOLD + "Usage : /startgame");
            return true;
        }

        if (StreamerVaro.isRunning()) {
            sender.sendMessage(ERROR + ChatColor.RED + "Das Spiel läuft bereits!");
            return true;
        }

        new BukkitRunnable() {
            int i = config.getInt("startdelay");
            @Override
            public void run() {
                if (i == 0)this.cancel();
                Bukkit.getOnlinePlayers().forEach(p -> {
                    p.sendMessage(MAIN + ChatColor.GOLD + "Das Spiel beginnt in " + ChatColor.LIGHT_PURPLE + i + ChatColor.GOLD + " Sekunden!");
                    if (i <=3) {
                        p.sendTitle(ChatColor.RED + String.valueOf(i), "");
                        p.sendMessage(MAIN + ChatColor.BOLD + ChatColor.GOLD + "Das Spiel beginnt in " + ChatColor.RED + i + ChatColor.GOLD + " Sekunden!");

                    }else {
                        p.sendTitle(ChatColor.LIGHT_PURPLE + String.valueOf(i), "");
                        p.sendMessage(MAIN + ChatColor.BOLD + ChatColor.GOLD + "Das Spiel beginnt in " + ChatColor.LIGHT_PURPLE + i + ChatColor.GOLD + " Sekunden!");
                    }
                });
            }
        }.runTaskTimer(StreamerVaro.getInstance(), 0, 20);


        Bukkit.getOnlinePlayers().forEach(p -> {
            p.sendMessage(MAIN + ChatColor.BOLD + ChatColor.GOLD + "DAS SPIEL HAT BEGONNEN!!");
        });
        StreamerVaro.setRunning(true);

        return false;
    }
}
