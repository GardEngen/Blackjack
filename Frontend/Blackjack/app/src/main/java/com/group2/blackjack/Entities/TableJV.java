package com.group2.blackjack.Entities;

import java.util.ArrayList;

/**
 * Created by dolgo on 11/11/2017.
 */

public class TableJV {
    private int money;
    private int currentBet = 0;
    private ArrayList<CardJV> player = new ArrayList<CardJV>();
    private ArrayList<CardJV> dealer = new ArrayList<CardJV>();
    public TableJV(int money){
        this.money = money;
    }

    public void placeBet(int bet){
        money -= bet;
        currentBet = bet;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void dealCard(boolean destination, CardJV card){
        if(destination)player.add(card);
        else dealer.add(card);
    }

    public void flushHands(){
        player.clear();
        dealer.clear();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }


    public int getCurrentBet() {
        return currentBet;
    }



    public ArrayList<CardJV> getPlayer() {
        return player;
    }



    public ArrayList<CardJV> getDealer() {
        return dealer;
    }


}
