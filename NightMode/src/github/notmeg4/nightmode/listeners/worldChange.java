package github.notmeg4.nightmode.listeners;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.timeUpdate;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class worldChange implements Listener {
    private NightMode plugin;

    FileConfiguration config;

    public worldChange(NightMode plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String perWorld = "worlds.perWorld";
        if (this.config.getBoolean(perWorld)) {
            String worlds = "worlds.enabledWorlds";
            List<String> enabledWorlds = this.config.getStringList(worlds);
            enabledWorlds.replaceAll(String::toLowerCase);
            if (enabledWorlds.contains(p.getWorld().getName().toLowerCase())) {
                new timeUpdate(e.getPlayer(), this.plugin);
            } else {
                p.resetPlayerTime();
            }
        } else {
            new timeUpdate(e.getPlayer(), this.plugin);
        }
    }
}
