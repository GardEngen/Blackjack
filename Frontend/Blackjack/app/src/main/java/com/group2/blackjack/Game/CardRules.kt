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
            else -> when {
                playerScore > dealerScore -> EndGameState.PLAYER
                playerScore < dealerScore -> EndGameState.DEALER
                else -> EndGameState.PUSH
            }
        }

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