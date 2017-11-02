package com.group2.blackjack.Enums

/**
 * Created by raugz on 11/2/2017.
 */
enum class Color {
    SPADES, DIAMONDS, HEARTHS, CLUBS;

    override fun toString(): String{
        return when(this){
            Color.CLUBS     -> "c"
            Color.DIAMONDS  -> "d"
            Color.SPADES    -> "s"
            Color.HEARTHS   -> "h"
        }
    }


}



