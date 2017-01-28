package com.TeamNovus.AutoMessage;

public enum Permission {
	COMMAND_RELOAD("commands.reload"),
	COMMAND_ADD("commands.add"),
	COMMAND_EDIT("commands.edit"),
	COMMAND_REMOVE("commands.remove"),
	COMMAND_ENABLE("commands.enable"),
	COMMAND_INTERVAL("commands.interval"),
	COMMAND_EXPIRY("commands.expiry"),
	COMMAND_RANDOM("commands.random"),
	COMMAND_PREFIX("commands.prefix"),
	COMMAND_SUFFIX("commands.suffix"),
	COMMAND_BROADCAST("commands.broadcast"),
	COMMAND_LIST("commands.list"),
	NONE("");

	private String node;

	private Permission(String node) {
		this.node = node;
	}

	public String getNode() {
		return node;
	}

	public static String getPermission(Permission permission) {
		return "automessage." + permission.getNode();
	}
}
