package com.group2.blackjack.Game

import com.group2.blackjack.Entities.Card

/**
 * Created by raugz on 11/2/2017.
 */
class CardRules {
    /**
     * true if player or dealer won
     */
    fun check21(player : List<Card>, dealer : List<Card>): Boolean {
        val playerScore = getScore(player)
        val dealerScore = getScore(dealer)
        return (playerScore >= 21 || dealerScore >= 21)
    }

    /**
     * returns true if the player wins, false if dealer wins
     */
    fun getWinner(player : List<Card>, dealer : List<Card>): Boolean{
        val playerScore = getScore(player)
        val dealerScore = getScore(dealer)
        if(playerScore > 21){
            return false
        }
        if (dealerScore > 21){
            return true
        }
        //TODO fix playerscore = dealerscore push
        return playerScore > dealerScore
    }

    //TODO fix ace is 11 or 1
    fun getScore(hand : List<Card>): Int{
        return hand.sumBy {
            if (it.value <= 10) it.value
            else{
                10
            }
        }
    }
}