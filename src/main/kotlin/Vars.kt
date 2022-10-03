import arc.Core
import arc.files.Fi
import arc.util.Log
import com.google.gson.*

object Vars {
    lateinit var config: Config

    var discordId: Long = 0L

    var largeImageKey: String? = null
    var largeImageText: String? = null

    var smallImageKey: String? = null
    var smallImageText: String? = null

    lateinit var words: Array<String>
    lateinit var details: Array<String>

    var button: String? = null
    var link: String? = null
    var interval: Float = 1f

    var current = 0
    var currentDetails = 0

    fun init() {
        val file = Fi("config.json")

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .create()

        if (!file.exists()) {
            file.writeString(gson.toJson(Config()))
            Log.info("Config file generated: ${file.absolutePath()}")
            Core.app.exit()
        }

        config = gson.fromJson(file.reader(), Config::class.java)

        discordId = config.discordId

        largeImageKey = config.largeImageKey
        largeImageText = config.largeImageText

        smallImageKey = config.smallImageKey
        smallImageText = config.smallImageText

        words = config.words
        details = config.details

        button = config.button
        link = config.link

        interval = config.interval
    }
}