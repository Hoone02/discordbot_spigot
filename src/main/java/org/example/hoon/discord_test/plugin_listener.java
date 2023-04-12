package org.example.hoon.discord_test;

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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class plugin_listener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        for (Command command : Discord_test.getJda().getGuildById("868345253038534686").retrieveCommands().complete()) {
            Bukkit.getLogger().info(command.getName());
            //command.delete().queue();
            command.editCommand().clearOptions().queue();
        }

        List<CommandData> commandData = new ArrayList<>();

        List<String> onlinePlayers = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());
        onlinePlayers.add(e.getPlayer().getName());

        OptionData optionData = new OptionData(OptionType.STRING, "nickname", "아무튼 닉네임", true)
                .addChoices(onlinePlayers.stream().map(s -> new Command.Choice(s, s)).collect(Collectors.toList()));
        commandData.add(Commands.slash("register", "아무튼 회원가입")
                .addOptions(optionData));

        Discord_test.getJda().getGuildById("868345253038534686").updateCommands().addCommands(commandData).queue();

        e.getPlayer().sendMessage("접속");
    }

}
