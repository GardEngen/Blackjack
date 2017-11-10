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
            println("Player won")
        }
        else{
            println("Dealer won")
        }
        //Thread.sleep(2000) // allow user to see result
        //startRound()
    }

    /**
     * checks the cards, and determined if there is a winner
     * should be called every time someone draws, to see if they pop
     */
    private fun checkOver(): Boolean {
        if (rules.check21(table.player, table.dealer)){
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
            if (checkOver()){
                endRound(rules.getWinner(table.player, table.dealer)) // true = player won
            }
            return drewCard
        }
        return null
    }

    fun dealerHit(): Card? {
        //TODO call round over in main activity? then start new round from there if we dont want user to do it manually
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

    /**
     * Player stands, dealer draws if rules are satisfied,
     * returns the card drawn by dealer untill he stops drawing, then returns null
     */
    fun stand() : Card?{
        if (!roundover){
            if (checkOver()){
                endRound(rules.getWinner(table.player, table.dealer)) // true = player won
                return null
            }
            else{
                val under21 = rules.getScore(table.dealer) < 21
                val over16 = rules.getScore(table.dealer) > 16
                val drew = deck.draw()
                return if(under21 || over16){
                    table.dealCard(false, drew)
                    checkOver()
                    drew
                } else{
                    endRound(rules.getWinner(table.player, table.dealer))
                    null
                }
            }
        }
        return null
    }
    //TODO future
    fun split(){
        println("hei")
    }
    //TODO future
    fun double(){

    }
}