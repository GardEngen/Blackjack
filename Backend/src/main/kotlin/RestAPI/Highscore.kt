package RestAPI

import Entities.Highscore
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

    val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
    val database = client.getDatabase("blackjackdb") //normal java driver usage
    val collection = database.getCollection<Highscore>() //KMongo extension method

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {

            val doktor = Highscore("Gard", 400)
            val json = gson.toJson(doktor)
            call.respondText(json, ContentType.Application.Json)
        }

        post("/postScore") {
            println("JEG KOMMER INN HEEERRR")
            val highscore = gson.fromJson(call.request.receiveContent().readText(), Highscore::class.java)

            val top10 = ArrayList<Highscore>()
            collection.find().forEach { top10.add(it) }
            if (top10.size == 0) {
                //add score
                println("Det finnes ingen item i liste")
                collection.insertOne(highscore);
                println("har lagt til " + highscore)
            }

            val sortedList = top10.sortedWith(compareBy { it.score })

            for (item in sortedList) {
                if (sortedList.size < 10) {
                    println("Det er mindre enn 10 ")
                    collection.insertOne(highscore);
                    break

                } else if (highscore.score >= item.score) {

                    //add score
                    println("score var større enn " + item.score)
                    collection.insertOne(highscore);
                    println("har lagt til " + highscore)
                    //delete lowest score
                    println("skal slette : " + sortedList.first().json)
                    collection.deleteOne(sortedList.first().json)
                    println("stopper loop")
                    break
                } else {
                    println("du har ikke høy nok score for å komme på toplisten")
                    break
                }
            }
        }

        get("/top10") {

            val top10 = ArrayList<Highscore>()
            collection.find().forEach { top10.add(it) }
            val sortedList = top10.sortedWith(compareBy { it.score })
            call.respondText(gson.toJson(sortedList), ContentType.Application.Json)
        }

    }
}