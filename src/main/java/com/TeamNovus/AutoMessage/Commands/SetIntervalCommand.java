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
import com.TeamNovus.AutoMessage.Util.Utils;

public class SetIntervalCommand implements CommandExecutor  {

	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
        final Optional<Object> ListName = ctx.getOne("name");
        final Optional<Object> number = ctx.getOne("number");
        
        if(ListName.isPresent()){
	        MessageList list = MessageLists.getBestList((String)ListName.get());
	        if(number.isPresent()){
		        if (list != null) {
		            if (Utils.isInteger((String)number.get())) {
		                list.setInterval(Integer.valueOf((String)number.get()));
		
		                MessageLists.schedule();
		                AutoMessage.plugin.saveConfiguration();
		
		                sender.sendMessage(Text.builder().color(TextColors.GREEN).append(Text.of("Interval: ")).color(TextColors.YELLOW).append(Text.of(list.getInterval())).color(TextColors.GREEN).append(Text.of("!")).build());
		            } else {
		                sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The interval must be an Integer!")).build());
		            }
		        }else{
		            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
		        }
	        } else {
                sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No Integer specified!")).build());
	        	
	        }
        } else{
            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list specified!")).build());
        }
		return CommandResult.success();
	}
}
