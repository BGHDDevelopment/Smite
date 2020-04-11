package me.noodles.smite;

import me.noodles.smite.listeners.UpdateJoinEvent;
import me.noodles.smite.utilities.UpdateChecker;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
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
    
    @SuppressWarnings({ "unchecked", "rawtypes"})
	public static Smite getPlugin() {
        return (Smite)getPlugin((Class) Smite.class);
    }
    

    @SuppressWarnings("unused")
	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
    	if (!(sender instanceof Player)){
            Bukkit.getServer().getLogger().info("Only players can do this!");
            return true;
    		}
    	final Player p = (Player)sender;
        if (!cmd.getName().equalsIgnoreCase("smite")) {
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid! Use like /smite playersname!"));
            return true;
        }
        if (args.length > 1) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid! Use like /smite playersname!"));
            return true;
        }
        else {
            final Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid! Player not found!"));
                return true;
            }
            if (!sender.hasPermission("smite.use")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "(!) You don't have permssion to use this command!"));
                return true;
            }
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aHit " + ChatColor.YELLOW  + target.getName() + " &awith a lightning strike!"));
            final Location i = target.getLocation();
            final World world2 = target.getWorld();
            world2.strikeLightningEffect(i);
            return true;
        }
    }
}
