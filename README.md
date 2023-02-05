# StreamerVaro

A Plugin for Streamer [fvlower](https://www.twitch.tv/fvlower) to play the well known youtube project [Varo](https://youtube.fandom.com/de/wiki/Minecraft_VARO).

---

## **Disclaimer**

Not everything in this Plugin is tested, use at your own risk!

---

## Dependencies

+ [MaxAPI](https://github.com/maxbossing/MaxAPI)

## Soft Dependencies

+ [PlayerTimeLimit](https://www.spigotmc.org/resources/playertimelimit-1-8-1-19.96577/)
  > Im not gonna Implement playtime accounting, so you should use this if you want it

---

## How to use

1. Get the newest 1.19 [PurpurMC](https://purpurmc.org/) build and setup the Server
     + [Paper](https://papermc.io/) Should work too, but it is not tested nor recommended
2. Get the Plugins
     + [MaxAPI](https://github.com/maxbossing/MaxAPI/releases)
     + [StreamerVaro](https://github.com/maxbossing/StreamerVaro/releases)
     + (Optional) [Luckperms](https://luckperms.net/download)
     + (Optional) [PlayerTimeLimit](https://www.spigotmc.org/resources/playertimelimit-1-8-1-19.96577/)
3. Start the Server to generate the configs
4. edit config.yml to your liking
     + > maxstrikes are the maximum allowed strikes a player can have before getting banned
5. The players can use /team to invite other players in their team, and /leaveteam to disband the team
6. if everything is ready, use /startgame to start
7. after the session, use /stopgame to stop
