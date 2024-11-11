package dev.guilhermeds.commands;

import dev.guilhermeds.entities.DiscordVerificationCode;
import dev.guilhermeds.manager.AccountLinkerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }
        DiscordVerificationCode discordVerificationCode = AccountLinkerManager.getInstance().getVerificationCode(player);

        player.sendMessage("To link your Minecraft account with Discord, type in Discord: " + ChatColor.AQUA + "/link " + discordVerificationCode.getCode());
        return true;
    }
}
