package com.group2.blackjack.Communication

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

/**
 * Created by Gard on 09.11.2017.
 */
class RestClient() {
    var ip = "10.111.48.32"
    var port = "5000"
    var fullUrl = "http://" + ip + ":" + port + "/"


    fun postScore(){

    }

    fun getTop10(){
        (fullUrl +"top10").httpGet().responseString { request, response, result ->
            //do something with response
            when (result) {
                is Result.Failure -> {
                    //error = result.getAs()
                    val jsonInString = String(response.data)
                    println("FAIL MESSAGEEEE::: " + jsonInString +  response.responseMessage)
                }
                is Result.Success -> {
                    //val data = result.getAs()
                    val jsonInString = String(response.data)
                    println("SUCCESS MESSAGEEEE::: " + jsonInString +  response.responseMessage)
                }
            }
        }

    }
}