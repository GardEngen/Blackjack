package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Entities.Table
import com.group2.blackjack.Enums.Color
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        standButton = findViewById(R.id.standButton) as Button
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
            var cardLayout = findViewById(R.id.cardLayout) as RelativeLayout
            val playerDraw = game.playerHit() // can be null
            val dealerDraw = game.dealerHit() // can be null
            println(playerDraw.toString())
            //var imgView = ImageView(this)
            if(playerDraw != null){
                var cardNumber = 1
                cardNumber++
                setImageToScreen(game.table.player, cardNumber, cardLayout)
            }
            //imgView.setImageResource(R.drawable.c6)
            //cardLayout.addView(imgView)
        }
        standButton.setOnClickListener{
            var card = game.stand()
            //TODO send card to GUI
            while(card != null){
                card = game.stand()
                if (card != null){
                    //TODO send to GUI
                }
            }
        }

        startButton.setOnClickListener{
            var cardLayout = findViewById(R.id.cardLayout) as RelativeLayout
            cardLayout.removeAllViews()
            val dealerLayout = findViewById(R.id.dealerCardsLayout) as RelativeLayout
            dealerLayout.removeAllViews()
            game.startRound()
            val table = game.table

            //TODO clean this up, add dealer cards, have initial cards show
            for(i in 0..1){
                setImageToScreen(table.player, i, cardLayout)
            }

            for(i in 0..1){
                setImageToScreen(table.dealer, i, dealerLayout)
            }

        }
    }

    private fun setImageToScreen(cards : List<Card>, i: Int, layout: RelativeLayout) {
        val cardString = cards[i].toString()

        var imgView = ImageView(this)
        //imgView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        val id = resources.getIdentifier(cardString, "drawable", packageName)
        imgView.setImageResource(id)

        //TODO fix image positions
        //imgView.layoutParams

        if(i > 0){
            var params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            params.addRule(RelativeLayout.RIGHT_OF, i-1)
            imgView.layoutParams = params
        }

        layout.addView(imgView, i)
    }
}
