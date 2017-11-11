package com.group2.blackjack.Communication

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.group2.blackjack.Entities.Highscore
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

/**
 * Created by Gard on 09.11.2017.
 */
class RestClient() {
    private var ip = "10.111.48.32"
    private var port = "5000"
    private var fullUrl = "http://" + ip + ":" + port + "/"

    fun postScore(name: String, score: Int) {
        val json = JSONObject()
        json.put("name", name)
        json.put("score", score)

        Fuel.post(fullUrl + "postScore")
                .header("Content-Type" to "application/json")
                .body(json.toString())
                .response { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val jsonInString = String(response.data)
                            println("FAIL MESSAGEEEE::: " + jsonInString + response.responseMessage)
                        }
                        is Result.Success -> {
                            val jsonInString = String(response.data)
                            println("SUCCESS MESSAGEEEE::: " + jsonInString + response.responseMessage)
                        }
                    }
                }
    }

    fun getTop10() : Array<Highscore>? {
        var returnList : Array<Highscore>? = null
        Fuel.get(fullUrl + "top10")
                .response  { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val jsonInString = String(response.data)
                            println("FAIL MESSAGEEEE::: " + jsonInString + response.responseMessage)
                        }
                        is Result.Success -> {
                            var gson = Gson()
                            val jsonInString = String(response.data)
                            println("SUCCESS MESSAGEEEE::: " + jsonInString + response.responseMessage)
                            returnList =  gson.fromJson(jsonInString, Array<Highscore>::class.java)
                        }
                    }
                }
        return returnList
    }
}