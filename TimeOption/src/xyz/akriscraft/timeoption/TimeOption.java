package xyz.akriscraft.timeoption;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import xyz.akriscraft.timeoption.commands.reloadCMD;
import xyz.akriscraft.timeoption.commands.toggleCMD;
import xyz.akriscraft.timeoption.listeners.placeholderRequest;
import xyz.akriscraft.timeoption.listeners.worldChange;
import xyz.akriscraft.timeoption.utils.dbConnect;
import xyz.akriscraft.timeoption.listeners.playerJoin;


import java.io.File;
import java.sql.Connection;
import java.sql.Time;

public class TimeOption extends JavaPlugin {
    private TimeOption plugin;
    public String configPath;
    private dbConnect dbConnect;

    FileConfiguration configuracion;
    @Override
    public void onEnable() {

        File config = new File(this.getDataFolder(),"config.yml");
        configPath = config.getPath();

        if(!(config.exists())){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
        configuracion = this.getConfig();

        String dbHost = "mysql_db.host";
        String dbPass = "mysql_db.password";
        String dbPort = "mysql_db.port";
        String dbUser = "mysql_db.user";
        String dbName = "mysql_db.database";

        getCommand("nightmode").setExecutor(new toggleCMD(this));
        getCommand("nightmodereload").setExecutor(new reloadCMD(this));
        PluginManager pm= getServer().getPluginManager();
        pm.registerEvents(new playerJoin(this), this);
        pm.registerEvents(new worldChange(this), this);

        new placeholderRequest(this).register();

        System.out.println("-----------------------");
        System.out.println("- NightMode (Enabled) -");
        System.out.println("-----------------------");

        this.dbConnect = new dbConnect(configuracion.getString(dbHost), Integer.parseInt(configuracion.getString(dbPort)),configuracion.getString(dbPass), configuracion.getString(dbUser), configuracion.getString(dbName) );
    }

    public Connection getMySQL() {
        Connection connection = this.dbConnect.getConnection();
        return connection;
    }
}