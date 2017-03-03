package com.TeamNovus.AutoMessage.Commands;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Permission;

public class HelpCommand implements CommandExecutor{
	
	public CommandResult execute(CommandSource sender, String args) throws CommandException {// help [Page]
		int maxLines = 6;

		/*if (args.length != 0) {
			if (!(Utils.isInteger(args[0])) || Math.abs(Integer.valueOf(args[0])) * maxLines - maxLines >= CommandManager.getCommands().size()) {
				sender.sendMessage(ChatColor.RED + "The specified page was not found.");
				return;
			}
		}

		int page = args.length == 0 ? 1 : Math.abs(Integer.valueOf(args[0]));
		int total = 0;
		sender.sendMessage(CommandManager.getExtra() + "__________________.[ " + CommandManager.getHighlight() + AutoMessage.plugin.getName() + CommandManager.getExtra() + " ].__________________");

		sender.sendMessage(ChatColor.GRAY + "Required: < > Optional: [ ]");
		for (int i = maxLines * page - maxLines; i < CommandManager.getCommands().size() && total < maxLines - 1; i++) {
			BaseCommand command = CommandManager.getCommands().get(i);
			if (!(command.hidden()) && Permission.has(command.permission(), sender)) {
				sender.sendMessage(CommandManager.getExtra() + " - " + CommandManager.getDark() + "/" + commandLabel + " " + command.aliases()[0] + (!(command.usage().equals("")) ? " " + command.usage() : "") + ": " + CommandManager.getLight() + command.desc());
				total++;
			}
		}
		sender.sendMessage(CommandManager.getLight() + "For help type: " + CommandManager.getHighlight() + "/" + commandLabel + " help [Page]");
		sender.sendMessage(CommandManager.getExtra() + "---------------------------------------------------");*/
		return CommandResult.success();
	}

	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		// TODO Auto-generated method stub
		return null;
	}
}
