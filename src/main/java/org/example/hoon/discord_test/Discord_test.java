package org.example.hoon.discord_test;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class Discord_test extends JavaPlugin {

    private static JDA jda;
    private String discordToken = "MTA5NTM1NzQxMDY5NDE1MjI4OA.GAiiPB.4S4ckD1vqwWTQcvLVaz0apuLKJ5YsGZlzPo_XA";

    @Override
    public void onEnable() {
        try {
            JDABuilder builder = JDABuilder.createDefault(discordToken);
            builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
            builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
            jda = builder.build();
            jda.addEventListener(new Discord_Listener());
            jda.awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JDA getJda() {
        return jda;
    }

    @Override
    public void onDisable() {
        jda.shutdown();
    }
}
