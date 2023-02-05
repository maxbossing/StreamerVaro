package de.maxbossing.streamervaro.UTils;

import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.Enums.StrikeEventType;
import de.maxbossing.streamervaro.Events.PlayerStrikeChangeEvent;
import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Strikes {
    private static ConfigManager strikes = StreamerVaro.getstrikes();
    private static ConfigManager config = StreamerVaro.getconfig();


    /**
     * Checks if a Player has Strikes
     * @param uuid The uuid of the targeted Player
     * @return true if the Player has strikes, false if the Player has no strikes
     */
    public static boolean hasStrikes(UUID uuid) {
        return strikes.getInt(uuid.toString()) != 0;
    }



    /**
     * Get number of Strikes from Player
     * @param uuid The uuid of the targeted Player
     * @return the amount of Strikes the Player has
     */
    public static int getStrikes(UUID uuid) {
        if (!hasStrikes(uuid)) return 0;
        return strikes.getInt(uuid.toString());
    }


    /**
     * Set the Strikes of a Player to an Arbitrary number
     * @param uuid The uuid of the targeted Player
     * @param newstrikes the amount of strikes the Player strikes will be set to
     * @return true if successfull, false if not successfull
     */
    public static boolean setStrikes(UUID uuid, int newstrikes) {
        try {
            strikes.set(uuid.toString(), newstrikes, true);
        }catch (Exception e) {
            return false;
        }

        PlayerStrikeChangeEvent event = new PlayerStrikeChangeEvent(Bukkit.getPlayer(uuid), StrikeEventType.SET_STRIKE, getStrikes(uuid));
        Bukkit.getPluginManager().callEvent(event);

        return true;
    }



    /**
     * Increments the targeted Players strikes by the specified amount
     * @param uuid The uuid of the targeted Player
     * @param increment The amount of strikes the Players should be incremented by
     * @return true if successfull, false if not successfull
     */
    public static boolean incrementStrikes(UUID uuid, int increment) {
        try {
            strikes.set(uuid.toString(), strikes.getInt(uuid.toString()) + increment, true);
        }catch (Exception e) {
            return false;
        }

        PlayerStrikeChangeEvent event = new PlayerStrikeChangeEvent(Bukkit.getPlayer(uuid), StrikeEventType.ADD_STRIKE, getStrikes(uuid));
        Bukkit.getPluginManager().callEvent(event);

        return true;
    }


    /**
     * Decrements the targeted Players strikes by thr specified amount
     * @param uuid The uuid of the targeted Player
     * @param decrement The amount of strikes the Players should be decremented by
     * @return true if successfull, false if not successfull
     */
    public static boolean decrementStrikes(UUID uuid, int decrement) {
        try {
            strikes.set(uuid.toString(), strikes.getInt(uuid.toString()) - decrement, true);
        }catch (Exception e) {
            return false;
        }

        PlayerStrikeChangeEvent event = new PlayerStrikeChangeEvent(Bukkit.getPlayer(uuid), StrikeEventType.REMOVE_STRIKE, getStrikes(uuid));
        Bukkit.getPluginManager().callEvent(event);

        return true;
    }



    /**
     * Returns the maximum allowed Strikes
     * @return The Maximum allowed Strikes
     */
    public static int getMaxStrikes() {
        return config.getInt("maxstrikes");
    }


    /**
     * Sets the Maximum allowed Strikes to the specified amount
     * @param maxStrikes The new Maximum allowed Strikes
     * @return true if successfull, false if not successfull
     */
    public static boolean setMaxStrikes(int maxStrikes) {
        try {
            config.set("maxstrikes", maxStrikes, true);
        }catch (Exception e) {
            return false;
        }
        return true;
    }
}
