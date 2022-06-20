package github.notmeg4.nightmode.listeners;

import github.notmeg4.nightmode.utils.timeUpdate;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import github.notmeg4.nightmode.NightMode;

import java.util.List;


public class worldChange implements Listener {

    private NightMode plugin;

    public worldChange(NightMode plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    FileConfiguration config;

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        String perWorld = "worlds.perWorld";
        if(config.getBoolean(perWorld)) {
            String worlds = "worlds.enabledWorlds";
            List<String> enabledWorlds = config.getStringList(worlds);
            enabledWorlds.replaceAll(String::toLowerCase);
            if(enabledWorlds.contains(p.getWorld().getName().toLowerCase())) {
                new timeUpdate(e.getPlayer(), plugin);
            } else {
                p.resetPlayerTime();
            }
        } else {
            new timeUpdate(e.getPlayer(), plugin);
        }
    }
}
