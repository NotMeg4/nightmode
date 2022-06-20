package github.notmeg4.nightmode.listeners;
import github.notmeg4.nightmode.NightMode;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import github.notmeg4.nightmode.utils.dbMan;

public class placeholderRequest extends PlaceholderExpansion {
    private NightMode plugin;

    @Override
    public String getIdentifier() {
        return "nightmode";
    }

    @Override
    public String getAuthor() {
        return "NotMeg4_";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }
    public placeholderRequest(NightMode plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onPlaceholderRequest(Player p, String params) {
        if(p == null) {
            return "nullPlayer";
        }
        if (params.equalsIgnoreCase("isenabled")){
            Boolean state = dbMan.getNightMode(plugin.getMySQL(), p.getName());
            if(state) {
                return "true";
            }
            return "false";
        }
        return "null";
    }
}
