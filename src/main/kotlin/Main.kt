import arc.*
import arc.backend.headless.HeadlessApplication
import arc.discord.DiscordRPC
import arc.discord.DiscordRPC.RichPresence
import arc.util.*

fun main() {
    HeadlessApplication(CustomRPC(), Log::err)
}

class CustomRPC : ApplicationListener {
    override fun init() {
        try {
            Vars.init()

            DiscordRPC.connect(Vars.discordId)
            Log.info("Initialized Discord rich presence.")
            Runtime.getRuntime().addShutdownHook(Thread { DiscordRPC.close() })

            if (Vars.interval <= 0) Vars.interval = 1f
            loop()
        } catch (e: Exception) {
            Log.err("Failed to initialize Discord RPC: $e")
            Core.app.exit()
        }
    }

    private fun loop() {
        val presence = RichPresence()

        if (!Vars.button.isNullOrEmpty()) {
            presence.label1 = Vars.button

            if (!Vars.link.isNullOrEmpty()) {
                presence.url1 = Vars.link
            }
        }

        val word: String = Vars.words[Vars.current]
        if (++Vars.current == Vars.words.size) {
            Vars.current = 0
        }

        presence.state = word

        val details: String = Vars.details[Vars.currentDetails]
        if (++Vars.currentDetails == Vars.details.size) {
            Vars.currentDetails = 0
        }

        presence.details = details

        if (!Vars.largeImageKey.isNullOrEmpty()) {
            presence.largeImageKey = Vars.largeImageKey

            if (!Vars.largeImageText.isNullOrEmpty()) {
                presence.largeImageText = Vars.largeImageText
            }
        }

        if (!Vars.smallImageKey.isNullOrEmpty()) {
            presence.smallImageKey = Vars.smallImageKey

            if (!Vars.smallImageText.isNullOrEmpty()) {
                presence.smallImageText = Vars.smallImageText
            }
        }

        DiscordRPC.send(presence)
        Timer.schedule({ loop() }, Vars.interval)
    }
}