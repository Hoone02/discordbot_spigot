package org.example.hoon.discord_test;

import com.squareup.okhttp.OkHttpClient;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.Response;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Discord_test extends JavaPlugin {


    private static JDA jda;
    private String discordToken = "MTA5NTU1Nzc0ODQ5NTc2NTYwNQ.GB8GRh.IIVs9tr84fpH1EEU1-5fVSpSyDFIMWsh35CXkA";

    @Override
    public void onEnable() {
        JDABuilder builder = JDABuilder.createDefault(discordToken);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.setChunkingFilter(ChunkingFilter.ALL);
        jda = builder.build();
        jda.addEventListener(new Discord_Listener());
        try {
            jda.awaitReady();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getCommand("discord").setExecutor(new onCommand());
        getCommand("discord").setTabCompleter(new onCommand());

        Bukkit.getServer().getPluginManager().registerEvents(new plugin_listener(), this);
    }

    public static JDA getJda() {
        return jda;
    }

    @Override
    public void onDisable() {
        jda.shutdownNow();
    }
}
