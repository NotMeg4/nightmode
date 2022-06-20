package xyz.akriscraft.timeoption.listeners;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import xyz.akriscraft.timeoption.TimeOption;
import org.jetbrains.annotations.NotNull;
import xyz.akriscraft.timeoption.utils.dbMan;

public class placeholderRequest extends PlaceholderExpansion {
    private TimeOption plugin;

    @Override
    public @NotNull String getIdentifier() {
        return "nightmode";
    }

    @Override
    public @NotNull String getAuthor() {
        return "NotMeg4_";
    }

    @Override
    public @NotNull String getVersion() {
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
    public placeholderRequest(TimeOption plugin) {
        this.plugin = plugin;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player p, @NotNull String params) {
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
        return null;
    }
}
