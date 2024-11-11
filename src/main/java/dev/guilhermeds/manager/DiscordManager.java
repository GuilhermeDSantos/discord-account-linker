package dev.guilhermeds.manager;

import dev.guilhermeds.listeners.SlashCommandListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.Collections;

import static net.dv8tion.jda.api.interactions.commands.OptionType.STRING;

public class DiscordManager {

    private static DiscordManager instance;
    private JDA jda;

    private DiscordManager() {
    }

    public static DiscordManager getInstance() {
        if (instance == null) {
            instance = new DiscordManager();
        }
        return instance;
    }

    public void start(Plugin plugin) {
        String token = "{{YOUR_TOKEN}}";
        this.jda = JDABuilder.createLight(token, Collections.emptyList())
                .addEventListeners(new SlashCommandListener())
                .build();

        CommandListUpdateAction commands = jda.updateCommands();

        commands.addCommands(
                Commands.slash("link", "Link a minecraft account")
                        .addOption(STRING, "code", "What is your linking code?", true)
        );

        commands.queue();
    }
}
