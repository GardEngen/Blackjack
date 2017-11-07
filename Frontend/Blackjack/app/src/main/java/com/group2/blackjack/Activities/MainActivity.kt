package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.group2.blackjack.Game.Game
import com.group2.blackjack.R

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var hitButton : Button
    private lateinit var balance : TextView
    private lateinit var game : Game
    private lateinit var pic : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        balance = findViewById(R.id.balanceText) as TextView
        pic = findViewById(R.id.leftCard) as ImageView
        buttonAction()


        //TODO add prompt for bet input
        game = Game(balance, pic)
        game.initGame()
        game.startRound()
    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            game.split()
        }
        hitButton.setOnClickListener{
            var cardLayout = findViewById(R.id.cardLayout) as LinearLayout
            val playerDraw = game.playerHit()
            val dealerDraw = game.dealerHit() // can be null
            var imgView = ImageView(this)
            imgView.setImageResource(R.drawable.c6)
            cardLayout.addView(imgView)
        }

    }
}
