package com.group2.blackjack.Game;

import com.group2.blackjack.Entities.Card;

import java.util.Collection;
import java.util.List;

/**
 * Created by root on 11.11.17.
 */

public class CardRulesJV {
    /**
     * true if player or dealer won
     */
    public boolean check21(List<Card> player, List<Card> dealer){
        int playerScore = getScore(player);
        int dealerScore = getScore(dealer);
        return (playerScore >= 21 || dealerScore >= 21);
    }

    /**
     * returns true if the player wins, false if dealer wins
     */
    public boolean getWinner(List<Card> player, List<Card> dealer){
        int playerScore = getScore(player);
        int dealerScore = getScore(dealer);
        if(playerScore > 21){
            return false;
        }
        if (dealerScore > 21){
            return true;
        }
        //TODO fix playerscore = dealerscore push
        return playerScore > dealerScore;
    }

    public int getScore(List<Card> hand){
        int sum = 0;
        for (Card c: hand) {
            if(c.getValue() <= 10) c.getValue();
            else{
                sum+=10;
            }
        }
        return sum;
    }

}
