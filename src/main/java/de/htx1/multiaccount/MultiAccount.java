package de.htx1.multiaccount;

import de.htx1.multiaccount.listeners.PlayerJoinListener;
import de.htx1.multiaccount.utils.UUIDFetcher;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created: 16.02.2023 00:18
 *
 * @author thvf
 */
public class MultiAccount extends JavaPlugin {

    @Getter
    private static final UUIDFetcher uuidFetcher = new UUIDFetcher();

    @Override
    public void onEnable()  {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
