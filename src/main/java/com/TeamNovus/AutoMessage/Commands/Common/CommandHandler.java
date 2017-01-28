package com.TeamNovus.AutoMessage.Commands.Common;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.dispatcher.SimpleDispatcher;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Commands.BaseCommand;
import com.TeamNovus.AutoMessage.Commands.HelpCommand;
import com.TeamNovus.AutoMessage.Commands.PluginCommands.ReloadCommand;

public class CommandHandler {
	private static TextColor light = TextColors.GREEN;
	private static TextColor dark = TextColors.DARK_GREEN;
	private static TextColor neutral = TextColors.WHITE;
	private static TextColor highlight = TextColors.AQUA;
	private static TextColor extra = TextColors.DARK_RED;
	private static TextColor error = TextColors.RED;
	private static TextColor warning = TextColors.YELLOW;

	public static TextColor getLight() {
		return light;
	}

	public static TextColor getDark() {
		return dark;
	}

	public static TextColor getNeutral() {
		return neutral;
	}

	public static TextColor getHighlight() {
		return highlight;
	}

	public static TextColor getExtra() {
		return extra;
	}

	public static TextColor getError() {
		return error;
	}

	public static TextColor getWarning() {
		return warning;
	}

	public static void register() {
		
		SimpleDispatcher rootCommand = new SimpleDispatcher(new BaseCommand());
		
		AutoMessage.plugin.getLogger().warn("[AM]",AutoMessage.plugin);
		
		rootCommand.register(new HelpCommand(), "help", "?");
		rootCommand.register(new ReloadCommand(), "reload", "rl");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, rootCommand, "Automessage","AM");
	}
}
