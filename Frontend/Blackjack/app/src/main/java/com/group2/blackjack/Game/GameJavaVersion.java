package com.group2.blackjack.Game;

import android.widget.TextView;

import com.group2.blackjack.Entities.Card;
import com.group2.blackjack.Entities.Deck;
import com.group2.blackjack.Entities.Table;

import java.awt.font.TextAttribute;

/**
 * Created by root on 11.11.17.
 */

public class GameJavaVersion {
    private String uriPath = "@drawable/";
    private TextView balanceText;

    private boolean roundover = false;
    private CardRules rules;
    private Table table;
    private Deck deck;

    public Table getTable() {
        return table;
    }

    public GameJavaVersion(TextView view){
        this.balanceText = view;
    }

    public void initGame(){
        table = new Table(500);
        rules = new CardRules();
        deck = new Deck();
    }

    public void startRound(){
        roundover=false;
        deck.reShuffle();
        table.flushHands();

        //init hands 2 cards each
        for(int i = 0; i < 4; i ++){ // draws 0 to 3, 4 cards
            if (i%2 == 0){
                Card drew = deck.draw();
                table.dealCard(true, drew);
            } else { // i%2 == 1
                Card drew = deck.draw();

                table.dealCard(false, drew);

            }
        }
        //TODO add entry money input from user
        int bet = 20;
        table.placeBet(bet);
        balanceText.setText(""+table.getMoney());
    }

    private void endRound(boolean winner){
        if (winner){
            int bet = table.getCurrentBet();
            table.addMoney(bet*2);
            System.out.println("Player won");
        }
        else{
            System.out.println("Dealer won");
        }
        //Thread.sleep(2000) // allow user to see result
        //startRound()
    }

    /**
     * checks the cards, and determined if there is a winner
     * should be called every time someone draws, to see if they pop
     */
    private boolean checkOver(){
        if (rules.check21(table.getPlayer(), table.getDealer())){
            roundover = true;
            return true;
        }
        return false;
    }

    public void newGame(){
        //unsused
    }

    public Card playerHit(){
        if (!roundover){
            Card drewCard = deck.draw();
            table.dealCard(true, drewCard);
            if (checkOver()){
                endRound(rules.getWinner(table.getPlayer(), table.getDealer())); // true = player won
            }
            return drewCard;
        }
        return null;
    }

    public Card dealerHit(){
        //TODO call round over in main activity? then start new round from there if we dont want user to do it manually
        if (!roundover){
            if(rules.getScore(table.getDealer()) < 17){
                Card drewCard = deck.draw();
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
    public Card stand(){
        if (!roundover){
            if (checkOver()){
                endRound(rules.getWinner(table.getPlayer(), table.getDealer())); // true = player won
                return null;
            }
            else{
                boolean under21 = rules.getScore(table.getDealer()) < 21;
                boolean over16 = rules.getScore(table.getDealer()) > 16;
                Card drew = deck.draw();
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

    //TODO future
    public void split(){
        System.out.println("hei");
    }
    //TODO future
    public void doubleFun(){//had to rename because double is a thing in java?

    }

}
