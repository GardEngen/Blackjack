package com.group2.blackjack.Entities

import com.group2.blackjack.Enums.Color

/**
 * Created by raugz on 11/2/2017.
 */
class Card constructor(color: Color, value: Int){
    val color = color
    val value = value

    override fun toString(): String{
        return "" + color.toChar() + value;
    }

}