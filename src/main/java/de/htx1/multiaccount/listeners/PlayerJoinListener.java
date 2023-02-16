package de.htx1.multiaccount.listeners;

import de.htx1.multiaccount.MultiAccount;
import de.htx1.multiaccount.handlers.AccountHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created: 16.02.2023 00:24
 *
 * @author thvf
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final AccountHandler accountHandler = new AccountHandler(player.getAddress().getHostString().replaceAll("\\.", ":"));

        if(accountHandler.getAccountsByIP().isEmpty() || accountHandler.getAccountsByIP() == null) accountHandler.manipulateList(player.getUniqueId(), true);
        if(!accountHandler.getAccountsByIP().contains(player.getUniqueId().toString())) accountHandler.manipulateList(player.getUniqueId(), true);

        CompletableFuture.supplyAsync(() -> {
            accountHandler.getAccountsByIP().forEach(all -> {
                if(!Objects.equals(all, player.getUniqueId().toString())) {
                    Bukkit.broadcastMessage("§7Der Spieler §e" + player.getName() + " §7ist der §e2. Account §7von §e" + MultiAccount.getUuidFetcher().getName(UUID.fromString(all)));
                }
            });
            return this;
        });
    }
}
