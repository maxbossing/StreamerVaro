package de.maxbossing.streamervaro.UTils;

import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.io.File;

public class UTils {
    public static void log(String msg, ChatColor color) {
        Bukkit.getConsoleSender().sendMessage(color + StreamerVaro.CONSOLE + msg);
    }

    public static void errorlog(String msg) {
        log("ERROR! " + msg, ChatColor.RED);
    }

    public static void shutdown(String cause) {
        errorlog(cause);
        errorlog("Shutting down");
        Bukkit.getPluginManager().disablePlugin(StreamerVaro.getInstance());
    }

    public static void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }
}
