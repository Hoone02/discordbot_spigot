package org.example.hoon.discord_test;

import kotlin.collections.ArrayDeque;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Discord_Listener extends ListenerAdapter {
    @Override
    public void onGuildReady(GuildReadyEvent e) {
        List<CommandData> commandData = new ArrayList<>();

        List<String> onlinePlayers = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());

        OptionData optionData = new OptionData(OptionType.STRING, "nickname", "아무튼 닉네임", true)
                .addChoices(onlinePlayers.stream().map(s -> new Command.Choice(s, s)).collect(Collectors.toList()));
        commandData.add(Commands.slash("register", "아무튼 회원가입")
                .addOptions(optionData));

        Discord_test.getJda().getGuildById("868345253038534686").updateCommands().addCommands(commandData).queue();

        Bukkit.getLogger().info("GuildReady");
    }

    public static void set(JDA jda) {
        List<CommandData> commandData = new ArrayList<>();

        List<String> onlinePlayers = Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList());

        OptionData optionData = new OptionData(OptionType.STRING, "nickname", "아무튼 닉네임", true)
                .addChoices(onlinePlayers.stream().map(s -> new Command.Choice(s, s)).collect(Collectors.toList()));
        commandData.add(Commands.slash("register", "아무튼 회원가입")
                .addOptions(optionData));

        jda.updateCommands().addCommands(commandData).queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent e) {
        String command = e.getName();

        if (command.equals("register")) {
            String name = e.getOption("nickname").getAsString();
            e.reply("마인크래프트 " + name + "님으로 인증요청 되었습니다.").queue();
            Bukkit.getPlayer(name).sendMessage("디스코드의 " + e.getUser().getName() + "계정으로 인증요청 되었습니다.");
            System.out.println(e.getCommandId());
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {

        }
    }

}
