package com.group2.blackjack.Game;

import com.group2.blackjack.Entities.Card;
import com.group2.blackjack.Entities.CardJV;
import com.group2.blackjack.Enums.EndGameState;


import java.util.Collection;
import java.util.List;

/**
 * Created by root on 11.11.17.
 */

public class CardRulesJV {
    /**
     * true if player or dealer won
     */
    public boolean check21(List<CardJV> player, List<CardJV> dealer){
        int playerScore = getScore(player);
        int dealerScore = getScore(dealer);
        return (playerScore >= 21 || dealerScore >= 21);
    }

    /**
     * returns true if the player wins, false if dealer wins
     */
    public EndGameState getWinner(List<CardJV> player, List<CardJV> dealer){
        int playerScore = getScore(player);
        int dealerScore = getScore(dealer);

        if(playerScore > 21){
            return EndGameState.DEALER;
        }
        else if(dealerScore > 21){
            return EndGameState.PLAYER;
        }
        else{
            if(playerScore > dealerScore) {
                return EndGameState.PLAYER;
            }
            else if(playerScore < dealerScore){
                return EndGameState.DEALER;
            }
            else{
                return EndGameState.PUSH;
            }
        }
    }

    public int getScore(List<CardJV> hand){
        int sumAceOne = 0;

        for (CardJV card: hand) {
            if(card.getValue() < 10)
                sumAceOne+= 10;
            else
                sumAceOne+=card.getValue();
        }

        int sumAceEleven = 0;
        for (CardJV card: hand) {
            if(card.getValue() == 1)
                sumAceEleven+=11;
            else if(card.getValue() > 10)
                sumAceEleven+=10;
            else
                sumAceEleven +=card.getValue();
        }


        if (sumAceEleven > 21) {
            return sumAceOne;
        } else {
            return sumAceEleven;
        }
    }

}
