package com.group2.blackjack.Game

import com.group2.blackjack.Entities.Card

/**
 * Created by raugz on 11/2/2017.
 */
class CardRules {
    /**
     * true if player won, false if dealer won
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
        return playerScore > dealerScore
    }

    private fun getScore(hand : List<Card>): Int{
        var sum = 0
        for(c : Card in hand){
            sum += c.value
        }
        return sum
    }
}