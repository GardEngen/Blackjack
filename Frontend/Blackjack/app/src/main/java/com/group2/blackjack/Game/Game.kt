package com.group2.blackjack.Game

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Entities.Deck
import com.group2.blackjack.Entities.Table

/**
 * Created by raugz on 11/2/2017.
 */
class Game constructor(tv : TextView){
    var uriPath = "@drawable/"
    var balanceText = tv
    private var roundover = false
    lateinit var rules : CardRules
    lateinit var table : Table
    lateinit var deck : Deck


    fun startRound(){
        roundover = false
        deck.reShuffle()
        table.flushHands()


        //init hands 2 cards each
        for(i in 0..3){ // draws 0 to 3, 4 cards
            if (i%2 == 0){
                val drew = deck.draw()
                table.dealCard(true, drew)
            } else { // i%2 == 1
                val drew = deck.draw()

                table.dealCard(false, drew)

                //back card
                if(i == 3){
                    table.dealCard(false, deck.drawBackCard())
                }
            }
        }

        //TODO add entry money input from user
        val bet = 20
        table.placeBet(bet)
        balanceText.text = table.money.toString()
    }

    fun initGame(){
        table = Table(500)
        rules = CardRules()
        deck = Deck()
    }

    private fun endRound(winner : Boolean){
        if (winner){
            val bet = table.currentBet
            table.addMoney(bet*2)
        }
        //Thread.sleep(2000) // allow user to see result
        //startRound()
    }

    /**
     * checks the cards, and determined if there is a winner
     */
    private fun checkOver(): Boolean {
        if (rules.check21(table.player, table.dealer)){ // TODO check correct
            roundover = true
            return true
        }
        return false
    }

    fun newGame(){
        //unused
    }

    fun playerHit(): Card? {
        if (!roundover){
            val drewCard = deck.draw()
            table.dealCard(true, drewCard)
            if (checkOver()){ // true = someone has over 21 TODO fix real rules
                endRound(rules.getWinner(table.player, table.dealer)) // true = player won
            }
            return drewCard
        }
        return null
    }

    fun dealerHit(): Card? {
        if (!roundover){
            if(rules.getScore(table.dealer) < 17){
                val drewCard = deck.draw()
                table.dealCard(false, drewCard)
                if (checkOver()){ // true = someone has over 21 TODO fix real rules
                    endRound(rules.getWinner(table.player, table.dealer)) // true = player won
                }
                return drewCard
            }
        }
        return null
    }

    fun stand(){
        if (!roundover){
            if (checkOver()){ // true = someone has over 21 TODO fix real rules
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
    }
    //TODO future
    fun split(){
        println("hei")
    }
    //TODO future
    fun double(){

    }
}