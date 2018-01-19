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

	public CommandResult execute(CommandSource sender, CommandContext context) throws CommandException {
        AutoMessage.plugin.loadConfig();

        sender.sendMessage(Text.builder("Configuration reloaded from disk!")
				.color(CommandHandler.light)
                .build()
        );
		return CommandResult.success();
	}
}
