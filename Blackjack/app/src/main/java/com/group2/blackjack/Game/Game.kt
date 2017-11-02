package com.group2.blackjack.Game

import android.content.Context
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Entities.Deck
import com.group2.blackjack.Entities.Table

/**
 * Created by raugz on 11/2/2017.
 */
class Game {
    lateinit var rules : CardRules
    lateinit var table : Table
    lateinit var deck : Deck

    fun run(){
        initGame()
        startRound()
    }

    private fun startRound(){
        deck.reShuffle()

        //init hands 2 cards each
        for(i in 0..4){ // should do 0 to 3, might draw 5 if 4
            if (i%2 == 0){
                table.dealCard(true, deck.draw())
            }
            else{ // i%2 == 1
                table.dealCard(false, deck.draw())
            }
        }

        //TODO add entry money input from user
        val bet = 20
        table.placeBet(bet)
    }

    private fun initGame(){
        table = Table(500)
        rules = CardRules()
        deck = Deck()
    }

    private fun endRound(winner : Boolean){
        if (winner){
            val bet = table.currentBet
            table.addMoney(bet*2)
        }
        table.flushHands()
        Thread.sleep(2000) // allow user to see result
        startRound()
    }

    /**
     * checks the cards, and determined if there is a winner
     */
    private fun checkOver(player : List<Card>, dealer : List<Card>): Boolean {
        return rules.check21(player, dealer)
    }

    fun newGame(){

    }

    fun hit(){
        table.dealCard(true, deck.draw())
        if (checkOver(table.player, table.dealer)){ // true = someone has over 21 TODO fix real rules
            endRound(rules.getWinner(table.player, table.dealer)) // true = player won
        }
        else{
            table.dealCard(false, deck.draw())
        }
    }

    fun stand(){
        table.dealCard(false, deck.draw())
        if (checkOver(table.player, table.dealer)){ // true = someone has over 21 TODO fix real rules
            endRound(rules.getWinner(table.player, table.dealer)) // true = player won
        }
    }
    //TODO future
    fun split(){
        println("hei")
    }
    //TODO future
    fun double(){

    }
}