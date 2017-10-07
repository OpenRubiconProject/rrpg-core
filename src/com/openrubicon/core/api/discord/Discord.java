package com.openrubicon.core.api.discord;

import com.openrubicon.core.api.interfaces.Errorable;
import com.openrubicon.core.api.interfaces.Startable;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

public class Discord implements Errorable, Startable {

    private static JDA api;
    private static String token = "";

    private boolean errored = false;

    public Discord(String token) {
        Discord.token = token;
        this.start();
    }

    public void shutdown()
    {
        Discord.api.shutdownNow();
    }

    @Override
    public String getError() {
        return null;
    }

    @Override
    public void restart() {

    }

    public void start()
    {
        try {
            //Discord.api = new JDABuilder(AccountType.BOT).setToken(Discord.token).addEventListener(new DiscordEventTestListener()).buildAsync();
            Discord.api = new JDABuilder(AccountType.BOT).setToken(Discord.token).addEventListener(new DiscordEventListener()).buildAsync();
            errored = false;
        } catch (LoginException e) {
            e.printStackTrace();
            errored = true;
        } catch (RateLimitedException e) {
            e.printStackTrace();
            errored = true;
        }
    }

    public static JDA getApi() {
        return api;
    }

    @Override
    public boolean isErrored() {
        return this.errored;
    }
}
