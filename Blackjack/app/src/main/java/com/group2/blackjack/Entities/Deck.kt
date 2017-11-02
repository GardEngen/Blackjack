package com.group2.blackjack.Entities

import com.group2.blackjack.Enums.Color
import java.util.*

/**
 * Created by raugz on 11/2/2017.
 */
class Deck constructor(){
    var cards = ArrayList<Card>()


    fun reShuffle(){
        cards.clear()
        for(i in 1 until 14){
            cards.add(Card(Color.SPADES, i))
            cards.add(Card(Color.HEARTHS, i))
            cards.add(Card(Color.CLUBS, i))
            cards.add(Card(Color.DIAMONDS, i))
        }
        shuffle()
    }

    init {
        reShuffle()
    }

    fun shuffle() {
        Collections.shuffle(cards)
    }

    fun draw() : Card{
        return cards.removeAt(0)
    }



}