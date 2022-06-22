package github.notmeg4.nightmode;

import github.notmeg4.nightmode.commands.reloadCMD;
import github.notmeg4.nightmode.commands.toggleCMD;
import github.notmeg4.nightmode.listeners.placeholderRequest;
import github.notmeg4.nightmode.listeners.playerJoin;
import github.notmeg4.nightmode.listeners.worldChange;
import github.notmeg4.nightmode.utils.dbConnect;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NightMode extends JavaPlugin {
    private github.notmeg4.nightmode.NightMode plugin;
    public String configPath;

    private dbConnect dbConnect;

    FileConfiguration configuracion;

    double pluginVersion = 1.0;

    public void onEnable() {
        File config = new File(getDataFolder(), "config.yml");
        this.configPath = config.getPath();
        this.configuracion = getConfig();
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        } else {
            String cfgvp = "config_Version";
            if(!(configuracion.getInt(cfgvp) == pluginVersion)) {
                configuracion.set(cfgvp, pluginVersion);
            }
            getConfig().options().copyDefaults(true);
            saveConfig();
        }

        getCommand("nightmode").setExecutor((CommandExecutor)new toggleCMD(this));
        getCommand("nightmodereload").setExecutor((CommandExecutor)new reloadCMD(this));
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents((Listener)new playerJoin(this), (Plugin)this);
        pm.registerEvents((Listener)new worldChange(this), (Plugin)this);
        (new placeholderRequest(this)).register();
        System.out.println("-----------------------");
        System.out.println("- NightMode (Enabled) -");
        System.out.println("-----------------------");

        String mysqlEnabled = "mysql_db.enabled";

        if(configuracion.getBoolean(mysqlEnabled)) {
            String dbHost = "mysql_db.host";
            String dbPass = "mysql_db.password";
            String dbPort = "mysql_db.port";
            String dbUser = "mysql_db.user";
            String dbName = "mysql_db.database";
            this.dbConnect = new dbConnect(this.configuracion.getString(dbHost), Integer.parseInt(this.configuracion.getString(dbPort)), this.configuracion.getString(dbPass), this.configuracion.getString(dbUser), this.configuracion.getString(dbName));
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cMySQL disabled! &9Using config.yml."));
            if(!(configuracion.contains("players"))) {
                List<String> nmplayers = new ArrayList<String>();
                configuracion.set("players", nmplayers);
                saveConfig();
            }
        }
    }

    public Connection getMySQL() {
        Connection connection = this.dbConnect.getConnection();
        return connection;
    }
}
