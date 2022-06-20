package github.notmeg4.nightmode;
import github.notmeg4.nightmode.commands.reloadCMD;
import github.notmeg4.nightmode.commands.toggleCMD;
import github.notmeg4.nightmode.listeners.worldChange;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import github.notmeg4.nightmode.listeners.placeholderRequest;
import github.notmeg4.nightmode.utils.dbConnect;
import github.notmeg4.nightmode.listeners.playerJoin;


import java.io.File;
import java.sql.Connection;

public class NightMode extends JavaPlugin {
    private NightMode plugin;
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