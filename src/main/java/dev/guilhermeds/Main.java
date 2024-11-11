package dev.guilhermeds;

import dev.guilhermeds.commands.LinkCommand;
import dev.guilhermeds.manager.DiscordManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("link").setExecutor(new LinkCommand());

        DiscordManager.getInstance().start(this);
    }

    @Override
    public void onDisable() {
    }
}