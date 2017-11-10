package com.group2.blackjack.Enums

/**
 * Created by raugz on 11/2/2017.
 */
enum class Color {
    SPADES, DIAMONDS, HEARTHS, CLUBS, CARDBACK;

     fun toChar(): Char{
        return when(this){
            Color.CLUBS     -> 'c'
            Color.DIAMONDS  -> 'd'
            Color.SPADES    -> 's'
            Color.HEARTHS   -> 'h'
            Color.CARDBACK  -> 'b'
        }
    }
}



