package RestAPI
import com.google.gson.Gson
import Entities.Highscore
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import java.util.Arrays
import com.mongodb.client.MongoDatabase
import com.mongodb.client.MongoCollection
import org.litote.kmongo.findOne
import org.litote.kmongo.json
import org.litote.kmongo.*


fun Application.main() {
    val gson = Gson()

    val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
    val database = client.getDatabase("blackjackdb") //normal java driver usage
    val collection = database.getCollection<Highscore>() //KMongo extension method

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        get("/") {

            val doktor = Highscore(null,"Gard",400)
            val json = gson.toJson(doktor)
            call.respondText(json, ContentType.Application.Json)
        }

        post("/postScore"){


          //  collection.insertOne(doktor)
        }

        get("/top10"){

            val doktor = Highscore(null,"tessst5",449)
            //database.getCollection<Highscore>().insertOne(doktor)
            collection.insertOne(doktor)
            println(collection.find().forEach {
                println(it)
            })



        }

    }
}