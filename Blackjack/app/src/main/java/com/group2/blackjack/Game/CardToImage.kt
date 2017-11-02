package com.group2.blackjack.Game

import com.group2.blackjack.Entities.Card
import com.group2.blackjack.Enums.Color

/**
 * Created by root on 02.11.17.
 */

fun getImage(card: Card):String{
    var path = "app/res/drawable/";
    path += card.color.toString()
    path += card.value
    return path;
}