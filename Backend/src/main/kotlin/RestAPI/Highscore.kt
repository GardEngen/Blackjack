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
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post


fun Application.main() {
    val gson = Gson()
    val highscoreRepo = HighscoreRepository()

    highscoreRepo.initDatabaseClient()
    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {

        post("/postScore") {
            val highscore = gson.fromJson(call.request.receiveContent().readText(), Highscore::class.java)
            if(highscore == null || highscore.score == 0 || highscore.name.equals("")){
                call.respond(HttpStatusCode.BadRequest,"The request is missing some information")
                return@post
            }
            val top10 = highscoreRepo.getTop10();
            if (top10.size < 10 ) {
                highscoreRepo.insertHighscore(highscore)
                call.respond(HttpStatusCode.OK)
            } else {
                for (item in top10) {
                    if (highscore.score >= item.score) {
                        highscoreRepo.insertHighscore(highscore);
                        highscoreRepo.deleteHighscore(top10.first())
                        call.respond(HttpStatusCode.OK)
                        return@post
                    } else {
                        println("du har ikke høy nok score for å komme på toplisten")
                        call.respond(HttpStatusCode.OK,"Your scare was not good enough to make the highscore")
                        return@post
                    }
                }
            }
            call.respond(HttpStatusCode.BadRequest,"Something went wrong")
        }

        get("/top10") {
            val top10 = highscoreRepo.getTop10()
            call.respondText(gson.toJson(top10), ContentType.Application.Json)
        }
    }
}