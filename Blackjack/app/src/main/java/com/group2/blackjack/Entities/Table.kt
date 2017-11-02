package com.group2.blackjack.Entities

/**
 * Created by raugz on 11/2/2017.
 */
class Table constructor(m : Int){
    var money = m;
    var currentBet : Int = 0
    var player = ArrayList<Card>()
    var dealer = ArrayList<Card>()

    fun placeBet(b : Int){
        currentBet = b
        money -= b
    }

    fun addMoney(m: Int){
        money += m
    }

    fun dealCard(destination : Boolean, card: Card){ //true for player, false for dealer
        if (destination){
            player.add(card)
        }
        else{
            dealer.add(card)
        }
    }

    fun flushHands(){
        player = ArrayList<Card>()
        dealer = ArrayList<Card>()
    }
}