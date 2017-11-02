package com.group2.blackjack.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.group2.blackjack.Entities.Deck
import com.group2.blackjack.Game.Game
import com.group2.blackjack.R

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button
    private lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game()

        splitButton = findViewById(R.id.splitButton) as Button

        buttonAction();

    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            game.split()

        }
    }
}
