package github.notmeg4.nightmode.listeners;

import github.notmeg4.nightmode.NightMode;
import github.notmeg4.nightmode.utils.dbMan;
import github.notmeg4.nightmode.utils.timeUpdate;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class playerJoin implements Listener {

    private NightMode plugin;

    public playerJoin(NightMode plugin) {
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
}
