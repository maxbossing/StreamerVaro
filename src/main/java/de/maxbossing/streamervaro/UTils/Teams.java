package de.maxbossing.streamervaro.UTils;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import de.maxbossing.maxapi.Builders.ItemStackBuilder;
import de.maxbossing.maxapi.Builders.SkullBuilder;
import de.maxbossing.maxapi.UTils.ConfigManager;
import de.maxbossing.streamervaro.StreamerVaro;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import java.util.UUID;

public class Teams {

    private static Inventory teaminviteinv = Bukkit.createInventory(null, 27, "§b§6Team request");

    private static BiMap<Inventory, Player> requesters = HashBiMap.create();

    private static ConfigManager teams = StreamerVaro.getTeams();

    private static final String MAIN = StreamerVaro.MAIN;

    public static void sendTeamRequest(Player requester, Player target) {
        UUID requester_u = requester.getUniqueId();
        UUID target_u = target.getUniqueId();

        for (int i = 0; i < teaminviteinv.getSize(); i++) {
            teaminviteinv.setItem(i, new ItemStackBuilder(Material.GRAY_STAINED_GLASS_PANE, "").build());
        }

        //  0  1  2  3  4  5  6  7  8
        //  9  10 11 12 13 14 15 16 17
        //  18 19 20 21 22 23 24 25 26

        // Set requester Skull at index 13
        ItemStack stack = SkullBuilder.createSkullByUUID(requester_u);
        SkullMeta meta = (SkullMeta) stack.getItemMeta();
        meta.setDisplayName(requester.getName());
        stack.setItemMeta(meta);

        teaminviteinv.setItem(13, stack);

        // set accept button at index 10
        teaminviteinv.setItem(10, new ItemStackBuilder(Material.GREEN_WOOL).displayname(ChatColor.BOLD + "" +ChatColor.GREEN + "ANNEHMEN").build());

        // set decline button at index 16
        teaminviteinv.setItem(16, new ItemStackBuilder(Material.RED_WOOL).displayname(ChatColor.BOLD + "" +ChatColor.RED + "ABLEHNEN").build());

        target.openInventory(teaminviteinv);
        addRequester(teaminviteinv, requester);

    }

    public static void addRequester(Inventory requestMenu,Player requester) {
        requesters.put(requestMenu, requester);
    }

    public static Inventory getRequestMenu(Player requester) {
        return requesters.inverse().get(requester);
    }

    public static Player getRequester(Inventory inv) {
        return requesters.get(inv);
    }

    public static void removeRequest(Inventory inventory) {
        requesters.remove(inventory);
    }

    public static boolean sentRequest(Player player) {
        return requesters.containsValue(player);
    }

    // Adds a team to the teams.yml config
    public static void addTeam(Player player1, Player player2) {

        if (teams.exists("players." + player1.getUniqueId().toString())) return;
        if (teams.exists("players." + player2.getUniqueId().toString())) return;

        teams.set("players." + player1.getUniqueId().toString(), player2.getUniqueId().toString(), true);
        teams.set("players." + player2.getUniqueId().toString(), player2.getUniqueId().toString(), true);

        player1.sendMessage(MAIN + ChatColor.GOLD + "Du bist nun in einem Team mit " + ChatColor.LIGHT_PURPLE + player2.getName());
        player2.sendMessage(MAIN + ChatColor.GOLD + "Du bist nun in einem Team mit " + ChatColor.LIGHT_PURPLE + player1.getName());
    }

    //Removes a Team from the teams.yml config
    public static void removeTeam(Player player1, Player player2) {
        if (!teams.exists("players." + player1.getUniqueId().toString()))return;
        if (!teams.exists("players." + player2.getUniqueId().toString()))return;

        teams.set("players." + player1.getUniqueId().toString(), null, true);
        teams.set("players." + player2.getUniqueId().toString(), null, true);

        player1.sendMessage(MAIN + ChatColor.GOLD + "Dein Team mit " + ChatColor.LIGHT_PURPLE + player2.getName() + ChatColor.GOLD + " wurde aufgelöst");
        player2.sendMessage(MAIN + ChatColor.GOLD + "Dein Team mit " + ChatColor.LIGHT_PURPLE + player1.getName() + ChatColor.GOLD + " wurde aufgelöst");

    }

    public static Player getTeamMate(Player player) {
        return Bukkit.getPlayer(UUID.fromString(teams.getString(player.getUniqueId().toString())));
    }

    public static void removeTeammate(Player mate) {
        teams.set("players." + mate.getUniqueId().toString(), null, true);
    }


}
