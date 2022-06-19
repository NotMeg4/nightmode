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
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {

        Player p = e.getPlayer();

        if(p.getWorld().toString() == "lobby") {
            new timeUpdate(e.getPlayer(), plugin);
        }
    }
}
