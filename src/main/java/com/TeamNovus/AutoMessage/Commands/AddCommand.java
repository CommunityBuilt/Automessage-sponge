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
import com.TeamNovus.AutoMessage.Models.Message;
import com.TeamNovus.AutoMessage.Models.MessageList;
import com.TeamNovus.AutoMessage.Models.MessageLists;
import com.TeamNovus.AutoMessage.Util.Utils;

public class AddCommand implements CommandExecutor{


	
	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
		final Optional<Object> listName = ctx.getOne("name");
		final Optional<Object> Index = ctx.getOne("index");
		final Optional<Object> Message = ctx.getOne("message");
		
		if(listName.isPresent()){
			if (!Index.isPresent()) {
		        if (MessageLists.getExactList((String)listName.get()) == null) {
		            MessageLists.setList((String)listName.get(), new MessageList());
		
		            AutoMessage.plugin.saveConfiguration();
		
		            sender.sendMessage(Text.builder().color(TextColors.GREEN).append(Text.of("List added sucessfully!")).build());
		        } else {
		            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("A list already exists by this name!")).build());
		        }
		    } else {
		        MessageList list = MessageLists.getBestList((String)listName.get());
		
		        if (list != null) {
	                if (Message.isPresent() && Utils.isInteger((String)Index.get())) {
	                    Message message = new Message((String)Message.get());
	
	                    list.addMessage(Integer.valueOf((String)Index.get()), message);
	                } else {
	                    Message message = new Message((String)Index.get());
	
	                    list.addMessage(message);
	                }
	
	                AutoMessage.plugin.saveConfiguration();
	
	                sender.sendMessage(Text.builder().color(TextColors.GREEN).append(Text.of("Message added!")).build());
		        } else {
		            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
		        }
		    }
		}else{
			sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list specified!")).build());
		}
		return CommandResult.success();
	}	
}
