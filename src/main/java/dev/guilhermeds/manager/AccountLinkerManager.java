package dev.guilhermeds.manager;

import dev.guilhermeds.entities.DiscordVerificationCode;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountLinkerManager {

    private static AccountLinkerManager instance;
    private final Map<UUID, DiscordVerificationCode> playerCode;
    private static final SecureRandom RANDOM = new SecureRandom();

    private AccountLinkerManager() {
        this.playerCode = new HashMap<>();
    }

    public static AccountLinkerManager getInstance() {
        if (instance == null) {
            instance = new AccountLinkerManager();
        }
        return instance;
    }

    public static String generateCode() {
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            char randomDigit = (char) ('0' + RANDOM.nextInt(10));
            sb.append(randomDigit);
        }
        return sb.toString();
    }

    public DiscordVerificationCode getVerificationCode(Player player) {
        System.out.println(this.playerCode.containsKey(player.getUniqueId()));
        if (this.playerCode.containsKey(player.getUniqueId()) && !this.playerCode.get(player.getUniqueId()).isExpired()) {
            return this.playerCode.get(player.getUniqueId());
        }

        DiscordVerificationCode discordVerificationCode = new DiscordVerificationCode(player.getUniqueId(), player.getName(), generateCode());
        this.playerCode.put(player.getUniqueId(), discordVerificationCode);

        return discordVerificationCode;
    }

    public DiscordVerificationCode findByCode(String code) {
        return this.playerCode.values().stream()
                .filter(verificationCode -> verificationCode.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification code"));
    }
}
