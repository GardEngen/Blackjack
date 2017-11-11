package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.group2.blackjack.Adapters.HighscoreAdapter
import com.group2.blackjack.Callbacks.AsyncResponseCallback
import com.group2.blackjack.Communication.RestClient
import com.group2.blackjack.Entities.Highscore

import com.group2.blackjack.R

class HighscoreActivity : AppCompatActivity(),AsyncResponseCallback {
    var restClient = RestClient()
    private lateinit var highscoreLV : ListView


    override fun getTop10FinishProcess(output: Array<Highscore>) {
        println("resultatet kommer herr")
        println(output.size)
        var highscoreAdapter = HighscoreAdapter(this,R.layout.highscore_listview_item,output)
        highscoreLV.adapter = highscoreAdapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        highscoreLV = findViewById(R.id.highscoreLV) as ListView

        restClient.aCallback = this
        restClient.getTop10()


    }
}
