package com.TeamNovus.AutoMessage.Commands;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.serializer.TextSerializers;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Models.Message;
import com.TeamNovus.AutoMessage.Models.MessageList;
import com.TeamNovus.AutoMessage.Models.MessageLists;

public class ListAllCommand implements CommandExecutor  {

	public final AutoMessage plugin;
	
	public ListAllCommand(AutoMessage plugin) {
		this.plugin = plugin;
	}
	
	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
		final Optional<Object> listName = ctx.getOne("Listname");
		if (!listName.isPresent()) {
            if (MessageLists.getMessageLists().keySet().size() != 0) {
                sender.sendMessage(Text.builder().color(TextColors.DARK_RED).append(Text.of("Availiable Lists:")).build());

                for (String key : MessageLists.getMessageLists().keySet()) {
                    sender.sendMessage(Text.builder().color(TextColors.GOLD).append(Text.of(key)).build());
                }
            } else {
                sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No lists available!")).build());
            }
        } else {
            MessageList list = MessageLists.getBestList((String)listName.get());

            if (list != null) {
                sender.sendMessage(Text.builder().color(TextColors.DARK_RED).append(Text.of(MessageLists.getBestKey((String)listName.get()))).build());

                List<Message> messages = list.getMessages();
                for (int i = 0; i < messages.size(); i++) {
                    sender.sendMessage(Text.builder().color(TextColors.YELLOW).append(Text.of(i + ": ")).color(TextColors.RESET).append(TextSerializers.FORMATTING_CODE.deserializeUnchecked(messages.get(i).getMessage())).build());
                }
            } else {
                sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
            }
        }
		return CommandResult.success();
	}
}
