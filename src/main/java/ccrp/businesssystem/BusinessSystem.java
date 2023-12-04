package ccrp.businesssystem;

import ccrp.businesssystem.commands.CommandManager;
import ccrp.businesssystem.data.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class BusinessSystem extends JavaPlugin {
    private static Plugin plugin;
    private static Config rankConfig;

    public static Plugin getPlugin() {
        return plugin;
    }

    public static Config getRankConfig() {
        return rankConfig;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Enabling BusinessSystem");

        //dependency injection setup
        plugin = this;

        //setting up config files
        saveDefaultConfig();
        saveConfig();

        File rankConfigFile = new File(getDataFolder().getPath() + File.separator + "ranks.json");
        try {
            rankConfigFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        rankConfig = new Config(rankConfigFile);
        rankConfig.save();





        //setting command executor
        Objects.requireNonNull(getCommand("business")).setExecutor(new CommandManager());

        //register events
        //getServer().getPluginManager().registerEvents(new EventHandler(),this);


        Bukkit.getLogger().info("Enabled BusinessSystem");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
