package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.R
import android.widget.RelativeLayout
import com.group2.blackjack.Game.GameJV

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var hitButton : Button
    private lateinit var balance : TextView
    private lateinit var game : GameJV
    private lateinit var startButton : Button
    private lateinit var standButton : Button
    private lateinit var cardLayout : RelativeLayout
    private lateinit var dealerLayout : RelativeLayout
    private var numbersOfPlayerHits : Int = 0
    private var numbersOfDealerHits = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        standButton = findViewById(R.id.standButton) as Button
        balance = findViewById(R.id.balanceText) as TextView
        startButton = findViewById(R.id.startButton) as Button
        standButton = findViewById(R.id.standButton) as Button
        cardLayout = findViewById(R.id.playerCardsLayout) as RelativeLayout
        dealerLayout = findViewById(R.id.dealerCardsLayout) as RelativeLayout
        buttonAction()


        //TODO add prompt for bet input
        game = GameJV(balance)
        game.initGame()
        //game.startRound()
        //continue round

    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            game.split()
        }
        standButton.setOnClickListener{
            var card = game.stand()
            //TODO fix crashes game
            if(card != null){
                numbersOfDealerHits++
                setImageToScreen(game.table.dealer, numbersOfDealerHits, dealerLayout, true)
            }
            while(card != null){
                card = game.stand()
                if (card != null){
                    setImageToScreen(game.table.dealer, numbersOfDealerHits, dealerLayout, true)
                }
            }
        }

        //HIT
        hitButton.setOnClickListener {
            val playerDraw = game.playerHit() // can be null
            println(playerDraw)
            if (playerDraw != null) {
                numbersOfPlayerHits++
                setImageToScreen(game.table.player, numbersOfPlayerHits, cardLayout, false)
            }
        }

        //START
        startButton.setOnClickListener{
            cardLayout.removeAllViews()
            dealerLayout.removeAllViews()
            numbersOfPlayerHits = 1

            game.startRound()
            val table = game.table

            for(i in 0..1){
                setImageToScreen(table.player, i, cardLayout,false)
            }

            for(i in 0..1){
                //draw backside card
                if(i == 1){
                    setImageToScreen(table.dealer, i, dealerLayout,true)
                }
                else{
                    setImageToScreen(table.dealer, i, dealerLayout,false)
                }
            }
        }
    }

    private fun setImageToScreen(cards : List<Card>, i: Int, layout: RelativeLayout, moveCard: Boolean) {
        val cardString = cards[i].toString()

        var imgView = ImageView(this)
        val id = resources.getIdentifier(cardString, "drawable", packageName)
        if(!moveCard) {
            imgView.setImageResource(id)
        }
            //
            var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.leftMargin = (i*70)
        if(moveCard){

                imgView.setImageResource(R.drawable.b0)
            }
            imgView.layoutParams = params

        layout.addView(imgView, i)
    }
}
