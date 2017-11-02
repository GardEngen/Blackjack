package com.group2.blackjack.Game

import android.content.Context
import android.widget.TextView
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Entities.Deck
import com.group2.blackjack.Entities.Table

/**
 * Created by raugz on 11/2/2017.
 */
class Game constructor(tv : TextView){
    var balanceText = tv
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
        for(i in 0..3){ // draws 0 to 3, 4 cards
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
        balanceText.text = table.money.toString()
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
        //Thread.sleep(2000) // allow user to see result
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
        //println("hittttttt")
        table.dealCard(true, deck.draw())
        if (checkOver(table.player, table.dealer)){ // true = someone has over 21 TODO fix real rules
            endRound(rules.getWinner(table.player, table.dealer)) // true = player won
        }
        else{ // dealers turn
            if(rules.getScore(table.dealer) < 17){
                table.dealCard(false, deck.draw())
            }
        }
    }

    fun stand(){
        if (checkOver(table.player, table.dealer)){ // true = someone has over 21 TODO fix real rules
            endRound(rules.getWinner(table.player, table.dealer)) // true = player won
        }
        else{
            val lessThanPlayer = rules.getScore(table.dealer) < rules.getScore(table.player)
            val under21 = rules.getScore(table.dealer) < 21
            while(lessThanPlayer || under21){
                table.dealCard(false, deck.draw())
            }
            endRound(rules.getWinner(table.player, table.dealer))
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