package RestAPI

import Entities.Highscore
import Repository.HighscoreRepository
import com.google.gson.Gson
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.content.readText
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.json


fun Application.main() {
    val gson = Gson()
    val highscoreRepo = HighscoreRepository()

    highscoreRepo.initDatabaseClient()
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {

        post("/postScore") {
            val highscore = gson.fromJson(call.request.receiveContent().readText(), Highscore::class.java)
            highscoreRepo.postHighScore(highscore)
        }

        get("/top10") {
            val top10 = highscoreRepo.getTop10()
            call.respondText(gson.toJson(top10), ContentType.Application.Json)
        }

    }
}