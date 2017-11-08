package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Entities.Table
import com.group2.blackjack.Enums.Color
import com.group2.blackjack.Game.Game
import com.group2.blackjack.R

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var hitButton : Button
    private lateinit var balance : TextView
    private lateinit var game : Game
    private lateinit var startButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        balance = findViewById(R.id.balanceText) as TextView
        startButton = findViewById(R.id.startButton) as Button
        buttonAction()


        //TODO add prompt for bet input
        game = Game(balance)
        game.initGame()
        //game.startRound()
        //continue round

    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            game.split()
        }
        hitButton.setOnClickListener{
            var cardLayout = findViewById(R.id.cardLayout) as LinearLayout
            val playerDraw = game.playerHit() // can be null
            val dealerDraw = game.dealerHit() // can be null
            var imgView = ImageView(this)
            imgView.setImageResource(R.drawable.c6)
            cardLayout.addView(imgView)
        }

        startButton.setOnClickListener{
            var cardLayout = findViewById(R.id.cardLayout) as LinearLayout
            cardLayout.removeAllViews()
            val dealerLayout = findViewById(R.id.dealerCardsLayout) as LinearLayout
            dealerLayout.removeAllViews()
            game.startRound()
            val table = game.table

            //TODO clean this up, add dealer cards, have initial cards show
            for(i in 0..1){
                setImageToScreen(table.player, i, cardLayout, true)
            }

            for(i in 0..1){
                setImageToScreen(table.dealer, i, dealerLayout, false)
            }

        }
    }

    private fun setImageToScreen(cards : List<Card>, i: Int, layout: LinearLayout, player : Boolean) {
        val cardString : String
        if(player){
            cardString = cards[i].color.toChar().toString() + cards[i].value
        }
        else{
            cardString = cards[i].color.toChar().toString() + cards[i].value
        }
        var imgView = ImageView(this)
        imgView.x = (i * 100).toFloat()
        val id = resources.getIdentifier(cardString, "drawable", packageName)

        imgView.setImageResource(id)
        layout.addView(imgView)
    }

}
