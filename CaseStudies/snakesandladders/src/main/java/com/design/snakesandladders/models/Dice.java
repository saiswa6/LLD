package com.design.snakesandladders.models;

import java.util.Random;

public class Dice {
    private int maxNumber;

    Dice(int maxNumber){
        this.maxNumber = maxNumber;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public int rollAndReturnRandomDiceNumber(){
        Random random = new Random();
        int randomDiceNumber = random.nextInt(1,maxNumber + 1);
        return randomDiceNumber;
    }
}
