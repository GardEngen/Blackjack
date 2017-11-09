package com.group2.blackjack.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Game.Game
import com.group2.blackjack.R
import android.widget.RelativeLayout



class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var hitButton : Button
    private lateinit var balance : TextView
    private lateinit var game : Game
    private lateinit var startButton : Button
    private lateinit var standButton : Button
    private lateinit var cardLayout : RelativeLayout
    private lateinit var dealerLayout : RelativeLayout

    private var numbersOfPlayerHits : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        balance = findViewById(R.id.balanceText) as TextView
        startButton = findViewById(R.id.startButton) as Button
        standButton = findViewById(R.id.standButton) as Button
        cardLayout = findViewById(R.id.playerCardsLayout) as RelativeLayout
        dealerLayout = findViewById(R.id.dealerCardsLayout) as RelativeLayout
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
        standButton.setOnClickListener {
            val intent = Intent(this, HighscoreActivity::class.java)
            startActivity(intent)
        }

        //HIT
        hitButton.setOnClickListener{
            val playerDraw = game.playerHit() // can be null
            if(playerDraw != null){
                numbersOfPlayerHits++
                setImageToScreen(game.table.player, numbersOfPlayerHits, cardLayout,true)
            }
        }
        //START
        startButton.setOnClickListener{
            cardLayout.removeAllViews()
            dealerLayout.removeAllViews()
            numbersOfPlayerHits = 1

            game.startRound()
            val table = game.table

            //TODO clean this up, add dealer cards, have initial cards show
            for(i in 0..1){
                setImageToScreen(table.player, i, cardLayout,true)
            }

            for(i in 0..1){
                //draw backside card
                setImageToScreen(table.dealer, i, dealerLayout,true)
                if(i == 1){
                    setImageToScreen(table.dealer, i+1, dealerLayout,false)
                }
            }
        }
    }

    private fun setImageToScreen(cards : List<Card>, i: Int, layout: RelativeLayout, moveCard: Boolean) {
        val cardString = cards[i].color.toChar().toString() + cards[i].value
        var imgView = ImageView(this)
        val id = resources.getIdentifier(cardString, "drawable", packageName)
        imgView.setImageResource(id)

        if(i > 0){
            var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            if(moveCard){
                params.leftMargin = (i*70)
            } else {
                params.leftMargin = ((i-1)*70)
            }
            imgView.layoutParams = params
        }
        layout.addView(imgView, i)
    }
}
