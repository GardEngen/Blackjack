package com.group2.blackjack.Enums;

/**
 * Created by root on 11.11.17.
 */

public enum ColorJV {
    SPADES, DIAMONDS, HEARTHS, CLUBS, CARDBACK;

    public char toChar(){
        switch (this){
            case CLUBS:     return 'c';
            case DIAMONDS:  return 'd';
            case SPADES:    return 's';
            case HEARTHS:   return 'h';
            case CARDBACK:  return 'b';
        }
        return 'f'; //kinda pointless
    }
}
