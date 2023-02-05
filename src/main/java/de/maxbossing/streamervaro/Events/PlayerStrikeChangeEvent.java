package de.maxbossing.streamervaro.Events;

import com.google.common.util.concurrent.Service;
import de.maxbossing.streamervaro.Enums.StrikeEventType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerStrikeChangeEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final StrikeEventType eventType;
    private final int strikes;



    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }


    public PlayerStrikeChangeEvent(Player player, StrikeEventType eventType, int strikes) {
        this.player = player;
        this.eventType = eventType;
        this.strikes = strikes;
    }

    public Player getPlayer() {
        return this.player;
    }

    public StrikeEventType getEventType() {
        return this.eventType;
    }

    public int getStrikes(){
        return this.strikes;
    }
}
