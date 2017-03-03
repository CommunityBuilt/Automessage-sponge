package com.TeamNovus.AutoMessage.Commands.PluginCommands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Commands.Common.CommandHandler;

public class ReloadCommand implements CommandExecutor  {

	public final AutoMessage plugin;
	
	public ReloadCommand(AutoMessage plugin) {
		this.plugin = plugin;
	}
	
	public CommandResult execute(CommandSource sender, CommandContext context) throws CommandException {
        plugin.loadConfig();

        sender.sendMessage(Text.builder().color(CommandHandler.getLight()).append(Text.of("Configuration reloaded from disk!")).build());
		return CommandResult.success();
	}
}
