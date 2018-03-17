package xyz.lukemoll.discordminecraftbridge;

import java.net.MalformedURLException;
import java.util.logging.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import xyz.lukemoll.discordminecraftbridge.AvatarProviders.ProviderNotFoundException;

public class DiscordMinecraftBridge extends JavaPlugin implements Listener {

	private Logger log = this.getLogger();
	private DiscordWebhook hook;
	private IAvatarProvider avProvider;
	
	@Override
	public void onEnable() {
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
		
		try {
			hook = new DiscordWebhook(getConfig().getString("discord.hookURL"));
//			hook.send("Enabled!"); // Either give this a better username & avatar or remove it.
		} catch (MalformedURLException e) {
			log.warning("MalformedURLException: Have you added your hook URL in config.yml?");
			log.warning("Plugin will not be active until hook URL is valid.");
			this.setEnabled(false);
		}
		
		try {
			this.avProvider = AvatarProviders.getAvatarProvider(getConfig().getString("minecraft.avatarProvider"));
		}
		catch(ProviderNotFoundException e) {
			log.warning(getConfig().getString("minecraft.avatarProvider") + " is not a valid avatar provider. " + AvatarProviders.DEFAULT_PROVIDER + " will be used as a fallback.");
			this.avProvider = AvatarProviders.getDefaultAvatarProvider();
		}
	}
	
	@Override
	public void onDisable() {
		hook = null;
		// Do we need to deregister events?
	}
	
	
	/******************** Event handlers **********************/
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		log.info("--> " + event.getPlayer().getDisplayName());
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(hook!=null) {
			String avatar = avProvider.getAvatar(event.getPlayer().getUniqueId().toString(),
												 event.getPlayer().getDisplayName());
			hook.send(event.getMessage(),
					  avatar,
					  event.getPlayer().getDisplayName());
		}
	}
	
}
