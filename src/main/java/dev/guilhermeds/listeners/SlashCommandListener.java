package dev.guilhermeds.listeners;

import dev.guilhermeds.entities.DiscordVerificationCode;
import dev.guilhermeds.manager.AccountLinkerManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SlashCommandListener extends ListenerAdapter {
  @Override
  public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
    if (event.getName().equals("link")) {
      String code = event.getOption("code").getAsString();

      DiscordVerificationCode discordVerificationCode;

      try {
        discordVerificationCode = AccountLinkerManager.getInstance().findByCode(code);

        if (discordVerificationCode.isExpired()) {
          event.reply("Code expired.").queue();
        } else {
          Player player = Bukkit.getPlayer(discordVerificationCode.getPlayerUniqueId());

          event.getGuild().modifyNickname(event.getMember(), discordVerificationCode.getPlayerNick())
                  .queue(success -> {
                    event.reply("Your account has been successfully linked.").queue();

                    assert player != null;
                    player.sendMessage("Your account has been successfully linked with Discord.");
                  }, error -> {
                    event.reply("An error occurred while linking your account. Please try again.").queue();

                    assert player != null;
                    player.sendMessage("An error occurred while linking your account. Please try again.");
                  });
        }
      } catch (Exception exception) {
        event.reply(exception.getMessage()).queue();
      }
    }
  }
}