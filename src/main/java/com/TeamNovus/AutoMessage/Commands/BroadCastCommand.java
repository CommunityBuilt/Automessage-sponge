package com.TeamNovus.AutoMessage.Commands;

import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import com.TeamNovus.AutoMessage.Models.MessageList;
import com.TeamNovus.AutoMessage.Models.MessageLists;
import com.TeamNovus.AutoMessage.Util.Utils;

public class BroadCastCommand implements CommandExecutor  {
	
	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
		final Optional<Object> listName = ctx.getOne("name");
		final Optional<Object> number = ctx.getOne("number");
		
        if (listName.isPresent()) {
            MessageList list = MessageLists.getBestList((String)listName.get());
        	if(list != null){
	            if (number.isPresent()) {
		            if (Utils.isInteger((String)number.get())) {
		                int index = Integer.valueOf((String)number.get());
		
		                if (list.getMessage(index) != null) {
		                    list.broadcast(index);
		                    return CommandResult.success();
		                } 
		            }
	            }
                sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified index does not exist!")).build());
	        } else {
	            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
	        }
		}else{
            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list was specified!")).build());
		}
		return CommandResult.success();
	}
}
