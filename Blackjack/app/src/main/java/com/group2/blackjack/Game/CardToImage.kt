package com.group2.blackjack.Game

import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Enums.Color

/**
 * Created by root on 02.11.17.
 */

fun getImage(card: Card):String{
    var path = "app/res/drawable/";

    when(card.color){
        Color.CLUBS     -> path += "c"
        Color.DIAMONDS  -> path += "d"
        Color.SPADES    -> path += "s"
        Color.HEARTHS   -> path += "h"
    }

    path += card.value
    return path;

}