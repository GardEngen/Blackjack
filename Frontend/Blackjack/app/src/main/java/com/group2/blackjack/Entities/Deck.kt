package com.group2.blackjack.Entities

import com.group2.blackjack.Enums.Color
import java.util.*

/**
 * Created by raugz on 11/2/2017.
 */
class Deck constructor(){
    var cards = ArrayList<Card>()

    init {
        reShuffle()
    }

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


    fun shuffle() {
        Collections.shuffle(cards)
    }

    fun draw() : Card{
        return cards.removeAt(0)
    }

    fun drawBackCard() : Card{
        return Card(Color.CARDBACK,0)
    }



}