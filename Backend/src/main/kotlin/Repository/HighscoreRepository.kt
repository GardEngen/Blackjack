package Repository

import Entities.Highscore
import com.mongodb.client.MongoCollection
import org.litote.kmongo.KMongo
import org.litote.kmongo.deleteOne
import org.litote.kmongo.getCollection
import org.litote.kmongo.json


class HighscoreRepository {
    private var collection: MongoCollection<Highscore>? = null


    fun initDatabaseClient() {
        val client = KMongo.createClient()
        val database = client.getDatabase("blackjackdb")
        collection = database.getCollection<Highscore>()
    }

    fun insertHighscore(highscore: Highscore) {
        if(collection != null) {
            collection!!.insertOne(highscore);
        }
    }

    fun deleteHighscore(highscore: Highscore) {
        if(collection != null) {
            collection!!.deleteOne(highscore.json)
        }
    }

    fun getTop10(): ArrayList<Highscore> {
        var top10 = ArrayList<Highscore>()
        if(collection != null) {
            collection!!.find().forEach { top10.add(it) }
            top10 = ArrayList(top10.sortedWith(compareBy { it.score }))
        }
        return top10;
    }
}