package com.TeamNovus.AutoMessage.Commands;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Models.MessageList;
import com.TeamNovus.AutoMessage.Models.MessageLists;

public class RandomCommand implements CommandExecutor  {

	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
		final Optional<Object> listName = ctx.getOne("name");
		if (listName.isPresent()) {
			MessageList list = MessageLists.getBestList((String)listName.get());
			
		    if (list != null) {
		        list.setRandom(!(list.isRandom()));
		
		        AutoMessage.plugin.saveConfiguration();
		
		        sender.sendMessage(Text.builder().color(TextColors.GREEN).append(Text.of("Random: ")).color(TextColors.YELLOW).append(Text.of(list.isRandom())).color(TextColors.GREEN).append(Text.of("!")).build());
		    } else {
		        sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
		    }
		}else{
			sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list specified!")).build());
		}
		return CommandResult.success();
    }
}
