package xyz.lukemoll.discordminecraftbridge;

import java.net.MalformedURLException;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordMinecraftBridge extends JavaPlugin implements Listener {

	private Logger log = this.getLogger();
	private DiscordWebhook hook;
	
	
	@Override
	public void onEnable() {
		log.info("onEnable()");
		getServer().getPluginManager().registerEvents(this, this);
		try {
			hook = new DiscordWebhook("webhook URL");
			hook.send("Enabled!");
		} catch (MalformedURLException e) {
			// Add load failure here
			log.warning("MalformedURLException");
		}
	}
	
	@Override
	public void onDisable() {
		log.info("onDisable();");
		// Do we need to deregister events?
	}
	
	
	/******************** Event handlers **********************/
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		log.info("--> " + event.getPlayer().getDisplayName());
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		log.info(event.getPlayer().getDisplayName()+": "+event.getMessage());
		String res = hook.send(String.format(event.getFormat(), event.getPlayer().getDisplayName(), event.getMessage()));
		log.info(res);
	}
	
}
