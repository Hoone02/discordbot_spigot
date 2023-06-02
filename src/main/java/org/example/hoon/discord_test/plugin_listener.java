package org.example.hoon.discord_test;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class plugin_listener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

//        for (Command command : Discord_test.getJda().getGuildById("868345253038534686").retrieveCommands().complete()) {
//            Bukkit.getLogger().info(command.getName());
//            command.delete().queue();
//        }

        List<String> list = Discord_Listener.getOnlinePlayers();

        Discord_Listener.set(list);

        e.getPlayer().sendMessage("접속");
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        List<String> list = Discord_Listener.getOnlinePlayers();
        list.remove(e.getPlayer().getName());

        Discord_Listener.set(list);
    }

}
