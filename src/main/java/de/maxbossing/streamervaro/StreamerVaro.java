package de.maxbossing.streamervaro;

import de.maxbossing.maxapi.Constants.Prefixes;
import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.Commands.*;
import de.maxbossing.streamervaro.Listeners.*;
import de.maxbossing.streamervaro.UTils.UTils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class StreamerVaro extends JavaPlugin {

    /*Prefixes*/
    public static final String MAIN = Prefixes.MAIN("StreamerVaro");
    public static final String ERROR = Prefixes.ERROR("StreamerVaro");
    public static final String WARNING = Prefixes.WARNING("StreamerVaro");
    public static final String DEBUG = Prefixes.DEBUG("StreamerVaro");
    public static final String CONSOLE = "[StreamerVaro] ";

    /*Main Instance*/
    private static StreamerVaro instance;

    /*Config Manager Instances*/
    private static ConfigManager c_strikes;
    private static ConfigManager c_config;
    private static ConfigManager c_teams;

    /*Other*/
    private static boolean isRunning;

    /*
     * =========
     * Overrides
     * =========
     */

    /*Plugin Startup Logic*/
    @Override
    public void onEnable() {
        // Initialize instance
        instance = this;

        // Initialize Configs
        config();

        // Initialize Listeners
        listeners();

        // Initialize Commands

        commands();

        //set running bool
        isRunning = c_config.getBoolean("running");

        // Startup Message
        UTils.log("""
                          ___  _  _ 
                         / __)( \\/ )
                         \\__ \\ \\  /  StreamerVaro v0.4.5
                         (___/  \\\\/  by Max
                          """, ChatColor.RESET);

        UTils.log("Plugin Loaded", ChatColor.LIGHT_PURPLE);
    }

    /*Plugin Shutdown Logic*/
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*
     * ===========
     * Config Init
     * ===========
     */
    void config() {

        /*
         * ################
         * Initialize files
         * ################
         */

        UTils.log("Initializing Configuration", ChatColor.YELLOW);

        /*
         * Check if datafolder exists
         * If not, create it
         */
        if (!this.getDataFolder().exists()) {
            try {
                UTils.log("Attempting to create Datafolder...", ChatColor.GOLD);
                if (!this.getDataFolder().mkdir()) {
                    UTils.shutdown("Can't create Datafolder");
                }
                UTils.log("Datafolder created", ChatColor.GREEN);

            }catch (Exception e) {
                UTils.shutdown("An Exception ocurred while creating the Datafolder");
            }
        }else {
            UTils.log("Datafolder exists, skipping", ChatColor.GOLD);
        }

        /*
         * Create config.yml
         */
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            try {
                UTils.log("Attempting to create config.yml...", ChatColor.GOLD);
                if (!config.createNewFile()) {
                    UTils.shutdown("Can't create config.yml");
                }
                UTils.log("config.yml created", ChatColor.GREEN);
            } catch (Exception e) {
                UTils.shutdown("An Exception ocurred while creating config.yml");
            }
        }else {
            UTils.log("config.yml exists, skipping", ChatColor.GOLD);
        }

        /*
         * Create Strikes.yml
         */
        File strikes = new File(this.getDataFolder(), "strikes.yml");
        if (!strikes.exists()) {
            try {
                UTils.log("Attempting to create strikes.yml...", ChatColor.GOLD);
                if (!strikes.createNewFile()) {
                    UTils.shutdown("Can't create strikes.yml");
                }
                UTils.log("strikes.yml created", ChatColor.GREEN);
            } catch (Exception e) {
                UTils.shutdown("An Exception ocurred while creating strikes.yml");
            }
        }else {
            UTils.log("strikes.yml exists, skipping", ChatColor.GOLD);
        }

        /*
         * Create teams.yml
         */
        File teams = new File(this.getDataFolder(), "teams.yml");
        if (!teams.exists()) {
            try {
                UTils.log("Attempting to create teams.yml...", ChatColor.GOLD);
                if (!teams.createNewFile()) {
                    UTils.shutdown("Can't create teams.yml");
                }
                UTils.log("teams.yml created", ChatColor.GREEN);
            } catch (Exception e) {
                UTils.shutdown("An Exception ocurred while creating teams.yml");
            }
        }else {
            UTils.log("teams.yml exists, skipping", ChatColor.GOLD);
        }


        UTils.log("Finished Initializing Configuration", ChatColor.GREEN);


        /*
         * ########################
         * Initialize Configmanager
         * ########################
         */
        c_config = new ConfigManager(this, "config.yml");
        c_strikes = new ConfigManager(this, "strikes.yml");
        c_teams = new ConfigManager(this, "timeout.yml");

        /*
         * ###############
         * Config Defaults
         * ###############
         */

        UTils.log("Initializing config nodes", ChatColor.YELLOW);

        /*
         * Defaults for config.yml
         */
        UTils.log("Initializing config.yml...", ChatColor.GOLD);
        try {
            if (!c_config.exists("maxstrikes")) {
                c_config.set("maxstrikes", 3, true);
            }
            if (!c_config.exists("maxplaytime")) {
                c_config.set("maxplaytime", 15, true);
            }
            if (!c_config.exists("running")) {
                c_config.set("running", false, true);
            }
        }catch (Exception e) {
            UTils.shutdown("an Exeption occurred while initializing config nodes for config.yml");
        }
        UTils.log("config.yml initialized", ChatColor.GREEN);


        UTils.log("Finished initializing config nodes", ChatColor.GREEN);
    }

    /*
    * =============
    * Listener Init
    * =============
    */
    void listeners() {
        UTils.log("Initializing Listeners...", ChatColor.YELLOW);

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerStrikeChangeListener(), this);
        pm.registerEvents(new PlayerPortalCreateListener(), this);
        pm.registerEvents(new TeamAcceptListener(), this);
        pm.registerEvents(new BlockListener(), this);
        pm.registerEvents(new HitListener(), this);
        pm.registerEvents(new DeathListener(), this);

        UTils.log("Finished Initializing Listeners", ChatColor.GREEN);
    }

    /*
     * =============
     * Commands Init
     * =============
     */

    void commands() {
        UTils.log("Initializing Commands...", ChatColor.YELLOW);

        getCommand("strikes").setExecutor(new StrikesCommand());
        getCommand("addstrikes").setExecutor(new AddStrikesCommand());
        getCommand("removestrikes").setExecutor(new RemoveStrikesCommand());
        getCommand("team").setExecutor(new TeamCommand());
        getCommand("leaveteam").setExecutor(new LeaveTeamCommand());
        getCommand("startgame").setExecutor(new StartGameCommand());
        getCommand("stopgame").setExecutor(new StopGameCommand());

        UTils.log("Finished Initializing Commands", ChatColor.GREEN);
    }


    /*
     * =======
     * GETTERS
     * =======
     */

    public static StreamerVaro getInstance() {
        return instance;
    }

    public static ConfigManager getstrikes() {
        return c_strikes;
    }

    public static ConfigManager getconfig() {
        return c_config;
    }

    public static ConfigManager getTeams() {
        return c_teams;
    }

    public static boolean isRunning() {
        return isRunning;
    }
    public static void setRunning(boolean running) {
        c_config.set("running", running, true);
        isRunning = running;
    }

}
