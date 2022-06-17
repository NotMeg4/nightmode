package xyz.akriscraft.timeoption;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.akriscraft.timeoption.commands.changetime;
import xyz.akriscraft.timeoption.utils.dbConnect;
import xyz.akriscraft.timeoption.listeners.playerJoin;

import java.sql.Connection;

public class TimeOption extends JavaPlugin {

    private static TimeOption plugin;
    private dbConnect dbManager;

    @Override
    public void onEnable() {
        getCommand("changetime").setExecutor(new changetime());
        Bukkit.getPluginManager().registerEvents(new playerJoin(plugin), this);

        System.out.println("-----------------------");
        System.out.println("- TimeChanger enabled -");
        System.out.println("-----------------------");

        this.dbManager = new dbConnect("localhost",3306,"7OZu2MyS5y5TTd4r", "mega", "akris_plugintest");
    }

    public static TimeOption getPlugin() {
        return plugin;
    }

    public Connection getMySQL() {
        Connection connection = this.dbConnect.getConnection();
        return connection;
    }
}