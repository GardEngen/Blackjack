package com.group2.blackjack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splitButton = findViewById(R.id.splitButton)


        splitButton.setOnClickListener{
            println("hei")
        }

    }
}
