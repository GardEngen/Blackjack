package com.group2.blackjack.Entities;

import com.group2.blackjack.Enums.Color;

/**
 * Created by dolgo on 11/11/2017.
 */

public class CardJV {
    private Color color;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private int value;
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }



    public CardJV(Color color, int value){
        this.color = color;
        this.value = value;
    }

    @Override
    public String toString(){
        return "" + color.toChar() + value;
    }
}
