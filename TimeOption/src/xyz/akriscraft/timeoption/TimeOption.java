package xyz.akriscraft.timeoption;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.akriscraft.timeoption.commands.changetime;
import xyz.akriscraft.timeoption.listeners.joinlistener;

public class TimeOption extends JavaPlugin {

    private static TimeOption plugin;

    @Override
    public void onEnable() {
        getCommand("changetime").setExecutor(new changetime());
        Bukkit.getPluginManager().registerEvents(new joinlistener(), this);

        System.out.println("-----------------------");
        System.out.println("- TimeChanger enabled -");
        System.out.println("-----------------------");
    }

    public static TimeOption getPlugin() {
        return plugin;
    }
}