package com.bghddevelopment.smite.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.bghddevelopment.smite.Smite;
import com.bghddevelopment.smite.utilities.Common;
import javafx.scene.control.Tab;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

/**
 * Author:  Kim (Thinkverse) Hallberg <work@hallberg.kim>
 * Created: 2020-04-11 16:36
 */

@CommandAlias("smite|smite")
@Description("Smite someone")
@CommandPermission("smite.use")
@Conditions("noconsole")
public final class SmiteCommand extends BaseCommand implements TabCompleter {

    @Dependency
    private Smite plugin;

    @Default
    public void onDefault(CommandSender sender, String[] args) {
            final Player player = (Player) sender;

                if (args.length >= 1) {
                    final Player target = Bukkit.getServer().getPlayer(args[0]);

                    if (target != null) {
                        Common.tell(player, "&aHit &e{target} &awith a lightning strike!".replace("{target}", target.getName()));

                        target.getWorld().strikeLightningEffect(target.getLocation());
                    } else {
                        Common.tell(player, "&cInvalid! Player not found!");
                    }
                    return;
                }
        return;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return (args.length == 1 && sender.hasPermission("smite.use")) ? null : Collections.emptyList();
    }

}
