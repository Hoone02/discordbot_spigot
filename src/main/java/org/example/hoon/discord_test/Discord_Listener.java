package org.example.hoon.discord_test;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class Discord_Listener extends ListenerAdapter {

    @Override
    public void onReady(ReadyEvent e) {
        TextChannel ch = e.getJDA().getTextChannelById("868345253038534689");
        ch.sendMessage("확인").queue();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            Bukkit.getLogger().info("테스트");
            Bukkit.getLogger().info(e.getMessage() + "");
            Bukkit.getLogger().info("" + e.getAuthor());
            Bukkit.getServer().broadcastMessage( e.getAuthor().getName() + " : " + e.getMessage().getContentRaw());
        }
    }
}
