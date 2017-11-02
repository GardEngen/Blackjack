package com.group2.blackjack.Game

import android.content.Context
import com.group2.blackjack.Entities.Card

/**
 * Created by raugz on 11/2/2017.
 */
class Game {
    lateinit var rules : CardRules
    fun run(){
        initGame()
        rules = CardRules()
        startRound()
    }

    fun startRound(){
        //TODO entrance fee
        //TODO deal cards
    }

    fun initGame(){

    }

    /**
     * checks the cards, and determined if there is a winner
     */
    fun checkOver(player : List<Card>, dealer : List<Card>): Boolean {
        return rules.check21(player, dealer)
    }

    fun newGame(){

    }

    fun hit(){

    }

    fun stand(){

    }

    fun split(){
        println("hei")
    }

    fun double(){

    }
}