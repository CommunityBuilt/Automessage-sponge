package com.TeamNovus.AutoMessage.Commands.PluginCommands;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import com.TeamNovus.AutoMessage.Permission;

public class ReloadCommand implements CommandCallable {

	private final Optional<Text> desc = Optional.of(Text.of("Reload the configuration from the disk."));
    private final Optional<Text> help = Optional.of(Text.of("Reload the configuration from the disk."));
    private final Text usage = Text.of("");
    

	@Override
	public Optional<Text> getHelp(CommandSource arg0) {
		return help;
	}

	@Override
	public Optional<Text> getShortDescription(CommandSource arg0) {
		return desc;
	}

	@Override
	public List<String> getSuggestions(CommandSource arg0, String arg1, Location<World> arg2) throws CommandException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Text getUsage(CommandSource arg0) {
		return usage;
	}

	@Override
	public CommandResult process(CommandSource sender, String args) throws CommandException {
        /*AutoMessage.plugin.loadConfig();

        sender.sendMessage(ChatColor.GREEN + "Configuration reloaded from disk!");*/
		return CommandResult.success();
	}

	@Override
	public boolean testPermission(CommandSource src) {
		return src.hasPermission(Permission.getPermission(Permission.COMMAND_RELOAD));
	}
}
