package com.group2.blackjack.Entities;

import com.group2.blackjack.Enums.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by dolgo on 11/11/2017.
 */

public class DeckJV {
    private ArrayList<CardJV> cards = new ArrayList<CardJV>();
    public ArrayList<CardJV> getCards() {
        return cards;
    }

    public DeckJV(){
        reShuffle();
    }

    public void reShuffle(){
        cards.clear();
        for(int i=1;i<14;i++){
            cards.add(new CardJV(Color.SPADES, i));
            cards.add(new CardJV(Color.HEARTHS, i));
            cards.add(new CardJV(Color.CLUBS, i));
            cards.add(new CardJV(Color.DIAMONDS, i));
        }
        shuffle();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public CardJV draw(){
        return cards.remove(0);
    }

    public CardJV drawBackCard(){
        return new CardJV(Color.CARDBACK,0);
    }

}
