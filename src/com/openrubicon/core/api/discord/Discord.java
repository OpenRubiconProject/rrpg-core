package com.openrubicon.core.api.discord;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Discord {

    private static JDA api;
    private static String token = "";

    public Discord(String token) {
        Discord.token = token;
        try {
            Discord.api = new JDABuilder(AccountType.BOT).setToken(Discord.token).addEventListener(new DiscordEventTestListener()).buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown()
    {
        Discord.api.shutdownNow();
    }

    public static JDA getApi() {
        return api;
    }
}
