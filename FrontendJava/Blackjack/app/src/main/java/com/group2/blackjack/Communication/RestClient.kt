package com.group2.blackjack.Communication

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.result.Result
import org.json.JSONObject

/**
 * Created by Gard on 09.11.2017.
 */
class RestClient() {
    var ip = "10.111.48.32"
    var port = "5000"
    var fullUrl = "http://" + ip + ":" + port + "/"

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

    fun getTop10() {
        Fuel.get(fullUrl + "top10")
                .responseJson { request, response, result ->
                    when (result) {
                        is Result.Failure -> {
                            val jsonInString = response.data
                            println("FAIL MESSAGEEEE::: " + jsonInString + response.responseMessage)
                        }
                        is Result.Success -> {
                            val jsonInString = response.data
                            println("SUCCESS MESSAGEEEE::: " + jsonInString + response.responseMessage)
                        }
                    }
                }
    }
}