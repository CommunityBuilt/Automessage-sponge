package com.TeamNovus.AutoMessage.Commands;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandMapping;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.dispatcher.Disambiguator;
import org.spongepowered.api.text.Text;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Commands.Common.CommandHandler;

public class BaseCommand implements Disambiguator{
	
	@Override
	public Optional<CommandMapping> disambiguate(CommandSource source, String aliasUsed, List<CommandMapping> availableOptions) {
		source.sendMessage(Text.of(CommandHandler.getExtra() + "__________________.[ " + CommandHandler.getHighlight() + AutoMessage.plugin.getName() + CommandHandler.getExtra() + " ].__________________"));
	    /*sender.sendMessage(CommandHandler.getDark() + "Description: " + CommandHandler.getLight() + AutoMessage.plugin.getDescription().getDescription());
	    sender.sendMessage(CommandHandler.getDark() + "Author: " + CommandHandler.getLight() + AutoMessage.plugin.getDescription().getAuthors().get(0));
	    sender.sendMessage(CommandHandler.getDark() + "Version: " + CommandHandler.getLight() + AutoMessage.plugin.getDescription().getVersion());
	    sender.sendMessage(CommandHandler.getDark() + "Website: " + CommandHandler.getLight() + AutoMessage.plugin.getDescription().getWebsite());
	    sender.sendMessage(CommandHandler.getExtra() + "---------------------------------------------------");*/
		return null;
	}
	
}
