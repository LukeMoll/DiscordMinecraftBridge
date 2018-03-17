package xyz.lukemoll.discordminecraftbridge;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import okhttp3.*;

public class DiscordWebhook {

	private final URL hook_URL;
	
	public DiscordWebhook(String url) throws MalformedURLException {
		this.hook_URL = new URL(url);
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

}
