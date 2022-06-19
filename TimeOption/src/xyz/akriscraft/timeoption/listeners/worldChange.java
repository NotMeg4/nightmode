package xyz.akriscraft.timeoption.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import xyz.akriscraft.timeoption.TimeOption;
import xyz.akriscraft.timeoption.utils.dbMan;
import xyz.akriscraft.timeoption.utils.timeUpdate;


public class worldChange implements Listener {

    private TimeOption plugin;

    public worldChange(TimeOption plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }
    FileConfiguration config;

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        String worlds = "enabled_Worlds":
        List<String> enabledWorlds = config.getStringList(worlds)
        Player p = e.getPlayer();

        if(enabledWorlds.contains(p.getWorld().toString())) {
            new timeUpdate(e.getPlayer(), plugin);
        }
    }
}
