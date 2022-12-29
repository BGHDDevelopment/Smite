package com.bghddevelopment.smite.commands;

import com.bghddevelopment.smite.utilities.Common;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 16:36
 */
public final class SmiteCommand implements TabExecutor {
    private final String PERMISSION = "smite.use";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (player.hasPermission(PERMISSION)) {
                if (args.length >= 1) {
                    final Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target != null) {
                        Common.tell(player, "&aHit &e{target} &awith a lightning strike!".replace("{target}", target.getName()));

                        target.getWorld().strikeLightningEffect(target.getLocation());
                    } else {
                        Common.tell(player, "&cInvalid! Player not found!");
                    }

                    return true;
                }

                return false;
            } else {
                Common.tell(player, "&cYou don't have permission to use this command!");
            }

            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission(PERMISSION)) ? null : Collections.emptyList();
    }

}
