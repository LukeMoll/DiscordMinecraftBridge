package xyz.lukemoll.discordminecraftbridge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import okhttp3.*;

public class DiscordWebhook {

	private final URL hook_URL;
	
	private final String ID;
	private final String TOKEN; // Don't need to expose token yet
	
	private final static Pattern hook_pattern = Pattern.compile("discordapp.com/api/webhooks/(.*?)/(.*)$", Pattern.CASE_INSENSITIVE);
	
	public DiscordWebhook(String url) throws MalformedURLException {
		this.hook_URL = new URL(url);
		Matcher m = hook_pattern.matcher(url);
		m.find();
		this.ID = m.group(1);
		this.TOKEN = m.group(2);
	}
	
	public String send(String message, String avatar_url, String username) {
		JSONObject obj = new JSONObject();
		obj.put("content", message);
		if(avatar_url != null)
			obj.put("avatar_url", avatar_url);
		if(username != null)
			obj.put("username", username);	

		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(MediaType.parse("application/json"), obj.toString());
		Request request = new Request.Builder()
		.url(hook_URL.toString())
		.post(body)
		.addHeader("Content-Type", "application/json")
		.addHeader("Cache-Control", "no-cache")
		.build();

		try {
			Response response = client.newCall(request).execute();
			return response.code() + ": " + response.message();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "IOException";
	}
	
	public String send(String message) {
		return send(message, null, null);
	}
	
	public String getID() {return ID;}

}
