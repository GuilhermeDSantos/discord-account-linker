package dev.guilhermeds.entities;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class DiscordVerificationCode {

    private static final Integer EXPIRATION_TIME = 300;

    private final UUID playerUniqueId;
    private final String playerNick;
    private final String code;
    private final long expiration;

    public DiscordVerificationCode(UUID playerUniqueId, String playerNick, String code) {
        this.playerUniqueId = playerUniqueId;
        this.playerNick = playerNick;
        this.code = code;
        this.expiration = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(EXPIRATION_TIME);
    }

    public UUID getPlayerUniqueId() {
        return playerUniqueId;
    }

    public String getPlayerNick() {
        return playerNick;
    }

    public String getCode() {
        return code;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > expiration;
    }
}
