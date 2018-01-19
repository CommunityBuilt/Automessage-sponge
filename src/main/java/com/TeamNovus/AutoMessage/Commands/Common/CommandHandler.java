package com.TeamNovus.AutoMessage.Commands.Common;

import com.TeamNovus.AutoMessage.Commands.*;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

import com.TeamNovus.AutoMessage.AutoMessage;
import com.TeamNovus.AutoMessage.Permission;
import com.TeamNovus.AutoMessage.Commands.PluginCommands.ReloadCommand;

public class CommandHandler {
	public static TextColor light = TextColors.GREEN;
	public static TextColor dark = TextColors.DARK_GREEN;
	public static TextColor neutral = TextColors.WHITE;
	public static TextColor highlight = TextColors.AQUA;
	public static TextColor extra = TextColors.DARK_RED;
	public static TextColor error = TextColors.RED;
	public static TextColor warning = TextColors.YELLOW;


	public static void register() {
		CommandSpec reload = CommandSpec.builder()
                .description(Text.of("Reload the configuration from the disk."))
                .permission(Permission.getPermission(Permission.COMMAND_RELOAD))
                .executor(new ReloadCommand())
                .build();
		
		CommandSpec list = CommandSpec.builder()
				.description(Text.of("List all lists or messages in a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_LIST))
		        .arguments(
		        		GenericArguments.optional(GenericArguments.string(Text.of("name")))
		        			 )
		        .executor(new ListAllCommand())
		        .build();
		
		CommandSpec interval = CommandSpec.builder()
				.description(Text.of("Set a lists broadcast interval."))
		        .permission(Permission.getPermission(Permission.COMMAND_INTERVAL))
		        .arguments(
		        		GenericArguments.string(Text.of("name")),
		        		GenericArguments.string(Text.of("number"))
		        		)
		        .executor(new SetIntervalCommand())
		        .build();
		
		CommandSpec broadcast = CommandSpec.builder()
				.description(Text.of("Broadcast a message from a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_BROADCAST))
		        .arguments(
		        		GenericArguments.string(Text.of("name")),
		        		GenericArguments.string(Text.of("number"))
		        		)
		        .executor(new BroadCastCommand())
		        .build();
		
		CommandSpec random = CommandSpec.builder()
				.description(Text.of("Set a lists broadcast method."))
		        .permission(Permission.getPermission(Permission.COMMAND_RANDOM))
		        .arguments(
		        		GenericArguments.string(Text.of("name"))
		        		)
		        .executor(new RandomCommand())
		        .build();
		
		CommandSpec add = CommandSpec.builder()
				.description(Text.of("Add a list or message to a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_ADD))
		        .arguments(
		        		GenericArguments.string(Text.of("name")),
		        		GenericArguments.optional(GenericArguments.string(Text.of("index"))),
		        		GenericArguments.optional(GenericArguments.string(Text.of("message")))
		        		)
		        .executor(new AddCommand())
		        .build();
		
		CommandSpec remove = CommandSpec.builder()
				.description(Text.of("Remove a list or message from a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_REMOVE))
		        .arguments(
		        		GenericArguments.string(Text.of("name")),
		        		GenericArguments.optional(GenericArguments.string(Text.of("index")))
		        		)
		        .executor(new RemoveCommad())
		        .build();

		CommandSpec edit = CommandSpec.builder()
				.description(Text.of("Edit a message in a list."))
		        .permission(Permission.getPermission(Permission.COMMAND_EDIT))
		        .arguments(
					GenericArguments.string(Text.of("name")),
					GenericArguments.string(Text.of("index")),
					GenericArguments.string(Text.of("message"))
				)
		        .executor(new EditCommand())
		        .build();

		CommandSpec enabled = CommandSpec.builder()
			.description(Text.of("Toggle broadcasting for a list."))
			.permission(Permission.getPermission(Permission.COMMAND_ENABLE))
			.arguments(
				GenericArguments.string(Text.of("name"))
			)
			.executor(new EnableCommand())
			.build();


		CommandSpec base = CommandSpec.builder()
			.child(reload,"reload", "rl")
			.child(list, "list","ls")
			.child(interval, "interval")
			.child(broadcast, "broadcast")
			.child(random, "random")
			.child(add, "add")
			.child(remove, "remove")
			.child(edit, "edit")
			.child(enabled, "enabled")
			.build();

		Sponge.getCommandManager().register(AutoMessage.plugin,base,"am","automessage");
	}
	
}
