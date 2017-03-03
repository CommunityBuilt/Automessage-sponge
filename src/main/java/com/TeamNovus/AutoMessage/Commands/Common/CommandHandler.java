package com.TeamNovus.AutoMessage.Commands.Common;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Permission;
import com.TeamNovus.AutoMessage.Commands.AddCommand;
import com.TeamNovus.AutoMessage.Commands.BroadCastCommand;
import com.TeamNovus.AutoMessage.Commands.EditCommand;
import com.TeamNovus.AutoMessage.Commands.EnableCommand;
import com.TeamNovus.AutoMessage.Commands.ListAllCommand;
import com.TeamNovus.AutoMessage.Commands.RandomCommand;
import com.TeamNovus.AutoMessage.Commands.RemoveCommad;
import com.TeamNovus.AutoMessage.Commands.SetIntervalCommand;
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
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
                .description(Text.of("Reload the configuration from the disk."))
                .permission(Permission.getPermission(Permission.COMMAND_RELOAD))
                .executor(new ReloadCommand(AutoMessage.plugin))
                .build(), "reload","rl");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("List all lists or messages in a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_LIST))
		        .arguments(
		        		GenericArguments.optional(GenericArguments.string(Text.of("Listname")))
		        			 )
		        .executor(new ListAllCommand(AutoMessage.plugin))
		        .build(), "list","ls");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Set a lists broadcast interval."))
		        .permission(Permission.getPermission(Permission.COMMAND_INTERVAL))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname")),
		        		GenericArguments.string(Text.of("number"))
		        		)
		        .executor(new SetIntervalCommand(AutoMessage.plugin))
		        .build(), "interval");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Broadcast a message from a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_BROADCAST))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname")),
		        		GenericArguments.string(Text.of("number"))
		        		)
		        .executor(new BroadCastCommand(AutoMessage.plugin))
		        .build(), "broadcast");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Set a lists broadcast method."))
		        .permission(Permission.getPermission(Permission.COMMAND_RANDOM))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname"))
		        		)
		        .executor(new RandomCommand(AutoMessage.plugin))
		        .build(), "random");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Add a list or message to a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_ADD))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname")),
		        		GenericArguments.optional(GenericArguments.string(Text.of("index"))),
		        		GenericArguments.optional(GenericArguments.string(Text.of("message")))
		        		)
		        .executor(new AddCommand(AutoMessage.plugin))
		        .build(), "add");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Remove a list or message from a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_REMOVE))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname")),
		        		GenericArguments.optional(GenericArguments.string(Text.of("index")))
		        		)
		        .executor(new RemoveCommad(AutoMessage.plugin))
		        .build(), "remove");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Edit a message in a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_EDIT))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname")),
		        		GenericArguments.string(Text.of("index")),
		        		GenericArguments.string(Text.of("message"))
		        		)
		        .executor(new EditCommand(AutoMessage.plugin))
		        .build(), "edit");
		
		Sponge.getCommandManager().register(AutoMessage.plugin, CommandSpec.builder()
				.description(Text.of("Toggle broadcasting for a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_ENABLE))
		        .arguments(
		        		GenericArguments.string(Text.of("Listname"))
		        		)
		        .executor(new EnableCommand(AutoMessage.plugin))
		        .build(), "enabled");
	}
	
}
