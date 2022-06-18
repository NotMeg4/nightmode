package xyz.akriscraft.timeoption;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.akriscraft.timeoption.commands.reloadCMD;
import xyz.akriscraft.timeoption.commands.toggleCMD;
import xyz.akriscraft.timeoption.utils.dbConnect;
import xyz.akriscraft.timeoption.listeners.playerJoin;


import java.io.File;
import java.sql.Connection;

public class TimeOption extends JavaPlugin {
    public String configPath;
    private dbConnect dbConnect;
    @Override
    public void onEnable() {
        File config = new File(this.getDataFolder(),"config.yml");

        configPath = config.getPath();
        if(!(config.exists())){
            this.getConfig().options().copyDefaults(true);
            saveConfig();
        }
        getCommand("nightmode").setExecutor(new toggleCMD(this));
        getCommand("nightmodereload").setExecutor(new reloadCMD(this));
        PluginManager pm= getServer().getPluginManager();
        pm.registerEvents(new playerJoin(this), this);


        System.out.println("-----------------------");
        System.out.println("-      NightMode      -");
        System.out.println("-----------------------");

        this.dbConnect = new dbConnect("localhost",3306,"7OZu2MyS5y5TTd4r", "mega", "akris_plugintest");
    }

    public Connection getMySQL() {
        Connection connection = this.dbConnect.getConnection();
        return connection;
    }
}