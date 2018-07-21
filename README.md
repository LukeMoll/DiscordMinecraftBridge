DiscordMinecraftBridge
===

This a [CraftBukkit] plugin that allows you to connect your Discord chat and Minecraft chat together! Messages sent in Minecraft will appear in Discord and vice-versa.  
In addition, messages sent from Discord will appear in Minecraft with a coloured username, which changes depending on who sent the message.
Similarly, messages sent from Minecraft will have the Minecraft player's head as the avatar.

## Installation
Download the JAR from the [Releases] page and put it in your `plugins/` folder in the CraftBukkit server folder.
Restart the server so the plugin generates its configuration files (which you're about to edit)

## Setup
Once you've done that, you'll need to create a new Discord application and bot user:  
1. Go to [My Applications](https://discordapp.com/developers/applications/me) on Discord.
2. Click **Create an application** and fill in the details.
3. Go to the **Bot** tab and click **Add Bot**, fill in the details there.
4. Copy the bot token and paste it into **botToken** in `plugins/Discord-Minecraft-Bridge/config.yml`.
5. Copy the **Client ID** from **General Information** and paste it into this URL, then go to it in a web browser: `https://discordapp.com/oauth2/authorize?client_id=<YOUR CLIENT ID HERE>&scope=bot` (the `< >` should NOT be in the URL)
6. Select your Discord server from the list and click **Add**. (You will need the **Manage Server** permission).
7. Now go to your Discord server, find the channel you want to link, and click the cog next to its name (**Edit channel**).
8. Go to **Webhooks** and click **Create Webhook**. Name it if you like, and copy the URL into **hookURL** in `config.yml`.
9. Restart the server and it should be working. Enjoy!

[CraftBukkit]: https://getbukkit.org/download/craftbukkit
[Releases]: https://github.com/LukeMoll/DiscordMinecraftBridge/releases