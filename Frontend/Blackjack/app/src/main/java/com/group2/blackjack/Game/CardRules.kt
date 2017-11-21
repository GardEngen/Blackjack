package com.group2.blackjack.Game

import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Enums.EndGameState

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
     * returns the EndGameState determined by the user and dealers scores
     */
    fun getWinner(player : List<Card>, dealer : List<Card>): EndGameState{
        val playerScore = getScore(player)
        val dealerScore = getScore(dealer)
        return when {
            playerScore > 21 -> EndGameState.DEALER
            dealerScore > 21 -> EndGameState.PLAYER
            playerScore > dealerScore -> EndGameState.PLAYER
            playerScore < dealerScore -> EndGameState.DEALER
            else -> EndGameState.PUSH
        }

    }

    fun getScore(hand : List<Card>): Int{
        var sumAceOne = 0
        hand.forEach { card ->
            sumAceOne += when {
                card.value > 10 -> 10
                else -> card.value
            }
        }

        var sumAceEleven = 0
        hand.forEach { card ->
            sumAceEleven += when {
                card.value == 1 -> 11
                card.value > 10 -> 10
                else -> card.value
            }
        }

        return if (sumAceEleven > 21) {
            sumAceOne
        } else {
            sumAceEleven
        }
    }
}