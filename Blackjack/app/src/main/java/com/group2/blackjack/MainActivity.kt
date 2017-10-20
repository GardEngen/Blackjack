package com.group2.blackjack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var splitButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitButton = findViewById(R.id.splitButton) as Button



        buttonAction();

    }

    private fun buttonAction() {
        splitButton.setOnClickListener{
            println("hei")
        }
    }
}
