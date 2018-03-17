package xyz.lukemoll.discordminecraftbridge;

import java.util.*;

public class AvatarProviders {

	private static Map<String,IAvatarProvider> PROVIDERS = null;
	
	public static final String DEFAULT_PROVIDER = "visage:face";
	
	public static Map<String,IAvatarProvider> getProviders() {
		if(PROVIDERS != null)
			return PROVIDERS;
		else {
			Map<String,IAvatarProvider> providers = new HashMap<String,IAvatarProvider>();
			
				/* In this naming convention:
				 * 
				 * Face:	2D head only	
				 * Head:	3D head only
				 * Front:	2D head + shoulders
				 * Bust		3D head + shoulders
				 */
				providers.put("crafatar:face", 	(u,n) -> "https://crafatar.com/avatars/" + u + "?overlay&default=MHF_Steve&size=256");
				providers.put("crafatar:head", 	(u,n) -> "https://crafatar.com/renders/head/" + u + "?overlay&default=MHF_Steve&scale=10");
				providers.put("visage:face", 	(u,n) -> "https://visage.surgeplay.com/face/256/" + u + ".png");
				providers.put("visage:front", 	(u,n) -> "https://visage.surgeplay.com/front/256/" + u + ".png");
				providers.put("visage:head", 	(u,n) -> "https://visage.surgeplay.com/head/256/" + u + ".png");
				providers.put("visage:bust", 	(u,n) -> "https://visage.surgeplay.com/bust/256/" + u + ".png");
				providers.put("minotar:face", 	(u,n) -> "https://minotar.net/helm/" + n + "/256.png");
				providers.put("minotar:head", 	(u,n) -> "https://minotar.net/cube/" + n + "/256.png");
				providers.put("minotar:front", 	(u,n) -> "https://minotar.net/armor/bust/" + n + "/256.png");
			
			PROVIDERS = providers;
			return Collections.unmodifiableMap(providers);
		}
	}
	
	public static IAvatarProvider getAvatarProvider(String key) throws ProviderNotFoundException {
		IAvatarProvider p = getProviders().get(key);
		if(p==null)
			throw new ProviderNotFoundException("No provider with key '" + key + "' found"); 
		else
			return p; 
	}
	
	public static IAvatarProvider getDefaultAvatarProvider() {
		return getProviders().get(DEFAULT_PROVIDER);
	}
	
	static class ProviderNotFoundException extends Exception {
		public ProviderNotFoundException(String msg) {
			super(msg);
		}
	}
	
}
