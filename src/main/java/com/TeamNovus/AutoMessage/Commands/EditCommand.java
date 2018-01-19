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

public class EditCommand implements CommandExecutor {

	public CommandResult execute(CommandSource sender, CommandContext ctx) throws CommandException {
	final Optional<Object> listName = ctx.getOne("name");
	final Optional<Object> Index = ctx.getOne("index");
	final Optional<Object> Message = ctx.getOne("message");
	
	if(listName.isPresent()){
		MessageList list = MessageLists.getBestList((String)listName.get());

        if (list != null) {
        	if(Index.isPresent()){
	            if (Utils.isInteger((String)Index.get())) {
	            	if(Message.isPresent()){
		                Message message = new Message((String)Message.get());
		
		                if (list.editMessage(Integer.valueOf((String)Index.get()), message)) {
		                    AutoMessage.plugin.saveConfiguration();
		
		                    sender.sendMessage(Text.builder().color(TextColors.GREEN).append(Text.of("Message edited!")).build());
		                    return CommandResult.success();
		                } else {
		                    sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified index does not exist!")).build());
		                }
	                }else{
	                	 sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The message index does not exist!")).build());
	                }
	            }
            } 
            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified index does not exist!")).build());
        } else {
            sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("The specified list does not exist!")).build());
        }
	}else{
        sender.sendMessage(Text.builder().color(TextColors.RED).append(Text.of("No list specified!")).build());
	}
	return CommandResult.success();
	}

}
