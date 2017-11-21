package com.group2.blackjack.Callbacks

import com.group2.blackjack.Enums.EndGameState

/**
 * Created by raugz on 11/11/2017.
 */
interface GameOverCallback {
    fun endGame(winner : EndGameState)
}