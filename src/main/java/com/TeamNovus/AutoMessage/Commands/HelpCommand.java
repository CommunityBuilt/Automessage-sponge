package com.TeamNovus.AutoMessage.Commands;

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

public class HelpCommand implements CommandCallable{

	private final Optional<Text> desc = Optional.of(Text.of("View all commands and their info."));
    private final Optional<Text> help = Optional.of(Text.of("View all commands and their info."));
    private final Text usage = Text.of("[Page]");
    

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
	public CommandResult process(CommandSource sender, String args) throws CommandException {// help [Page]
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

	@Override
	public boolean testPermission(CommandSource src) {
		return src.hasPermission(Permission.getPermission(Permission.NONE));
	}
	
	
	//---------------------------------------------------------------------------------------------------------------------------
	/*
	 * 
	 * 
	 * 
	
	public void onBase(){
        sender.sendMessage(CommandManager.getExtra() + "__________________.[ " + CommandManager.getHighlight() + AutoMessage.plugin.getName() + CommandManager.getExtra() + " ].__________________");
        sender.sendMessage(CommandManager.getDark() + "Description: " + CommandManager.getLight() + AutoMessage.plugin.getDescription().getDescription());
        sender.sendMessage(CommandManager.getDark() + "Author: " + CommandManager.getLight() + AutoMessage.plugin.getDescription().getAuthors().get(0));
        sender.sendMessage(CommandManager.getDark() + "Version: " + CommandManager.getLight() + AutoMessage.plugin.getDescription().getVersion());
        sender.sendMessage(CommandManager.getDark() + "Website: " + CommandManager.getLight() + AutoMessage.plugin.getDescription().getWebsite());
        sender.sendMessage(CommandManager.getExtra() + "---------------------------------------------------");
        return true;
	}
	
	@BaseCommand(aliases = "reload", desc = "Reload the configuration from the disk.", usage = "", permission = Permission.COMMAND_RELOAD)
    public void onReloadCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        AutoMessage.plugin.loadConfig();

        sender.sendMessage(ChatColor.GREEN + "Configuration reloaded from disk!");
    }

    @BaseCommand(aliases = "add", desc = "Add a list or message to a list.", usage = "<List> [Index] [Message]", min = 1, permission = Permission.COMMAND_ADD)
    public void onAddCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 1) {
            if (MessageLists.getExactList(args[0]) == null) {
                MessageLists.setList(args[0], new MessageList());

                AutoMessage.plugin.saveConfiguration();

                sender.sendMessage(ChatColor.GREEN + "List added sucessfully!");
            } else {
                sender.sendMessage(ChatColor.RED + "A list already exists by this name!");
            }
        } else {
            MessageList list = MessageLists.getBestList(args[0]);

            if (list != null) {
                if (args.length >= 2) {
                    if (args.length >= 3 && Utils.isInteger(args[1])) {
                        Message message = new Message(Utils.concat(args, 2, args.length));

                        list.addMessage(Integer.valueOf(args[1]), message);
                    } else {
                        Message message = new Message(Utils.concat(args, 1, args.length));

                        list.addMessage(message);
                    }

                    AutoMessage.plugin.saveConfiguration();

                    sender.sendMessage(ChatColor.GREEN + "Message added!");
                } else {
                    sender.sendMessage(ChatColor.RED + "Please specify more arguments!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
            }
        }
    }

    @BaseCommand(aliases = "edit", desc = "Edit a message in a list.", usage = "<List> <Index> <Message>", min = 3, permission = Permission.COMMAND_EDIT)
    public void onEditCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        MessageList list = MessageLists.getBestList(args[0]);

        if (list != null) {
            if (Utils.isInteger(args[1])) {
                Message message = new Message(Utils.concat(args, 2, args.length));

                if (list.editMessage(Integer.valueOf(args[1]), message)) {
                    AutoMessage.plugin.saveConfiguration();

                    sender.sendMessage(ChatColor.GREEN + "Message edited!");
                } else {
                    sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
        }
    }

    @BaseCommand(aliases = "remove", desc = "Remove a list or message from a list.", usage = "<List> [Index]", min = 1, max = 3, permission = Permission.COMMAND_REMOVE)
    public void onRemoveCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 1) {
            if (MessageLists.getExactList(args[0]) != null) {
                MessageLists.setList(args[0], null);

                AutoMessage.plugin.saveConfiguration();

                sender.sendMessage(ChatColor.GREEN + "List removed sucessfully!");
            } else {
                sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
            }
        } else {
            MessageList list = MessageLists.getBestList(args[0]);

            if (list != null) {
                if (Utils.isInteger(args[1])) {
                    if (list.removeMessage(Integer.valueOf(args[1]))) {
                        MessageLists.schedule();
                        AutoMessage.plugin.saveConfiguration();

                        sender.sendMessage(ChatColor.GREEN + "Message removed!");
                    } else {
                        sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
            }
        }
    }

    @BaseCommand(aliases = "enabled", desc = "Toggle broadcasting for a list.", usage = "<List>", min = 1, max = 1, permission = Permission.COMMAND_ENABLE)
    public void onEnableCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        MessageList list = MessageLists.getBestList(args[0]);

        if (list != null) {
            list.setEnabled(!(list.isEnabled()));

            AutoMessage.plugin.saveConfiguration();

            sender.sendMessage(ChatColor.GREEN + "Enabled: " + ChatColor.YELLOW + list.isEnabled() + ChatColor.GREEN + "!");
        } else {
            sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
        }
    }

    @BaseCommand(aliases = "interval", desc = "Set a lists broadcast interval.", usage = "<List> <Interval>", min = 2, max = 2, permission = Permission.COMMAND_INTERVAL)
    public void onIntervalCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        MessageList list = MessageLists.getBestList(args[0]);

        if (list != null) {
            if (Utils.isInteger(args[1])) {
                list.setInterval(Integer.valueOf(args[1]));

                MessageLists.schedule();
                AutoMessage.plugin.saveConfiguration();

                sender.sendMessage(ChatColor.GREEN + "Interval: " + ChatColor.YELLOW + Integer.valueOf(args[1]) + ChatColor.GREEN + "!");
            } else {
                sender.sendMessage(ChatColor.RED + "The interval must be an Integer!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
        }
    }

    @BaseCommand(aliases = "random", desc = "Set a lists broadcast method.", usage = "<List>", min = 1, max = 1, permission = Permission.COMMAND_RANDOM)
    public void onRandomCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        MessageList list = MessageLists.getBestList(args[0]);

        if (list != null) {
            list.setRandom(!(list.isRandom()));

            AutoMessage.plugin.saveConfiguration();

            sender.sendMessage(ChatColor.GREEN + "Random: " + ChatColor.YELLOW + list.isRandom() + ChatColor.GREEN + "!");
        } else {
            sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
        }
    }

    @BaseCommand(aliases = "broadcast", desc = "Broadcast a message from a list.", usage = "<List> <Index>", min = 2, max = 2, permission = Permission.COMMAND_BROADCAST)
    public void onBroadcast(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        MessageList list = MessageLists.getBestList(args[0]);

        if (list != null) {
            if (Utils.isInteger(args[1])) {
                int index = Integer.valueOf(args[1]);

                if (list.getMessage(index) != null) {
                    list.broadcast(index);
                } else {
                    sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The specified index does not exist!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
        }
    }

    @BaseCommand(aliases = "list", desc = "List all lists or messages in a list.", usage = "[List]", max = 1, permission = Permission.COMMAND_LIST)
    public void onListCmd(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (args.length == 0) {
            if (MessageLists.getMessageLists().keySet().size() != 0) {
                sender.sendMessage(ChatColor.DARK_RED + "Availiable Lists:");

                for (String key : MessageLists.getMessageLists().keySet()) {
                    sender.sendMessage(ChatColor.GOLD + key);
                }
            } else {
                sender.sendMessage(ChatColor.RED + "No lists available!");
            }
        } else {
            MessageList list = MessageLists.getBestList(args[0]);

            if (list != null) {
                sender.sendMessage(ChatColor.DARK_RED + MessageLists.getBestKey(args[0]));

                List<Message> messages = list.getMessages();
                for (int i = 0; i < messages.size(); i++) {
                    sender.sendMessage(ChatColor.YELLOW + "" + i + ": " + ChatColor.RESET + ChatColor.translateAlternateColorCodes("&".charAt(0), messages.get(i).getMessage()));
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The specified list does not exist!");
            }
        }
    }*/
}
