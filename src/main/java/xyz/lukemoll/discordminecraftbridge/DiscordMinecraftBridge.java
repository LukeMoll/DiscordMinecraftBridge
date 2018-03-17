package xyz.lukemoll.discordminecraftbridge;

import java.util.logging.Logger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DiscordMinecraftBridge extends JavaPlugin implements Listener {

	private Logger log = this.getLogger();
	
	@Override
	public void onEnable() {
		log.info("onEnable()");
		getServer().getPluginManager().registerEvents(this, this);
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
	}
	
}
