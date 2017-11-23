package com.group2.blackjack.Game;

import android.widget.TextView;

import com.group2.blackjack.Callbacks.GameOverCallback;
import com.group2.blackjack.Entities.Card;
import com.group2.blackjack.Entities.CardJV;
import com.group2.blackjack.Entities.Deck;
import com.group2.blackjack.Entities.DeckJV;
import com.group2.blackjack.Entities.Table;
import com.group2.blackjack.Entities.TableJV;
import com.group2.blackjack.Enums.EndGameState;
import com.group2.blackjack.Enums.EndGameStateJV;

import java.awt.font.TextAttribute;

/**
 * Created by root on 11.11.17.
 */

public class GameJV {
    private String uriPath = "@drawable/";
    private TextView balanceText;
    private GameOverCallback eventCaller;
    private boolean roundOver = true;
    private CardRulesJV rules;
    private TableJV table;
    private DeckJV deck;



    public TableJV getTable() {
        return table;
    }

    public GameJV(TextView view, GameOverCallback event){
        this.balanceText = view;
        this.eventCaller = event;
        initGame();
    }

    public void initGame(){
        table = new TableJV(500);
        rules = new CardRulesJV();
        deck = new DeckJV();
    }

    public void startRound(int bet){
        roundOver=false;
        deck.reShuffle();
        table.flushHands();

        //init hands 2 cards each
        for(int i = 0; i < 4; i ++){ // draws 0 to 3, 4 cards
            if (i%2 == 0){
                CardJV drew = deck.draw();
                table.dealCard(true, drew);
            } else { // i%2 == 1
                CardJV drew = deck.draw();

                table.dealCard(false, drew);

            }
        }

        if (checkOver()){
            endRound(rules.getWinner(table.getPlayer(), table.getDealer()));
        }

        table.placeBet(bet);
        balanceText.setText(""+table.getMoney());
    }



    private void endRound(EndGameState winner){
        if (winner == EndGameState.PLAYER) {
            int bet = table.getCurrentBet();
            table.addMoney(bet * 2);
            balanceText.setText(table.getMoney());
            eventCaller.endGame(EndGameState.PLAYER);
        }
        else if(winner == EndGameState.DEALER){
            eventCaller.endGame(EndGameState.DEALER);
        }
        else{
            int bet = table.getCurrentBet();
            table.addMoney(bet);
            balanceText.setText(table.getMoney());
        }

    }

    /**
     * checks the cards, and determined if there is a winner
     * should be called every time someone draws, to see if they pop
     */
    private boolean checkOver(){
        if (rules.check21(table.getPlayer(), table.getDealer())){
            roundOver = true;
            return true;
        }
        return false;
    }

    public CardJV playerHit(){
        if (!roundOver){
            CardJV drewCard = deck.draw();
            table.dealCard(true, drewCard);
            if (checkOver()){
                endRound(rules.getWinner(table.getPlayer(), table.getDealer())); // true = player won
            }
            return drewCard;
        }
        return null;
    }

    public CardJV dealerHit(){
        //TODO call round over in main activity? then start new round from there if we dont want user to do it manually
        if (!roundOver){
            if(rules.getScore(table.getDealer()) < 17){
                CardJV drewCard = deck.draw();
                table.dealCard(false, drewCard);
                if (checkOver()){ // true = someone has over 21 TODO fix real rules
                    endRound(rules.getWinner(table.getPlayer(), table.getDealer())); // true = player won
                }
                return drewCard;
            }
        }
        return null;
    }

    /**
     * Player stands, dealer draws if rules are satisfied,
     * returns the card drawn by dealer untill he stops drawing, then returns null
     */
    public CardJV stand(){
        if (!roundOver){
            if (checkOver()){
                endRound(rules.getWinner(table.getPlayer(), table.getDealer())); // true = player won
                return null;
            }
            else{
                boolean under21 = rules.getScore(table.getDealer()) < 21;
                boolean over16 = rules.getScore(table.getDealer()) > 16;
                CardJV drew = deck.draw();
                if(under21 || over16){
                    table.dealCard(false, drew);
                    checkOver();
                    return drew;
                } else{
                    endRound(rules.getWinner(table.getPlayer(), table.getDealer()));
                    return null;
                }
            }
        }
        return null;
    }

    public boolean getRoundOver() {
        return roundOver;
    }
}
