package com.design.snakesandladders.exceptions;

public class InvalidDiceNumberException extends Exception{
    public InvalidDiceNumberException(){
        super("Dice Number is greater than Board. Choose Maximum Dice Number less than Board size");
    }
}
