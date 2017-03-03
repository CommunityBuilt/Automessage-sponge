package com.TeamNovus.AutoMessage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;

import com.TeamNovus.AutoMessage.Commands.Common.CommandHandler;
import com.TeamNovus.AutoMessage.Models.Message;
import com.TeamNovus.AutoMessage.Models.MessageList;
import com.TeamNovus.AutoMessage.Models.MessageLists;
import com.google.inject.Inject;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(id = "automessage", name = "auto message", version = "1.0")
public class AutoMessage  {
	public static AutoMessage plugin;
	@Inject
	private Logger logger;
	private Game game;
	private String name = "auto message";
	
	@Inject
	@DefaultConfig(sharedRoot = true)
	private Path defaultConfig;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private ConfigurationLoader<CommentedConfigurationNode> configManager;

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path privateConfigDir;
	
	private ConfigurationNode rootNode;
	
	@Listener
	// logger and config files
    public void onPreInit(GamePreInitializationEvent event) {
		plugin = this;
		System.out.println("PreInit");
		
		rootNode = null;
		
		loadConfig();

		// Saves any version changes to the disk
		saveConfiguration();
		
    }
	
	@Listener
	//command registration
    public void onServerStarting(GameStartingServerEvent event) {
		System.out.println("ServerStarting");
		CommandHandler.register();
    }
	
	@Listener
	//cleanup (no players are connected no changes to worlds are saved)
    public void onServerStopped(GameStoppedServerEvent event) {
		System.out.println("ServerStopping");
		
		MessageLists.unschedule();
    }
	
	public void saveConfiguration() {
		
		for (String key : MessageLists.getMessageLists().keySet()) {
			MessageList list = MessageLists.getExactList(key);
			rootNode.getNode("message-lists" , key , "enabled").setValue(list.isEnabled());
			rootNode.getNode("message-lists" , key , "interval").setValue(list.getInterval());
			rootNode.getNode("message-lists" , key , "random").setValue(list.isRandom());

			List<String> messages = new LinkedList<String>();

			for (Message m : list.getMessages()) {
				messages.add(m.getMessage());
			}

			rootNode.getNode("message-lists" , key , "messages").setValue(messages);
		}

		saveConfig();
	}
	
	public Logger getLogger(){
		return this.logger;
	}
	
	@Inject
	public void setGame(Game game){
		this.game = game;
	}
	
	public Game getGame(){
		return game;
	}
	
	public String getName(){
		return name;
	}
	
	public void saveConfig(){
		try {
			configManager.save(rootNode);
		} catch(IOException e) {
		    // error
		}
	}

	public ConfigurationNode getRootNode() {
		return rootNode;
	}

	public void loadConfig() {
		try {
		    rootNode = configManager.load();
		} catch(IOException e) {
		    // error
		}

		MessageLists.clear();

		for (Object key : rootNode.getNode("message-lists").getChildrenMap().keySet().toArray()) {
			
			MessageList list = new MessageList();

			if (rootNode.getNode("message-lists" , key , "enabled") != null)
				list.setEnabled(rootNode.getNode("message-lists" , key , "enabled").getBoolean());

			if (rootNode.getNode("message-lists" , key , "interval") != null)
				list.setInterval(rootNode.getNode("message-lists" , key , "interval").getInt());


			if (rootNode.getNode("message-lists" , key , "random") != null)
				list.setRandom(rootNode.getNode("message-lists" , key , "random").getBoolean());

			LinkedList<Message> finalMessages = new LinkedList<Message>();

			if (rootNode.getNode("message-lists" , key , "messages") != null) {
				ArrayList<Object> messages = (ArrayList<Object>) rootNode.getNode("message-lists" , key , "messages").getValue();

				for (Object m : messages) {
					if (m instanceof String) {
						finalMessages.add(new Message((String) m));
					} else if (m instanceof Map) {
						Map<String, List<String>> message = (Map<String, List<String>>) m;
						for (Entry<String, List<String>> entry : message.entrySet()) {
							finalMessages.add(new Message(entry.getKey()));
						}
					}
				}
			}

			list.setMessages(finalMessages);

			MessageLists.setList(key.toString(), list);
		}

		MessageLists.schedule();
	}
}
