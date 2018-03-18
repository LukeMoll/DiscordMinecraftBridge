package xyz.lukemoll.discordminecraftbridge;

import org.bukkit.Server;
import org.json.JSONArray;
import org.json.JSONObject;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.util.DiscordException;

public class DiscordListener implements IListener<MessageEvent> {
	
	private IDiscordClient client;
	private Server server;
	private DiscordWebhook hook;
	
	public DiscordListener(String token, Server server, DiscordWebhook hook) throws DiscordException {
		ClientBuilder clientBuilder = new ClientBuilder(); // Creates the ClientBuilder instance
        clientBuilder.withToken(token); // Adds the login info to the builder
        client = clientBuilder.login();
        EventDispatcher d = client.getDispatcher();
        d.registerListener(this);
        
        this.server = server;
        this.hook = hook;
	}
	
	public void destroy() {
		client.logout();
	}

	@Override
	public void handle(MessageEvent arg0) {
		JSONArray arr = new JSONArray();
		JSONObject sender = new JSONObject();
			String username = arg0.getAuthor().getDisplayName(arg0.getGuild());
			sender.put("text", "<" + username + "> ");
			sender.put("color", getColor(username));
			sender.put("bold", true);
		arr.put(sender);
		JSONObject message = new JSONObject();
			message.put("text", arg0.getMessage());
			message.put("color", "white");
			message.put("bold", false);
		arr.put(message);
			
		String json = arr.toString();
		server.dispatchCommand(server.getConsoleSender(), "tellraw @a " + json);	
	}
	
	private static final String[] colors = {"dark_green", "dark_aqua", "dark_red", "dark_purple", "gold", "blue", "green", "aqua", "red", "light_purple", "yellow"};
	private static String getColor(String username) {
		return colors[Math.abs(djb2(username))%colors.length];
	}
	
	private static int djb2(String input) {
		input = input.toLowerCase();
		int hash = 5381;
		for(char c : input.toCharArray()) {
			hash = ((hash << 5) + hash) + (int) c;
		}
		return hash;
	}
		
}
