package me.noodles.smite;

import me.noodles.smite.commands.SmiteCommand;
import me.noodles.smite.listeners.UpdateJoinEvent;
import me.noodles.smite.utilities.UpdateChecker;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.*;
import org.bukkit.command.*;
import org.bukkit.event.Listener;
import org.bukkit.*;

public class Smite extends JavaPlugin implements Listener
{
	
    public static Smite plugin;

	
    public void onEnable() {
    	Smite.plugin = this;
        final PluginDescriptionFile VarUtilType = this.getDescription();
        this.getLogger().info("Smite V" + VarUtilType.getVersion() + " starting...");
        this.saveDefaultConfig();
        this.reloadConfig();
        this.registerCommand("smite", new SmiteCommand());
        registerEvents((Plugin)this, new UpdateJoinEvent(this));
        registerEvents(this, this);
        this.getLogger().info("Smite V" + VarUtilType.getVersion() + " started!");
        this.setEnabled(true);
        this.getLogger().info("Smite V" + VarUtilType.getVersion() + " checking for updates...");
        if (getConfig().getBoolean("CheckForUpdates.Enabled", true)) {
            new UpdateChecker(this, 46604).getLatestVersion(remoteVersion -> {
                getLogger().info("Checking for Updates ...");

                if (getDescription().getVersion().equalsIgnoreCase(remoteVersion)) {
                    getLogger().info("No new version available");
                } else {
                    getLogger().warning(String.format("Newest version: %s is out! You are running version: %s", remoteVersion, getDescription().getVersion()));
                    getLogger().warning("Please Update Here: http://www.spigotmc.org/resources/46604");
                }
            });
        }
    }
    
    
    public static void registerEvents(final Plugin plugin, final Listener... listeners) {
        for (final Listener listener : listeners) {
            Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
        }
    }

    private void registerCommand(final String command, final CommandExecutor executor) {
        this.getCommand(command).setExecutor(executor);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes"})
	public static Smite getPlugin() {
        return (Smite)getPlugin((Class) Smite.class);
    }

}
