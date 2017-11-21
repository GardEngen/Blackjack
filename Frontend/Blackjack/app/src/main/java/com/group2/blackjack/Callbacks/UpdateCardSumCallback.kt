package com.group2.blackjack.Callbacks

/**
 * Created by raugz on 11/21/2017.
 */
interface UpdateCardSumCallback {
    fun updateSum(player : Int, dealer : Int, showDealer : Boolean)
}