package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.group2.blackjack.Entities.Deck
import com.group2.blackjack.Game.Game
import com.group2.blackjack.R

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var hitButton : Button
    private lateinit var balance : TextView
    private lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button
        hitButton = findViewById(R.id.hitButton) as Button
        balance = findViewById(R.id.balanceText) as TextView
        buttonAction()

        game = Game(balance)
        game.run()
    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            game.split()
        }
        hitButton.setOnClickListener{
            game.hit()
        }

    }
}
