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

public class EnableCommand implements CommandExecutor {

	public final AutoMessage plugin;
	
	public EnableCommand(AutoMessage plugin) {
		this.plugin = plugin;
	}
	
	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
		final Optional<Object> listName = ctx.getOne("Listname");
		
		if(listName.isPresent()){
			MessageList list = MessageLists.getBestList((String)listName.get());
	
		    if (list != null) {
		        list.setEnabled(!(list.isEnabled()));
		
		        AutoMessage.plugin.saveConfiguration();
		
		        sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of(TextColors.GREEN)).append(Text.of("Enabled: ")).color(TextColors.YELLOW).append(Text.of(list.isEnabled())).color(TextColors.GREEN).append(Text.of("!")).build());
		    } else {
		        sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
		    }
		}else{
	        sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list specified!")).build());
		}
		return CommandResult.success();
	}

}
