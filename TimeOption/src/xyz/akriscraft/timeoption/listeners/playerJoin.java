package xyz.akriscraft.timeoption.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.akriscraft.timeoption.TimeOption;
import xyz.akriscraft.timeoption.utils.dbMan;
import xyz.akriscraft.timeoption.utils.timeUpdate;


public class playerJoin implements Listener {

    private TimeOption plugin;

    public playerJoin(TimeOption plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onplayerjoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if(!(dbMan.playerExist(plugin.getMySQL(), p.getName()))) {
            dbMan.createPlayer(plugin.getMySQL(), p.getName());
        }
        new timeUpdate(e.getPlayer(), plugin);
    }
    @EventHandler
    public void onWorldChange(PlayerT)

}
