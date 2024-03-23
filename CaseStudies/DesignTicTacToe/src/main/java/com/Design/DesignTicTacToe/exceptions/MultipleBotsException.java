package com.Design.DesignTicTacToe.exceptions;

public class MultipleBotsException extends Exception{
    public MultipleBotsException(){
        super("A game cannot have more than one not players");
    }
}
